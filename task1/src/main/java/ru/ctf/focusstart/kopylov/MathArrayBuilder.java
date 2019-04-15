package ru.ctf.focusstart.kopylov;

public class MathArrayBuilder {

    public int[][] buildMulArray(int size) {
        int[][] array = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = (i+1) * (j+1);
            }
        }

        return array;
    }
}
