package de.thenutheads.jlndbe.quizdroid;

import java.util.Random;

/**
 * Created by Jlndbe on 24.01.2016.
 */
public class Util {
    public static int getRandomRange(int min, int max){
        return (new Random()).nextInt(max - min + 1) + min;
    }
}
