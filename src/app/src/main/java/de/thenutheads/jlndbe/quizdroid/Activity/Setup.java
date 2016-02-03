package de.thenutheads.jlndbe.quizdroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import de.thenutheads.jlndbe.quizdroid.App;
import de.thenutheads.jlndbe.quizdroid.ErrorCode;
import de.thenutheads.jlndbe.quizdroid.Logic.Category;
import de.thenutheads.jlndbe.quizdroid.Logic.Difficulty;
import de.thenutheads.jlndbe.quizdroid.Logic.Length;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManager;
import de.thenutheads.jlndbe.quizdroid.Logic.QuizManagerState;
import de.thenutheads.jlndbe.quizdroid.Logic.Settings;
import de.thenutheads.jlndbe.quizdroid.R;

public class Setup extends Activity {

    private Spinner _category;
    private Spinner _difficulty;
    private Spinner _length;

    private ArrayAdapter<Category> _cAdapter;
    private ArrayAdapter<Difficulty> _dAdapter;
    private ArrayAdapter<Length> _lAdapter;

    private Settings _qSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(QuizManager.getState() != QuizManagerState.INITIALIZED){
            Toast.makeText(this, ErrorCode.QUIZ_MANAGER_NOT_INITIALIZED.toString(), Toast.LENGTH_LONG).show();
            App.delayedExit(ErrorCode.QUIZ_MANAGER_NOT_INITIALIZED.getValue());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_setup);

        setup();
    }

    private void setup(){

        _category = (Spinner) findViewById(R.id.sp_category);
        _difficulty = (Spinner) findViewById(R.id.sp_difficulty);
        _length = (Spinner) findViewById(R.id.sp_length);

        _cAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                QuizManager.getInstance().getCategoryList());
        _dAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Difficulty.values());
        _lAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Length.values());

        _category.setAdapter(_cAdapter);
        _difficulty.setAdapter(_dAdapter);
        _length.setAdapter(_lAdapter);
    }

    public void onClick_StartQuiz(View view){
        if(QuizManager.getInstance().start(new Settings(
                (Difficulty)_difficulty.getSelectedItem(),
                (Category)_category.getSelectedItem(),
                (Length)_length.getSelectedItem()
        )) == 0){
            Toast.makeText(this, getString(R.string.setup_no_matching_data), Toast.LENGTH_LONG).show();
            return;
        }

        startActivity(new Intent(this, Stage.class));
    }
}
