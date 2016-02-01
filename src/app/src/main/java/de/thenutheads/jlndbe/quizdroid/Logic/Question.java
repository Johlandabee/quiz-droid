package de.thenutheads.jlndbe.quizdroid.Logic;

import java.util.Arrays;

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

public class Question {
    private int _id;
    private int _difficulty;
    private String[] _questionData;

    //--------------------------------------------------------------------------------------------->

    public Question(int id, String question, String correct_answer, String answer_b,
                    String answer_c, String answer_d, String locale, int difficulty){
        _id = id;
        _difficulty = difficulty;
        _questionData = new String[]{
               question,
               correct_answer,
               answer_b,
               answer_c,
               answer_d,
               locale
        };
    }

    //--------------------------------------------------------------------------------------------->

    public int getId(){
        return _id;
    }

    //--------------------------------------------------------------------------------------------->

    public int getDifficulty() {
        return _difficulty;
    }

    //--------------------------------------------------------------------------------------------->

    public String[] getDataArray(){
        return _questionData;
    }

    //--------------------------------------------------------------------------------------------->

    public String getQuestion(){
        return _questionData[0];
    }

    //--------------------------------------------------------------------------------------------->

    public String[] getAnswers(){
        return Arrays.copyOfRange(_questionData, 1, 4);
    }

    //--------------------------------------------------------------------------------------------->

    public String getCorrectAnswer(){
        return _questionData[1];
    }

    //--------------------------------------------------------------------------------------------->

    public String getLanguageCode(){
        return _questionData[5];
    }

    //--------------------------------------------------------------------------------------------->
}
