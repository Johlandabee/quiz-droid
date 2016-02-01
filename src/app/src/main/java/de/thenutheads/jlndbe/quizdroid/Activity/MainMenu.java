package de.thenutheads.jlndbe.quizdroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.thenutheads.jlndbe.quizdroid.Logic.QuizCategory;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizDifficulty;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizLength;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizMode;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizSettings;
import de.thenutheads.jlndbe.quizdroid.R;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    public void DEBUG_onClickMethod(View view){

        QuizSettings settings = new QuizSettings(
                QuizDifficulty.EASY,
                new QuizCategory(1, "category_history"),
                QuizLength.MEDIUM,
                QuizMode.SINGLEPLAYER
        );

        QuizManager.getInstance().start(settings);
        startActivity(new Intent(this, QuizStage.class));
    }
}
