package de.thenutheads.jlndbe.quizdroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.ErrorCode;
import de.thenutheads.jlndbe.quizdroid.Logic.Player;
import de.thenutheads.jlndbe.quizdroid.Logic.Question;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManagerState;
import de.thenutheads.jlndbe.quizdroid.Logic.Settings;
import de.thenutheads.jlndbe.quizdroid.R;
import de.thenutheads.jlndbe.quizdroid.Util;

public class Stage extends Activity {

    private final String DEBUG_LOG_TAG = "QDStage";

    private Settings _settings;
    private Question _question;

    private Button[] _buttons;

    private TextView _questionView;
    private TextView _triesView;

    private ProgressBar _quizProgress;

    private QuizManager _qM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(QuizManager.getState() != QuizManagerState.INITIALIZED){
            Toast.makeText(this, ErrorCode.QUIZ_MANAGER_NOT_INITIALIZED.toString(), Toast.LENGTH_LONG).show();
            App.delayedExit(ErrorCode.QUIZ_MANAGER_NOT_INITIALIZED.getValue());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_stage);

        _qM = QuizManager.getInstance();

        _settings = QuizManager.getInstance().getSettings();

        _questionView = (TextView) findViewById(R.id.txt_question);
        _triesView = (TextView) findViewById(R.id.txt_tries);

        _buttons = new Button[4];

        _buttons[0] = (Button) findViewById(R.id.btn_answer_a);
        _buttons[1] = (Button) findViewById(R.id.btn_answer_b);
        _buttons[2] = (Button) findViewById(R.id.btn_answer_c);
        _buttons[3] = (Button) findViewById(R.id.btn_answer_d);

        _quizProgress = (ProgressBar) findViewById(R.id.quiz_progress);

        _quizProgress.setMax(QuizManager.getInstance().getTotal());
        _quizProgress.setProgress(0);

        setData();
    }

    public void onClick_SkipQuestion(View view){
        Toast.makeText(this, getString(R.string.stage_question_skipped), Toast.LENGTH_LONG).show();

        Player player = _qM.getActivePlayer();
        player.addTry();

        if(_qM.isQueueEmpty() || player.getTries() == _settings.getDifficulty().getTrails()){
            showResult();
        } else {
            setData();
        }
    }

    public void onClick_Answer(View view){
        Player player = _qM.getActivePlayer();

        if(((Button) view).getText().equals(_question.getCorrectAnswer())){

            player.addCorrect();
            player.addScore(QuizManager.SCORE_BASE * _settings.getDifficulty().getValue());
        }
        else {
            player.addTry();

            if(player.getTries() == _settings.getDifficulty().getTrails()){
                showResult();
            }
        }

        if(_qM.isQueueEmpty()){
            showResult();
        } else {
            setData();
        }
    }

    public void showResult(){
        Log.d(DEBUG_LOG_TAG, "ROUND ENDED");
        startActivity(new Intent(this, Results.class));
        finish();
    }

    private void setData(){
        _question = _qM.getNextQuestion();
        _quizProgress.setProgress(_qM.getCurrent());

        Log.d(DEBUG_LOG_TAG, Arrays.toString(_question.getAnswers()));

        Player player = _qM.getActivePlayer();
        _triesView.setText(String.valueOf(_settings.getDifficulty().getTrails() - player.getTries()));


        List<String> answers = new ArrayList<>(Arrays.asList(_question.getAnswers()));
        List<Button> buttons = new ArrayList<>(Arrays.asList(_buttons));

        _questionView.setText(_question.getQuestion());

        Log.d(DEBUG_LOG_TAG, String.format("Correct answer: %s", _question.getCorrectAnswer()));

        while(!buttons.isEmpty()){
            int r = Util.getRandomRange(0, answers.size() - 1);
            Log.d(DEBUG_LOG_TAG, String.format("Random=%d", r));

            while(buttons.get(0).getText() == answers.get(r)){
                r = Util.getRandomRange(0 , answers.size() - 1);
                Log.d(DEBUG_LOG_TAG, String.format("New random=%d answers=%d", r, answers.size()));
            }

            buttons.get(0).setText(answers.get(r));
            answers.remove(r);
            buttons.remove(0);
        }

    }
}
