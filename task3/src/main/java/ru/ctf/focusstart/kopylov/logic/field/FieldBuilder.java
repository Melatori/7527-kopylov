package ru.ctf.focusstart.kopylov.logic.field;

import ru.ctf.focusstart.kopylov.logic.cells.Cell;

class FieldBuilder {
    static Cell[][] buildField(int height, int width, int bombCount) {
        if (!isCorrectField(height, width, bombCount)) {
            throw new IllegalArgumentException();
        }
        int[][] numField = setNums(setMines(new int[height][width], bombCount));
        Cell[][] cellField = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cellField[i][j] = new Cell(numField[i][j]);
            }
        }
        return cellField;
    }

    private static int[][] setMines(int[][] field, int bombCount) {
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

    private static boolean isCorrectField(int height, int width, int bombCount) {
        if (height < 3) {
            return false;
        }
        if (width < 3) {
            return false;
        }
        if (bombCount < 1) {
            return false;
        }
        return bombCount < width * height;
    }
}
