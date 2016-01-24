package de.thenutheads.jlndbe.quizdroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private final String DEBUG_LOG_TAG = "QDDatabaseHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "questions.db";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(DEBUG_LOG_TAG, String.format("DatabaseHelper(): DATABASE_NAME=%s DATABASE_VERSION=%d", DATABASE_NAME, DATABASE_VERSION));
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
