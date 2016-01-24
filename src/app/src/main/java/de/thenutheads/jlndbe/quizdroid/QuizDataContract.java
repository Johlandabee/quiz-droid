package de.thenutheads.jlndbe.quizdroid;

import android.provider.BaseColumns;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public final class QuizDataContract {

    public QuizDataContract(){}

    public static abstract class QuizData implements BaseColumns {
        public static final String TABLE_NAME = "quizdata",
                _ID = "questionid",
                COLUMN_NAME_QUESTION = "question",
                COLUMN_NAME_DIFFICULTY = "difficulty",
                COLUMN_NAME_CORRECT_ANSWER = "correct_answer",
                COLUMN_NAME_ANSWER_B = "answer_b",
                COLUMN_NAME_ANSWER_C = "answer_c",
                COLUMN_NAME_ANSWER_D= "answer_d",
                COLUMN_NAME_CATEGORY = "category",
                COLUMN_NAME_LANGUAGE = "language";
    }
}
