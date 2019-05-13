package ru.ctf.focusstart.kopylov.utils;

import ru.ctf.focusstart.kopylov.cells.BombCell;
import ru.ctf.focusstart.kopylov.cells.Cell;
import ru.ctf.focusstart.kopylov.cells.EmptyCell;

public class FieldBuilder {
    public static Cell[][] buildField(int height, int width, int bombCount) {
        Cell[][] field = new Cell[height][width];
        setMines(bombCount, field);
        setNums(field);
        return field;
    }

    private static void setMines(int bombCount, Cell[][] field) {
        for (int i = 0; i < bombCount; i++) {
            int a = (int) (Math.random() * field.length);
            int b = (int) (Math.random() * field[0].length);
            if (field[a][b] instanceof BombCell) {
                i--;
            } else {
                field[a][b] = new BombCell();
            }
        }
    }

    private static void setNums(Cell[][] field) {
        int x = field.length;
        int y = field[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (field[i][j] instanceof BombCell) {
                    continue;
                }

                field[i][j] = new EmptyCell();
                int fieldContent = field[i][j].getContent();

                if (isBomb(i - 1, j - 1, field)) {
                    field[i][j].setContent(++fieldContent);
                }
                if (isBomb(i - 1, j, field)) {
                    field[i][j].setContent(++fieldContent);
                }
                if (isBomb(i - 1, j + 1, field)) {
                    field[i][j].setContent(++fieldContent);
                }
                if (isBomb(i, j - 1, field)) {
                    field[i][j].setContent(++fieldContent);
                }
                if (isBomb(i, j + 1, field)) {
                    field[i][j].setContent(++fieldContent);
                }
                if (isBomb(i + 1, j - 1, field)) {
                    field[i][j].setContent(++fieldContent);
                }
                if (isBomb(i + 1, j, field)) {
                    field[i][j].setContent(++fieldContent);
                }
                if (isBomb(i + 1, j + 1, field)) {
                    field[i][j].setContent(++fieldContent);
                }
            }
        }
    }

    private static boolean isBomb(int x, int y, Cell[][] array) {
        int sizeX = array.length;
        int sizeY = array[0].length;

        if (x < 0) {
            return false;
        }
        if (x >= sizeX) {
            return false;
        }
        if (y < 0) {
            return false;
        }
        if (y >= sizeY) {
            return false;
        }
        return array[x][y] instanceof BombCell;
    }
}
