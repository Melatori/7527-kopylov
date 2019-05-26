package ru.ctf.focusstart.kopylov.logic;

import ru.ctf.focusstart.kopylov.logic.cells.Cell;

public class FieldBuilder {
    private static int height = 10;
    private static int width = 10;
    private static int bombCount = 10;
    private static Cell[][] field;

    public static Cell[][] buildField() {
        int[][] numField = setNums(setMines());
        Cell[][] cellField = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cellField[i][j] = new Cell(numField[i][j]);
            }
        }
        field = cellField;
        return cellField;
    }

    private static int[][] setMines() {
        int[][] field = new int[FieldBuilder.height][FieldBuilder.width];

        for (int i = 0; i < bombCount; i++) {
            int a = (int) (Math.random() * field.length);
            int b = (int) (Math.random() * field[0].length);
            if (field[a][b] == -1) {
                i--;
            } else {
                field[a][b] = -1;
            }
        }

        return field;
    }

    private static int[][] setNums(int[][] field) {
        int x = field.length;
        int y = field[0].length;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (field[i][j] == -1) {
                    continue;
                }

                if (isBomb(i - 1, j - 1, field)) {
                    field[i][j]++;
                }
                if (isBomb(i - 1, j, field)) {
                    field[i][j]++;
                }
                if (isBomb(i - 1, j + 1, field)) {
                    field[i][j]++;
                }
                if (isBomb(i, j - 1, field)) {
                    field[i][j]++;
                }
                if (isBomb(i, j + 1, field)) {
                    field[i][j]++;
                }
                if (isBomb(i + 1, j - 1, field)) {
                    field[i][j]++;
                }
                if (isBomb(i + 1, j, field)) {
                    field[i][j]++;
                }
                if (isBomb(i + 1, j + 1, field)) {
                    field[i][j]++;
                }
            }
        }

        return field;
    }

    private static boolean isBomb(int x, int y, int[][] array) {
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
        return array[x][y] == -1;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static int getBombCount() {
        return bombCount;
    }

    public static boolean setField(int height, int width, int bombCount) {
        if (height < 3) {
            return false;
        }
        if (width < 3) {
            return false;
        }
        if (bombCount < 1) {
            return false;
        }
        if (bombCount >= width * height) {
            return false;
        }
        FieldBuilder.height = height;
        FieldBuilder.width = width;
        FieldBuilder.bombCount = bombCount;
        return true;
    }

    static Cell getCell(int x, int y) {
        return field[x][y];
    }
}
