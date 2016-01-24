package de.thenutheads.jlndbe.quizdroid.Logic;

import android.content.Context;
import android.util.Log;

import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.R;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public class QuizCategory {
    private final String DEBUG_LOG_TAG = "QDQuizCategory";

    private int _id;
    private String _categoryName, _localizedName;

    public QuizCategory(int id ,String categoryName){
        _id = id;
        _categoryName = categoryName;

        Context context = App.getContext();

        try {
           _localizedName = context.getString(context.getResources().getIdentifier(_categoryName, "string", context.getPackageName()));
        } catch (Exception e){
            _localizedName = _categoryName;
            Log.d(DEBUG_LOG_TAG,String.format("QuizCategory(): %s", e.getLocalizedMessage()));
        }

    }

    public int getId (){
        return _id;
    }

    public String getCategoryName(){
        return _categoryName;
    }

    public String getLocalizedName(){
        return _localizedName;
    }
}
