package de.thenutheads.jlndbe.quizdroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class QuizStage extends Activity {

    private QuizSettings _settings;
    private Question _question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_stage);
       // setData();
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
        //answers.add(_question.getAnswerB());
        //answers.add(_question.getAnswerC());
        //answers.add(_question.getAnswerD());

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
}
