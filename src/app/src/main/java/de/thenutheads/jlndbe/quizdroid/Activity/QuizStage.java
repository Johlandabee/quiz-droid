package de.thenutheads.jlndbe.quizdroid.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.thenutheads.jlndbe.quizdroid.Logic.Player;
import de.thenutheads.jlndbe.quizdroid.Logic.Question;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizSettings;
import de.thenutheads.jlndbe.quizdroid.R;
import de.thenutheads.jlndbe.quizdroid.Util;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class QuizStage extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    private Question _question;
    private QuizSettings _settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_stage);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        setData();
    }

    public void onSelectAnswer (View view){
        Player player = QuizManager.getInstance().getActivePlayer();

        if(((Button) view).getText() == _question.getCorrectAnswer()){
            player.addScore(100 * _question.getDifficulty());
            player.addCorrect();
        } else {
            player.addTrail();
            Log.d("STAGE", String.format("Wrong answer (%d/%d)!", player.getTrails(),
                    _settings.getQuizDifficulty().getTrails()));

            if (player.getTrails() >= _settings.getQuizDifficulty().getTrails()) {
                Toast.makeText(this, getText(R.string.quiz_end_no_trails), Toast.LENGTH_LONG).show();
                showResult();
                return;
            }
        }

        if (QuizManager.getInstance().getQueueSize() == 1) {
            Toast.makeText(this, getText(R.string.quiz_end_last_question), Toast.LENGTH_LONG).show();
            return;
        }

        if (QuizManager.getInstance().getNextQuestion() == null) {
            showResult();
            return;
        }

        setData();

    }

    public void showResult(){
        Log.d("STAGE", "ROUND ENDED");
        startActivity(new Intent(this, Results.class));
        finish();
    }

    private void setData(){
        _settings = QuizManager.getInstance().getSettings();

        List<String> answers = new ArrayList<>();
        List <Button> buttons = new ArrayList<>();

        _question = QuizManager.getInstance().getNextQuestion();

        if(_question == null) return;


        buttons.add((Button) findViewById(R.id.btn_answer_a));
        buttons.add((Button) findViewById(R.id.btn_answer_b));
        buttons.add((Button) findViewById(R.id.btn_answer_c));
        buttons.add((Button) findViewById(R.id.btn_answer_d));

        TextView questionView = (TextView) findViewById(R.id.txt_question);
        questionView.setText(_question.getQuestion());

        Log.d("STAGE", String.format("Correct answer: %s", _question.getCorrectAnswer()));

        answers.add(_question.getCorrectAnswer());
        answers.add(_question.getAnswerB());
        answers.add(_question.getAnswerC());
        answers.add(_question.getAnswerD());

        while(!answers.isEmpty()){
            int r = Util.getRandomRange(0, buttons.size() - 1);
            Log.d("STAGE", String.format("Random=%d", r));

            while(buttons.get(0).getText() == answers.get(r)){
                r = Util.getRandomRange(0 , buttons.size() - 1);
                Log.d("STAGE", String.format("New random=%d answers=%d", r, answers.size()));
            }

            buttons.get(0).setText(answers.get(r));
            answers.remove(r);
            buttons.remove(0);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
