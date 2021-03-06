package ru.ctf.focusstart.kopylov.logic.field;

import ru.ctf.focusstart.kopylov.logic.cells.Cell;
import ru.ctf.focusstart.kopylov.logic.game.GameManager;

public class Field {
    private int height = 10;
    private int width = 10;
    private int bombCount = 10;
    private Cell[][] cells;
    private int bombMarked;
    private int emptyCellsMarked;
    private GameManager gameManager;

    public Field(GameManager gameManager) {
        this.gameManager = gameManager;
        cells = FieldBuilder.buildField(height, width, bombCount);
    }

    public void resetField() {
        bombMarked = 0;
        emptyCellsMarked = 0;
        cells = FieldBuilder.buildField(height, width, bombCount);
    }

    public void openCell(int x, int y) {
        gameManager.startStopwatch();
        Cell.CellAction action = cells[x][y].open();
        switch (action) {
            case CELL_OPEN:
                gameManager.updateScore();
                break;
            case EMPTY_CELL_OPEN:
                openNearCells(x, y, true);
                break;
            case BOOM:
                gameManager.handleLose();
                break;
        }
    }

    public void markCell(int x, int y) {
        gameManager.startStopwatch();
        Cell.CellAction action = cells[x][y].mark();
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
        if (isWin()) {
            gameManager.handleVictory();
        }
    }

    public void openField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].open() == Cell.CellAction.CELL_OPEN) {
                    gameManager.updateScore();
                }
            }
        }
    }

    public void setNewParams(int height, int width, int bombCount) {
        this.height = height;
        this.width = width;
        this.bombCount = bombCount;
    }

    private void openNearCells(int x, int y, boolean isStartOpening) {
        if (!isStartOpening) {
            Cell.CellAction cellAction = cells[x][y].open();
            if (cellAction == Cell.CellAction.CELL_OPEN) {
                gameManager.updateScore();
                return;
            }

            if (cellAction != Cell.CellAction.EMPTY_CELL_OPEN) {
                return;
            }
        }

        if (isCoordinatesAvailable(x - 1, y - 1)) {
            openNearCells(x - 1, y - 1, false);
        }
        if (isCoordinatesAvailable(x - 1, y)) {
            openNearCells(x - 1, y, false);
        }
        if (isCoordinatesAvailable(x - 1, y + 1)) {
            openNearCells(x - 1, y + 1, false);
        }
        if (isCoordinatesAvailable(x, y - 1)) {
            openNearCells(x, y - 1, false);
        }
        if (isCoordinatesAvailable(x, y + 1)) {
            openNearCells(x, y + 1, false);
        }
        if (isCoordinatesAvailable(x + 1, y - 1)) {
            openNearCells(x + 1, y - 1, false);
        }
        if (isCoordinatesAvailable(x + 1, y)) {
            openNearCells(x + 1, y, false);
        }
        if (isCoordinatesAvailable(x + 1, y + 1)) {
            openNearCells(x + 1, y + 1, false);
        }
    }

    private boolean isWin() {
        return ((bombMarked == bombCount) && (emptyCellsMarked == 0));
    }

    private boolean isCoordinatesAvailable(int x, int y) {
        return (x > -1 && x < height && y > -1 && y < width);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getBombCount() {
        return bombCount;
    }
}
