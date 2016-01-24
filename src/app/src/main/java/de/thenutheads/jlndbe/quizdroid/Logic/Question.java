package de.thenutheads.jlndbe.quizdroid.Logic;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public class Question {
    private int _id;
    private String[] _questionData;

    public Question(int id, String question, String correct_answer, String answer_b, String answer_c, String answer_d, String locale){
        _id = id;
        _questionData = new String[]{
               question,
               correct_answer,
               answer_b,
               answer_c,
               answer_d,
               locale
        };
    }

    public int getId(){
        return _id;
    }

    public String[] getDataArray(){
        return _questionData;
    }

    public String getQuestion(){
        return _questionData[0];
    }

    public String getCorrectAnswer(){
        return _questionData[1];
    }

    public String getAnswerB(){
        return _questionData[2];
    }

    public String getAnswerC(){
        return _questionData[3];
    }

    public String getAnswerD(){
        return _questionData[4];
    }

    public String getLanguageCode(){
        return _questionData[5];
    }
}
