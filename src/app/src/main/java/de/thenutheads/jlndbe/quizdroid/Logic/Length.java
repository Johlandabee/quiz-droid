package de.thenutheads.jlndbe.quizdroid.Logic;

import android.content.Context;

import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.R;

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

public enum Length {
    SHORT(5),
    MEDIUM(10),
    LONG(20),
    VERY_LONG(40);

    private final int _length;

    //--------------------------------------------------------------------------------------------->

    Length(int length){
        _length = length;
    }

    //--------------------------------------------------------------------------------------------->

    public int getValue() {
        return _length;
    }

    //--------------------------------------------------------------------------------------------->

    @Override
    public String toString(){
        return toLocalizedString(this, App.getContext());
    }

    //--------------------------------------------------------------------------------------------->

    private String toLocalizedString(Length length, Context context){
        switch (length) {
            case SHORT:
                return context.getString(R.string.quiz_length_short);
            case MEDIUM:
                return context.getString(R.string.quiz_length_medium);
            case LONG:
                return context.getString(R.string.quiz_length_long);
            case VERY_LONG:
                return context.getString(R.string.quiz_length_very_long);
            default:
                return null;
        }
    }

    //--------------------------------------------------------------------------------------------->

}
