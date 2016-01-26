package de.thenutheads.jlndbe.quizdroid;

import android.content.Context;

/**
 * Created by Jlndbe on 25.01.2016.
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

public enum ErrorCode {
    DB_COULD_NOT_COPY(1010),
    DB_COULD_NOT_OPEN(1020),
    DB_CORRUPT(1030),

    XML_CATEGORY_NOT_DEFINED(2010);

    ErrorCode(int code){
        _code = code;
    }

    private final int _code;

    //--------------------------------------------------------------------------------------------->

    public int getValue(){
        return _code;
    }

    //--------------------------------------------------------------------------------------------->

    @Override
    public String toString(){
        return toLocalizedString(this, App.getContext());
    }

    //--------------------------------------------------------------------------------------------->

    private String toLocalizedString(ErrorCode code, Context context){
        switch (code) {
            case DB_COULD_NOT_COPY:
                return context.getString(R.string.error_db_could_not_copy);
            case DB_COULD_NOT_OPEN:
                return context.getString(R.string.error_db_could_not_open);
            case DB_CORRUPT:
                return context.getString(R.string.error_db_could_not_open);
            case XML_CATEGORY_NOT_DEFINED:
                return context.getString(R.string.error_xml_category_not_defined);
            default:
                return null;
        }
    }

    //--------------------------------------------------------------------------------------------->
}
