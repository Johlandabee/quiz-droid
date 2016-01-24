package de.thenutheads.jlndbe.quizdroid.Logic;

import android.content.Context;

import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.R;

/**
 * Created by Jlndbe on 24.01.2016.
 * Defifition der Schwierigkeitsgrade
 */
public enum QuizDifficulty {
    EASY (1),
    NORMAL (3),
    HARD (5);

    private final int level;
    QuizDifficulty(int level){
        this.level = level;
    }

    public int getValue() {
        return level;
    }

    @Override
    public String toString(){
        return toLocalizedString(this, App.getContext());
    }

    private String toLocalizedString(QuizDifficulty difficulty, Context context){
        String value = "";
        switch (difficulty) {
            case EASY:
                value = context.getString(R.string.quiz_difficulty_easy);
                break;
            case NORMAL:
                value = context.getString(R.string.quiz_difficulty_normal);
                break;
            case HARD:
                value = context.getString(R.string.quiz_difficulty_hard);
                break;
        }
        return value;
    }
}
