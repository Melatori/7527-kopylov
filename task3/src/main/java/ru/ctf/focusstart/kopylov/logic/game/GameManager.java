package ru.ctf.focusstart.kopylov.logic.game;

import ru.ctf.focusstart.kopylov.logic.Scoreboard;
import ru.ctf.focusstart.kopylov.logic.field.Field;
import ru.ctf.focusstart.kopylov.logic.score.Score;
import ru.ctf.focusstart.kopylov.logic.stopwatch.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<GameStateListener> gameStateListeners = new ArrayList<>();
    private List<GameRestartListener> gameRestartListeners = new ArrayList<>();
    private Field field = new Field(this);
    private Score score = new Score(field);
    private Scoreboard scoreboard = new Scoreboard();
    public final Stopwatch STOPWATCH = new Stopwatch();


    public void addGameStateListener(GameStateListener listener) {
        gameStateListeners.add(listener);
    }

    public void addGameRestartListener(GameRestartListener listener) {
        gameRestartListeners.add(listener);
    }

    public void newGame() {
        field.start();
    }

    public void restartGame() {
        field.changeField();
        field.start();

        for (GameRestartListener listener : gameRestartListeners) {
            listener.handleRestartGame();
        }
        STOPWATCH.reset();
        score.reset();
    }

    public void restartGame(int height, int width, int bombCount) {
        field.setNewParams(height, width, bombCount);
        field.changeField();
        field.start();

        for (GameRestartListener listener : gameRestartListeners) {
            listener.handleRestartGame();
        }
        STOPWATCH.reset();
        score.reset();
    }

    public void handleVictory() {
        field.openField();
        STOPWATCH.stop();
        scoreboard.addToBoard(score.getScore());
        for (GameStateListener listener : gameStateListeners) {
            listener.handleWinGame();
        }
    }

    public void handleLose() {
        field.openField();
        STOPWATCH.stop();
        for (GameStateListener listener : gameStateListeners) {
            listener.handleLoseGame();
        }
    }

    public Field getField() {
        return field;
    }

    public Score getScore() {
        return score;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
