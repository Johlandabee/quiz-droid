package de.thenutheads.jlndbe.quizdroid;

import android.app.Application;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Jlndbe on 24.01.2016.
 * Inspired by https://goo.gl/QOmBlf
 * <p/>
 * <p/>
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2016 Steven Johlandabee Wobser
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper      _instance;
    private static DatabaseHelperState _state = DatabaseHelperState.UNINITIALIZED;

    private static final String DB_NAME = "release_quiz.db";
    private static final String DB_PATH = "/data/data/de.thenutheads.jlndbe.quizdroid/databases/";
    private static final String DB_PATH_COMPLETE = DB_PATH + DB_NAME;
    private static final int    DB_VERSION = 1;

    private final String DEBUG_LOG_TAG = "QDDatabaseHelper";

    private Context _context;

    private SQLiteDatabase _db;

    //--------------------------------------------------------------------------------------------->

    private DatabaseHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        _context = context;

    if(!dbExists() || App.DEBUG_COPY_DATABASE){
            this.getReadableDatabase();

            try{
                copyDbFromAssets();
            } catch (IOException e){
                Toast.makeText(_context, ErrorCode.DB_COULD_NOT_COPY.toString(), Toast.LENGTH_LONG).show();
                App.delayedExit(ErrorCode.DB_COULD_NOT_COPY.getValue());
            }
        }

        Log.d(DEBUG_LOG_TAG, String.format("DatabaseHelper(): DATABASE_NAME=%s DATABASE_VERSION=%d",
                DB_NAME, DB_VERSION));
    }

    //--------------------------------------------------------------------------------------------->

    public static void doInitialization(Context context){
        if(_state != DatabaseHelperState.UNINITIALIZED) return;

        _instance = new DatabaseHelper(context);

        _state = DatabaseHelperState.CLOSED;
    }

    //--------------------------------------------------------------------------------------------->

    public static DatabaseHelper getInstance(){
        if (_state == DatabaseHelperState.UNINITIALIZED) return null;
        return _instance;
    }

    //--------------------------------------------------------------------------------------------->

    public SQLiteDatabase getDb() {
        try {
            if (_state == DatabaseHelperState.CLOSED || _db == null) {
                _db = SQLiteDatabase.openDatabase(DB_PATH_COMPLETE, null, SQLiteDatabase.OPEN_READONLY);
                _state = DatabaseHelperState.OPEN;
            }
        } catch (SQLiteException e){
            Toast.makeText(_context, ErrorCode.DB_COULD_NOT_OPEN.toString(), Toast.LENGTH_LONG).show();
            App.delayedExit(ErrorCode.DB_COULD_NOT_OPEN.getValue());
        }
        return _db;
    }

    //--------------------------------------------------------------------------------------------->

    private void closeDb() {
        if(_state != DatabaseHelperState.OPEN || _db == null){
            Log.d(DEBUG_LOG_TAG,"closeDb(): Database already closed!");
            return;
        }

        _db.close();
    }

    //--------------------------------------------------------------------------------------------->

    public DatabaseHelperState getState(){
        return _state;
    }

    //--------------------------------------------------------------------------------------------->

    private boolean dbExists(){
        SQLiteDatabase db = null;

        try{
            db = SQLiteDatabase.openDatabase(DB_PATH_COMPLETE,null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e){

        }

        if(db != null){
            db.close();
        }

        return db != null ? true : false;
    }

    //--------------------------------------------------------------------------------------------->

    private void copyDbFromAssets() throws IOException {
        InputStream in = _context.getAssets().open(DB_NAME);
        OutputStream out =  new FileOutputStream(DB_PATH_COMPLETE);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer))>0){
            out.write(buffer, 0, length);
        }

        out.flush();
        out.close();
        in.close();
    }

    //--------------------------------------------------------------------------------------------->

    public void onCreate(SQLiteDatabase db) {}

    //--------------------------------------------------------------------------------------------->

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    //--------------------------------------------------------------------------------------------->

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    //--------------------------------------------------------------------------------------------->

}
