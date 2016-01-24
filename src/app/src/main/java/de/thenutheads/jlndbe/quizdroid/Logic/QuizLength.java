package de.thenutheads.jlndbe.quizdroid.Logic;

import android.content.Context;

import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.R;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public enum QuizLength {
    SHORT(5),
    MEDIUM(10),
    LONG(20),
    VERY_LONG(40);

    private final int length;

    QuizLength(int length){
        this.length = length;
    }

    public int getValue() {
        return length;
    }

    @Override
    public String toString(){
        return toLocalizedString(this, App.getContext());
    }

    private String toLocalizedString(QuizLength length, Context context){
        String value = "";
        switch (length) {
            case SHORT:
                value = context.getString(R.string.quiz_length_short);
                break;
            case MEDIUM:
                value = context.getString(R.string.quiz_length_medium);
                break;
            case LONG:
                value = context.getString(R.string.quiz_length_long);
                break;
            case VERY_LONG:
                value = context.getString(R.string.quiz_length_very_long);
                break;
        }
        return value;
    }
}
