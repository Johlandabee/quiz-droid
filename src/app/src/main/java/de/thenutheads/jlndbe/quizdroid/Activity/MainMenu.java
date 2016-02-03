package de.thenutheads.jlndbe.quizdroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.ErrorCode;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManagerState;
import de.thenutheads.jlndbe.quizdroid.Logic.Mode;
import de.thenutheads.jlndbe.quizdroid.R;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(QuizManager.getState() != QuizManagerState.INITIALIZED){
            Toast.makeText(this, ErrorCode.QUIZ_MANAGER_NOT_INITIALIZED.toString(), Toast.LENGTH_LONG).show();
            App.delayedExit(ErrorCode.QUIZ_MANAGER_NOT_INITIALIZED.getValue());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    public void onClick_Singleplayer(View view){
        QuizManager.getInstance().setMode(Mode.SINGLEPLAYER);
        startActivity(new Intent(this, Setup.class));
    }

    public void onClick_Versus(View view){
        QuizManager.getInstance().setMode(Mode.VERSUS);

    }
}
