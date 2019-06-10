package ru.ctf.focusstart.kopylov.logic.score;

import ru.ctf.focusstart.kopylov.logic.field.Field;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private List<ScoreListener> listeners = new ArrayList<>();
    private double score = 0;
    private double multiplier;
    private int bombCount;
    private int square;
    private Field field;

    public Score(Field field) {
        this.field = field;
        bombCount = field.getBombCount();
        square = field.getHeight() * field.getWidth();
        multiplier = (double) bombCount / square * 10;
    }

    public void addListeners(ScoreListener listener) {
        listeners.add(listener);
    }

    public void calculateScore(int time) {
        double difficulty;
        if (time <= 1) {
            difficulty = 5;
        } else {
            difficulty = Math.sqrt((97 - time) / 1.5) - 3;
        }
        score += difficulty * multiplier;

        for (ScoreListener listener : listeners) {
            listener.handleChangeScoreEvent((int) Math.floor(score));
        }
    }

    public void reset() {
        score = 0;
        bombCount = field.getBombCount();
        square = field.getHeight() * field.getWidth();
        multiplier = (double) bombCount / square * 10;
    }

    public double getScore() {
        return score;
    }
}
