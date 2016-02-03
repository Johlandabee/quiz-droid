package de.thenutheads.jlndbe.quizdroid;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;

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

public class App extends Application {
    private static Context _context;

    public static final boolean DEBUG_COPY_DATABASE = true;
    public static final boolean DEBUG_IGNORE_CATEGORY = false;
    public static final boolean DEBUG_IGNORE_DIFFICULTY = false;

    public static final int APP_CLOSE_ON_ERROR_DELAY = 5;

    //--------------------------------------------------------------------------------------------->

    @Override
    public void onCreate(){
        super.onCreate();
        _context = this;

        DatabaseHelper.doInitialization(this);
        QuizManager.doInitialization(this);
    }

    //--------------------------------------------------------------------------------------------->

    public static Context getContext() throws IllegalStateException {
        return _context;
    }

    //--------------------------------------------------------------------------------------------->

    public static void delayedExit(final int code){
        Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                System.exit(code);
            }
        }, APP_CLOSE_ON_ERROR_DELAY, TimeUnit.SECONDS);
    }
}
