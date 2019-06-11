package ru.ctf.focusstart.kopylov.logic.game;

import ru.ctf.focusstart.kopylov.logic.Scoreboard;
import ru.ctf.focusstart.kopylov.logic.field.Field;
import ru.ctf.focusstart.kopylov.logic.score.Score;
import ru.ctf.focusstart.kopylov.logic.score.ScoreListener;
import ru.ctf.focusstart.kopylov.logic.stopwatch.Stopwatch;
import ru.ctf.focusstart.kopylov.logic.stopwatch.StopwatchListener;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<GameStateListener> gameStateListeners = new ArrayList<>();
    private List<GameRestartListener> gameRestartListeners = new ArrayList<>();
    private List<GameStartListener> gameStartListeners = new ArrayList<>();
    private Field field = new Field(this);
    private Score score = new Score(field);
    private Scoreboard scoreboard = new Scoreboard();
    private final Stopwatch stopwatch = new Stopwatch();


    public void addGameStateListener(GameStateListener listener) {
        gameStateListeners.add(listener);
    }

    public void addGameRestartListener(GameRestartListener listener) {
        gameRestartListeners.add(listener);
    }

    public void addGameStartListener(GameStartListener listener) {
        gameStartListeners.add(listener);
    }

    public void newGame() {
        for (GameStartListener listener : gameStartListeners) {
            listener.handleNewGameEvent();
        }
    }

    public void restartGame() {
        field.resetField();
        newGame();

        for (GameRestartListener listener : gameRestartListeners) {
            listener.handleRestartGameEvent();
        }
        stopwatch.reset();
        score.reset();
    }

    public void restartGame(int height, int width, int bombCount) {
        field.setNewParams(height, width, bombCount);
        field.resetField();
        newGame();

        for (GameRestartListener listener : gameRestartListeners) {
            listener.handleRestartGameEvent();
        }
        stopwatch.reset();
        score.reset();
    }

    public void handleVictory() {
        field.openField();
        stopwatch.stop();
        scoreboard.addToBoard(score.getScore());
        for (GameStateListener listener : gameStateListeners) {
            listener.handleWinGameEvent();
        }
    }

    public void handleLose() {
        field.openField();
        stopwatch.stop();
        for (GameStateListener listener : gameStateListeners) {
            listener.handleLoseGameEvent();
        }
    }

    public void startStopwatch() {
        stopwatch.stop();
    }
    public Field getField() {
        return field;
    }

    public int getFieldHeight() {
        return field.getHeight();
    }

    public int getFieldWidth() {
        return field.getWidth();
    }

    public int getFieldBombCount() {
        return field.getBombCount();
    }

    public void addScoreListener(ScoreListener listener) {
        score.addListeners(listener);
    }

    public void addStopwatchListener(StopwatchListener listener) {
        stopwatch.addListeners(listener);
    }

    public void updateScore() {
        score.calculateScore(stopwatch.getTimeSec());
    }

    public List<Double> getScoreboardList() {
        return scoreboard.getScoreList();
    }
}
