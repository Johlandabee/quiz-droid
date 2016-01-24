package de.thenutheads.jlndbe.quizdroid;

import android.app.Application;
import android.content.Context;

import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;

/**
 * Created by Jlndbe on 24.01.2016.
 * App Einstiegspunkt
 */
public class App extends Application {
    private static Context _context;

    @Override
    public void onCreate(){
        super.onCreate();
        QuizManager.doInitialization(this);

        _context = this;
    }

    public static Context getContext() throws IllegalStateException {
        return _context;
    }
}
