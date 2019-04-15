package ru.ctf.focusstart.kopylov;

//зачем-то предлагает уменьшить уровень доступа public, зачем?
public class MathArrayBuilder {

    public MathArrayBuilder() {
    }

    public int[][] BuildMulArray(int size) {
        int[][] array = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = (i+1) * (j+1);
            }
        }

        return array;
    }
}
