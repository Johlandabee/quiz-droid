package de.thenutheads.jlndbe.quizdroid.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import de.thenutheads.jlndbe.quizdroid.Logic.Player;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizSettings;
import de.thenutheads.jlndbe.quizdroid.R;

public class Results extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        QuizSettings settings = QuizManager.getInstance().getSettings();

        name.setText(player.getName());
        score.setText(String.format("%s",player.getScore()));
        correctAnswers.setText(String.format("%d/%d", player.getCorrectCount(),
                settings.getQuizLength().getValue()));

        category.setText(settings.getQuizCategory().getLocalizedName());


        difficulty.setText(settings.getQuizDifficulty().toString());

    }

    public void onClickOk(View view){
        finish();
    }
}
