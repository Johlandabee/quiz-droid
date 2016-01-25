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

public class QuizManager {
    private static QuizManager      _instance;
    private static QuizManagerState _state = QuizManagerState.UNINITIALIZED;

    private final static String DEBUG_LOG_TAG = "QDQuizManager";
    private final boolean       DEBUG_IGNORE_CATEGORY = false;
    private final boolean       DEBUG_IGNORE_DIFFICULTY = false;

    private Context      _context;
    private QuizSettings _qSettings;

    private Queue<Question> _qQueue;
    private List<Player> _pList;

    //--------------------------------------------------------------------------------------------->

    private QuizManager(Context context) {
        Log.d(DEBUG_LOG_TAG, "QuizManager(): Initializing...");

        _qQueue = new LinkedList<>();
        _pList = new ArrayList<>();

        Log.d(DEBUG_LOG_TAG, "QuizManager(): Initialized!");
    }

    //--------------------------------------------------------------------------------------------->

    public static QuizManager getInstance(){
        if (_state == QuizManagerState.UNINITIALIZED) {
            Log.d(DEBUG_LOG_TAG, "getInstance(): Not initialized! Returning NULL!");
            return null;
        }
        Log.d(DEBUG_LOG_TAG, "getInstance(): Returning instance");
        return _instance;
    }

    //--------------------------------------------------------------------------------------------->

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

    //--------------------------------------------------------------------------------------------->

    private void setQuestionQueue()
    {
        _qQueue.clear();

        List<Question> qCache = new ArrayList<>();

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

        Cursor c = DatabaseHelper.getInstance().getDb().rawQuery(sql, null);

        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                qCache.add(new Question(
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
        while(!qCache.isEmpty()){
            int b = Util.getRandomRange(0, qCache.size() - 1);

            while(_qQueue.contains(qCache.get(b))){
                b = Util.getRandomRange(0 , qCache.size() - 1);
            }

            Log.d(DEBUG_LOG_TAG,String.format("setQuestionQueue(): Random index=%d", b));

            _qQueue.add(qCache.get(b));
            qCache.remove(b);
            i++;
            if(i == _qSettings.getQuizLength().getValue() && !qCache.isEmpty())
                qCache.clear();
        }

        Log.d(DEBUG_LOG_TAG, String.format("setQuestionQueue(): _qQueue size=%d", _qQueue.size()));
    }

    //--------------------------------------------------------------------------------------------->

    public void start (QuizSettings settings){
        _qSettings = settings;
        setQuestionQueue();
    }

    //--------------------------------------------------------------------------------------------->

}
