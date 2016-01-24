package de.thenutheads.jlndbe.quizdroid.Logic;

/**
 * Created by Jlndbe on 24.01.2016.
 */

public class QuizSettings {

    private QuizDifficulty _difficultyPreset;
    private QuizCategory _quizCategory;

    private int _customDifficulty = 1;
    private boolean _noSpan = false;

    /**
     * Default Konstruktor
     * @param difficulty
     * @param category
     */
    public QuizSettings(QuizDifficulty difficulty, QuizCategory category) {
        if(difficulty == QuizDifficulty.CUSTOM)
            _difficultyPreset = QuizDifficulty.EASY;
        else
            _difficultyPreset = difficulty;

        _quizCategory = category;
    }

    /**
     * Konstruktor mit erweiterten Einstellungsmöglichkeiten
     * @param category
     * @param customDifficulty
     * @param noSpan
     */
    public QuizSettings(QuizCategory category, int customDifficulty, boolean noSpan){
        _difficultyPreset = QuizDifficulty.CUSTOM;

        if(customDifficulty > 5)
            _customDifficulty = 5;
        else
            _customDifficulty = customDifficulty;

        _noSpan = noSpan;
    }

    public QuizCategory getQuizCategory(){
        return _quizCategory;
    }

    /**
     * Git ein Integer Array zur&uuml;ck. Der erste Wert beschreibt die Schwierigkeit.
     * Zweiter ob nur Fragen von dem explizit gew&auml;hltem Schwierigkeitslevel gestellt werden.
     * Der zur&uuml;ckgegebene Wert dient als Basis f&uuml;r die auswahl der Fragen.
     *  Die Schwierigkeitsstufe geht von 1 bis 5.
     *
     *  Einfach: 1
     *  Normal: 2-3
     *  Schwer: 4-5
     *
     *  Fragen mit einem geringeren als dem gew&auml;hlten Schwierigkeitsgrad werden ebenfalls gew&auml;hlt.
     *  (Wenn _noSpan nicht gesetzt wurde!)
     *  Das Level bestimmt die obere Grenze.
     *
     *  Schwerere Fragen geben einen Punkte Multiplikator.
     *
     *
     * @return Returns difficulty Integer Array.
     */
    public int[] getQuizDifficulty(){

        int[] value = {0,0};

        switch (_difficultyPreset){
            case EASY:
                value = new int[]{1,0};
                break;
            case NORMAL:
                value = new int[]{3,0};
                break;
            case HARD:
                value = new int[]{5,0};
                break;
            case CUSTOM:
                value = new int[]{_customDifficulty, (_noSpan) ? 1 : 0};
                break;
        }

        return value;
    }
}
