package de.thenutheads.jlndbe.quizdroid.Logic;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.thenutheads.jlndbe.quizdroid.DatabaseHelper;
import de.thenutheads.jlndbe.quizdroid.Util;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public class QuizManager {
    private final static String DEBUG_LOG_TAG = "QDQuizManager";
    private final boolean DEBUG_IGNORE_CATEGORY = false,
        DEBUG_IGNORE_DIFFICULTY = false;

    private static QuizManager _instance;
    private static QuizManagerState _state = QuizManagerState.UNINITIALIZED;

    private Context _context;

    private QuizSettings _qSettings;

    private Queue<Question> _qQueue;
    private List<Player> _playerList;

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

        _qQueue = new LinkedList<>();
        _playerList = new ArrayList<>();

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

    public Question getNextQuestion(){
        return _qQueue.remove();
    }

    /**
     * Methode ist noch nicht Idioten-Sicher
     */
    private void buildQuestionQueue()
    {
        SQLiteDatabase db = DatabaseHelper.getInstance().getDatabase();
        List<Question> qList = new ArrayList<>();
        _qQueue.clear();

        String sql = "SELECT quiz_data._id,quiz_data.question," +
                "quiz_data.correct_answer,quiz_data.answer_b," +
                "quiz_data.answer_c,quiz_data.answer_d," +
                "quiz_data.difficulty, quiz_categories.categoryname," +
                "quiz_localization.languagecode " +
                "FROM quiz_data, quiz_categories, quiz_localization " +
                "WHERE quiz_data.category=quiz_categories._id " +
                "AND quiz_data.locale=quiz_localization._id ";

        if(!DEBUG_IGNORE_DIFFICULTY)
            sql+="AND quiz_data.difficulty<=" + _qSettings.getQuizDifficulty().getValue() + " ";

        if(!DEBUG_IGNORE_CATEGORY)
            sql+= "AND quiz_categories.categoryname=" + "'" +
                    _qSettings.getQuizCategory().getCategoryName() + "'";

        Cursor c = db.rawQuery(sql, null);

        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                qList.add(new Question(
                        c.getInt(c.getColumnIndex("_id")),
                        c.getString(c.getColumnIndex("question")),
                        c.getString(c.getColumnIndex("correct_answer")),
                        c.getString(c.getColumnIndex("answer_b")),
                        c.getString(c.getColumnIndex("answer_c")),
                        c.getString(c.getColumnIndex("answer_d")),
                        c.getString(c.getColumnIndex("languagecode")))
                );
                c.moveToNext();
            }
        }
        c.close();

        int i = 0;
        while(!qList.isEmpty()){
            int b = Util.getRandomRange(0, qList.size() - 1);

            while(_qQueue.contains(qList.get(b))){
                b = Util.getRandomRange(0 , qList.size() - 1);
            }

            Log.d(DEBUG_LOG_TAG,String.format("buildQuestionQueue(): Random index=%d", b));

            _qQueue.add(qList.get(b));
            qList.remove(b);
            i++;
            if(i == _qSettings.getQuizLength().getValue() && !qList.isEmpty())
                qList.clear();
        }

        Log.d(DEBUG_LOG_TAG, String.format("buildQuestionQueue(): _qQueue size=%d", _qQueue.size()));
    }

    public void startNewGame (QuizSettings settings){
        _qSettings = settings;
        buildQuestionQueue();
    }
}
