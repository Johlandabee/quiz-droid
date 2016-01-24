package de.thenutheads.jlndbe.quizdroid.Logic;

import android.content.Context;

import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.R;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public enum QuizMode {
    SINGLEPLAYER,
    VERSUS;

    @Override
    public String toString(){
        return toLocalizedString(this, App.getContext());
    }

    private String toLocalizedString(QuizMode mode, Context context){
        String value = "";
        switch (mode) {
            case SINGLEPLAYER:
                value = context.getString(R.string.quiz_mode_singleplayer);
                break;
            case VERSUS:
                value = context.getString(R.string.quiz_mode_versus);
                break;
        }
        return value;
    }
}
