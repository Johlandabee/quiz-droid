package de.thenutheads.jlndbe.quizdroid;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Jlndbe on 24.01.2016.
 * http://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper _instance;
    private static DatabaseHelperState _state = DatabaseHelperState.UNINITIALIZED;

    private final String DEBUG_LOG_TAG = "QDDatabaseHelper";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "quiz.db",
        DB_PATH = "/data/data/de.thenutheads.jlndbe.quizdroid/databases/",
        DB_PATH_COMPLETE = DB_PATH + DB_NAME;

    private Context _context;
    private SQLiteDatabase _db;

    private DatabaseHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        _context = context;

    if(!dataBaseExists() || BuildConfig.DEBUG){
            this.getReadableDatabase();

            try{
                copyDataBaseFromAssets();
            } catch (IOException e){
                //TODO:
            }
        }

        openDataBase();

        Log.d(DEBUG_LOG_TAG, String.format("DatabaseHelper(): DATABASE_NAME=%s DATABASE_VERSION=%d", DB_NAME, DB_VERSION));
    }

    public static void doInitialization(Context context){
        if(_state != DatabaseHelperState.UNINITIALIZED) return;

        _instance = new DatabaseHelper(context);

        _state = DatabaseHelperState.INITIALIZED;
    }

    public static DatabaseHelper getInstance(){
        if (_state != DatabaseHelperState.INITIALIZED) return null;
        return _instance;
    }

    public SQLiteDatabase getDatabase(){
        return _db;
    }

    private void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        _db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    private boolean dataBaseExists(){
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

    private void copyDataBaseFromAssets() throws IOException {
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

    public void onCreate(SQLiteDatabase db) {
        Log.d(DEBUG_LOG_TAG, "onCreate() called!");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DEBUG_LOG_TAG, "onUpgrade() called!");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DEBUG_LOG_TAG, "onDowngrade() called!");
    }

}
