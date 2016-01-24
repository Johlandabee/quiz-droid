package de.thenutheads.jlndbe.quizdroid.Logic;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;
import de.thenutheads.jlndbe.quizdroid.DatabaseHelper;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public class QuizManager {
    private final static String DEBUG_LOG_TAG = "QDQuizManager";
    private final boolean DEBUG_IGNORE_CATEGORY = false;

    private static QuizManager _instance;
    private static QuizManagerState _state = QuizManagerState.UNINITIALIZED;

    private Context _context;

    private QuizSettings _quizSettings;

    private List<Question> _questionQueue;

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

        Log.d(DEBUG_LOG_TAG, "QuizManager(): Initialized!");
    }

    public static void doInitialization (Context context){
        if (_state != QuizManagerState.UNINITIALIZED) {
            Log.d(DEBUG_LOG_TAG, "doInitialization(): Already initialized!");
            return;
        }

        _instance = new QuizManager(context);
        _state = QuizManagerState.INITIALIZED;
    }

    public void setContext(Context context){
        _context = context;
        Log.d(DEBUG_LOG_TAG, "setContext(): Context set!");
    }

    /**
     * https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
     */
    public void buildQuestionQueue()
    {
        // DEBUG BEGIN
        _quizSettings = new QuizSettings(QuizDifficulty.EASY,
                new QuizCategory(1, "category_history"),
                QuizLength.MEDIUM, QuizMode.SINGLEPLAYER);

        // DEBUG END

        SQLiteDatabase db = DatabaseHelper.getInstance().getDatabase();

        String sql = "SELECT quiz_data._id,quiz_data.question," +
                "quiz_data.correct_answer,quiz_data.answer_b," +
                "quiz_data.answer_c,quiz_data.answer_d," +
                "quiz_data.difficulty, quiz_categories.categoryname," +
                "quiz_localization.languagecode " +
                "FROM quiz_data, quiz_categories, quiz_localization " +
                "WHERE quiz_data.category=quiz_categories._id " +
                "AND quiz_data.locale=quiz_localization._id ";

        if(!DEBUG_IGNORE_CATEGORY)
            sql+= "AND quiz_categories.categoryname=" + "'" +
                    _quizSettings.getQuizCategory().getCategoryName() + "'";

        Cursor c = db.rawQuery(sql, null);


        c.close();
    }

    public void newGame (QuizSettings settings){

    }
}
