package de.thenutheads.jlndbe.quizdroid.Logic;

/**
 * Created by Jlndbe on 24.01.2016.
 */

public class QuizSettings {

    private QuizDifficulty _difficultyPreset;
    private QuizCategory _quizCategory;
    private QuizLength _quizLengthPreset;
    private QuizMode _quizMode;

    private int _customDifficulty = 1;
    private boolean _noSpan = false;

    /**
     * Default Konstruktor
     * @param difficulty
     * @param category
     */
    public QuizSettings(QuizDifficulty difficulty, QuizCategory category, QuizLength length, QuizMode mode) {
        _quizLengthPreset = length;
        _quizMode = mode;
        _difficultyPreset = difficulty;

        _quizCategory = category;
    }

    public QuizCategory getQuizCategory(){
        return _quizCategory;
    }

    /**
     * Git einen Integer zur&uuml;ck. Der Wert beschreibt die Schwierigkeit.
     * Der zur&uuml;ckgegebene Wert dient als Basis f&uuml;r die auswahl der Fragen.
     * Die Schwierigkeitsstufe geht von 1 bis 5.
     *
     *  Einfach: 1
     *  Normal: 2-3
     *  Schwer: 4-5
     *
     *  Fragen mit einem geringeren als dem gew&auml;hlten Schwierigkeitsgrad werden ebenfalls gew&auml;hlt.
     *  Das Level bestimmt die obere Grenze.
     *
     *  Schwerere Fragen geben einen Punkte Multiplikator.
     *
     * @return Returns difficulty Integer Array.
     */
    public QuizDifficulty getQuizDifficulty() {
        return _difficultyPreset;
    }
}
