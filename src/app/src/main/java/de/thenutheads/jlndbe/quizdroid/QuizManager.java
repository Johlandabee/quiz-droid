package de.thenutheads.jlndbe.quizdroid;

/**
 * Created by Jlndbe on 20.01.2016.
 */
public class QuizManager {
    private static QuizManager _instance;
    private QuizSettings _quizSettings;

    public QuizManager(){

        _instance = this;
    }

    public static QuizManager Instance(){
        return _instance();
    }

    public Question getNextQuestion(){

    }

    public void
}
