package de.thenutheads.jlndbe.quizdroid.Logic;

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

public class QuizSettings {

    private QuizDifficulty _difficultyPreset;
    private QuizCategory _quizCategory;
    private QuizLength _quizLengthPreset;
    private QuizMode _quizMode;

    //--------------------------------------------------------------------------------------------->

    public QuizSettings(QuizDifficulty difficulty, QuizCategory category, QuizLength length, QuizMode mode) {
        _quizLengthPreset = length;
        _quizMode = mode;
        _difficultyPreset = difficulty;

        _quizCategory = category;
    }

    //--------------------------------------------------------------------------------------------->

    public QuizCategory getQuizCategory(){
        return _quizCategory;
    }

    //--------------------------------------------------------------------------------------------->

    public QuizDifficulty getQuizDifficulty() {
        return _difficultyPreset;
    }

    //--------------------------------------------------------------------------------------------->

    public QuizLength getQuizLength(){
        return _quizLengthPreset;
    }

    //--------------------------------------------------------------------------------------------->

    public QuizMode getQuizMode(){
        return _quizMode;
    }

    //--------------------------------------------------------------------------------------------->
}
