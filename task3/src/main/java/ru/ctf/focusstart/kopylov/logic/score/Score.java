package ru.ctf.focusstart.kopylov.logic.score;

import ru.ctf.focusstart.kopylov.logic.FieldBuilder;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private static List<ScoreListener> listeners = new ArrayList<>();
    private static double score = 0;
    private static double multiplier = (double) FieldBuilder.getBombCount() / ((double) FieldBuilder.getHeight() * (double) FieldBuilder.getWidth()) * 10;

    public static void addListeners(ScoreListener listener) {
        listeners.add(listener);
    }

    public static void calculateScore(int time) {
        double difficulty;
        if (time <= 1) {
            difficulty = 5;
        } else {
//            difficulty = Math.sqrt(time / 60);
            difficulty = Math.sqrt((97 - time) / 1.5) - 3;
        }
        score += difficulty * multiplier;
        System.out.println("score: " + score);

        for (ScoreListener listener : listeners) {
            listener.changeScore((int) Math.floor(score));
        }
    }

    public static void reset() {
        score = 0;
        multiplier = (double) FieldBuilder.getBombCount() / ((double) FieldBuilder.getHeight() * (double) FieldBuilder.getWidth()) * 10;
    }

    public static double getScore() {
        return score;
    }
}
