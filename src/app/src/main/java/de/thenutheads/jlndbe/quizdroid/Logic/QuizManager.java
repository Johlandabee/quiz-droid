package de.thenutheads.jlndbe.quizdroid.Logic;

import android.content.Context;
import android.util.Log;

import java.util.Queue;
import de.thenutheads.jlndbe.quizdroid.DatabaseHelper;
import de.thenutheads.jlndbe.quizdroid.QuizDataContract;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public class QuizManager {
    private final static String DEBUG_LOG_TAG = "QDQuizManager";

    private static QuizManager _instance;
    private static QuizManagerState _state = QuizManagerState.UNINITIALIZED;

    private Context _context;
    private DatabaseHelper _dbHelper;
    private QuizSettings _quizSettings;

    private Queue<Question> _questionQueue;

    public static QuizManager getInstance() {
        if (_state == QuizManagerState.UNINITIALIZED) {
            Log.d(DEBUG_LOG_TAG, "getInstance(): Not initialized! Returning NULL!");
            return null;
        }
        Log.d(DEBUG_LOG_TAG, "getInstance() called!");
        return _instance;
    }

    private QuizManager(Context context) {
        Log.d(DEBUG_LOG_TAG, "QuizManager(): Initializing...");

        _dbHelper = new DatabaseHelper(context);
        _state = QuizManagerState.INITIALIZED;

        Log.d(DEBUG_LOG_TAG, "QuizManager(): Initialized!");
    }

    public static void doInitialization (Context context){
        if (_state != QuizManagerState.UNINITIALIZED) {
            Log.d(DEBUG_LOG_TAG, "doInitialization(): Already initialized!");
            return;
        }

        _instance = new QuizManager(context);
    }

    public void setContext(Context context){
        _context = context;
        Log.d(DEBUG_LOG_TAG, "setContext(): Context set!");
    }

    private void buildQuestionQueue()
    {
        String[] projection = {
                QuizDataContract.QuizData._ID,
                QuizDataContract.QuizData.COLUMN_NAME_CATEGORY,
                QuizDataContract.QuizData.COLUMN_NAME_DIFFICULTY,
                QuizDataContract.QuizData.COLUMN_NAME_QUESTION,
                QuizDataContract.QuizData.COLUMN_NAME_CORRECT_ANSWER,
                QuizDataContract.QuizData.COLUMN_NAME_ANSWER_B,
                QuizDataContract.QuizData.COLUMN_NAME_ANSWER_C,
                QuizDataContract.QuizData.COLUMN_NAME_ANSWER_D,
                QuizDataContract.QuizData.COLUMN_NAME_LANGUAGE
        };


    }

    public void newGame (QuizSettings settings){

    }
}
