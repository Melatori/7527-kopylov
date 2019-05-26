package ru.ctf.focusstart.kopylov.logic;

import ru.ctf.focusstart.kopylov.UIComponents.mainwindow.UIMainWindow;
import ru.ctf.focusstart.kopylov.logic.cells.Cell;
import ru.ctf.focusstart.kopylov.logic.score.Score;
import ru.ctf.focusstart.kopylov.logic.stopwatch.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static List<GameListener> listenerList = new ArrayList<>();
    private static int bombMarked;
    private static int emptyCellsMarked;
    private static Stopwatch stopwatch = new Stopwatch();

    public static void addListener(GameListener listener) {
        listenerList.add(listener);
    }

    public static void newGame() {
        bombMarked = 0;
        emptyCellsMarked = 0;
        for (GameListener field : listenerList) {
            field.createNewGame();
        }
    }

    public static void restartGame() {
        bombMarked = 0;
        emptyCellsMarked = 0;
        for (GameListener field : listenerList) {
            field.createNewGame();
        }
        UIMainWindow.refreshGameField();
        stopwatch.reset();
        Score.reset();
    }

    public static void reactOnOpenCell(int x, int y) {
        stopwatch.start();
        Cell.CellAction action = FieldBuilder.getCell(x, y).open();
        switch (action) {
            case CELL_OPEN:
                Score.calculateScore(stopwatch.getTimeSec());
                break;
            case EMPTY_CELL_OPEN:
                openNearCells(x, y);
                break;
            case BOOM:
                lose();
                break;
        }
    }

    public static void reactOnMarkCell(int x, int y) {
        stopwatch.start();
        Cell.CellAction action = FieldBuilder.getCell(x, y).mark();
        switch (action) {
            case CELL_MARKED:
                emptyCellsMarked++;
                break;
            case BOMB_MARKED:
                bombMarked++;
                break;
            case CELL_UNMARKED:
                emptyCellsMarked--;
                break;
            case BOMB_UNMARKED:
                bombMarked--;
                break;
        }
        System.out.println("bomb marked: " + bombMarked + " empty cell marked: " + emptyCellsMarked);
        if (isWin()) {
            victory();
        }
    }

    private static void openNearCells(int x, int y) {
        if (FieldBuilder.getCell(x, y).open() == Cell.CellAction.CELL_OPEN) {
            Score.calculateScore(stopwatch.getTimeSec());
            return;
        }

        if (isCoordinatesAvailable(x - 1, y - 1)) {
            openNearCells(x - 1, y - 1);
        }
        if (isCoordinatesAvailable(x - 1, y)) {
            openNearCells(x - 1, y);
        }
        if (isCoordinatesAvailable(x - 1, y + 1)) {
            openNearCells(x - 1, y + 1);
        }
        if (isCoordinatesAvailable(x, y - 1)) {
            openNearCells(x, y - 1);
        }
        if (isCoordinatesAvailable(x, y + 1)) {
            openNearCells(x, y + 1);
        }
        if (isCoordinatesAvailable(x + 1, y - 1)) {
            openNearCells(x + 1, y - 1);
        }
        if (isCoordinatesAvailable(x + 1, y)) {
            openNearCells(x + 1, y);
        }
        if (isCoordinatesAvailable(x + 1, y + 1)) {
            openNearCells(x + 1, y + 1);
        }
    }

    private static void victory() {
        openField();
        stopwatch.stop();
        Scoreboard.addToBoard(Score.getScore());
        System.out.println("Victory!!!");
    }

    private static void lose() {
        openField();
        stopwatch.stop();
        System.out.println("Lose");
    }

    private static void openField() {
        for (int i = 0; i < FieldBuilder.getHeight(); i++) {
            for (int j = 0; j < FieldBuilder.getWidth(); j++) {
                if (FieldBuilder.getCell(i, j).open() == Cell.CellAction.CELL_OPEN) {
                    Score.calculateScore(stopwatch.getTimeSec());
                }
            }
        }
    }

    private static boolean isCoordinatesAvailable(int x, int y) {
        return (x > -1 && x < FieldBuilder.getHeight() && y > -1 && y < FieldBuilder.getWidth() && !FieldBuilder.getCell(x, y).IS_OPEN && !FieldBuilder.getCell(x, y).IS_MARKED);
    }

    private static boolean isWin() {
        return ((bombMarked == FieldBuilder.getBombCount()) && (emptyCellsMarked == 0));
    }

    public static Stopwatch getStopwatch() {
        return stopwatch;
    }
}
