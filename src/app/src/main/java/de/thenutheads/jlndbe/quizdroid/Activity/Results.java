package de.thenutheads.jlndbe.quizdroid.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.ErrorCode;
import de.thenutheads.jlndbe.quizdroid.Logic.Player;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManagerState;
import de.thenutheads.jlndbe.quizdroid.Logic.Settings;
import de.thenutheads.jlndbe.quizdroid.R;

public class Results extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(QuizManager.getState() != QuizManagerState.INITIALIZED){
            Toast.makeText(this, ErrorCode.QUIZ_MANAGER_NOT_INITIALIZED.toString(), Toast.LENGTH_LONG).show();
            App.delayedExit(ErrorCode.QUIZ_MANAGER_NOT_INITIALIZED.getValue());
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);
        setData();
    }


    private void setData(){
        TextView name = (TextView) findViewById(R.id.out_player_name),
                 score = (TextView) findViewById(R.id.out_score),
                 correctAnswers = (TextView) findViewById(R.id.out_correct_answers),
                 category = (TextView) findViewById(R.id.out_category),
                 difficulty = (TextView) findViewById(R.id.out_difficulty);

        Player player = QuizManager.getInstance().getActivePlayer();
        Settings settings = QuizManager.getInstance().getSettings();

        name.setText(player.getName());
        score.setText(String.format("%s",player.getScore()));
        correctAnswers.setText(String.format("%d/%d", player.getCorrectCount(),
            QuizManager.getInstance().getTotal()));

        category.setText(settings.getCategory().getLocalizedName());


        difficulty.setText(settings.getDifficulty().toString());

    }

    public void onClickOk(View view){
        finish();
    }
}
