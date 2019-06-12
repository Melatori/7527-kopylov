package ru.ctf.focusstart.kopylov.part1;

class ComplexMathThings {
    private ComplexMathThings() {

    }

    static int makeVeryComplexCalculation(int number) {
        return fib(number);
    }

    private static int fib(int a) {
        if (a == 0) {
            return 0;
        }
        if (a < 0) {
            throw new IllegalArgumentException();
        }
        int prev = 0;
        int cur = 1;
        for (int i = 1; i < a; i++) {
            int tmp = cur;
            cur = prev + cur;
            prev = tmp;
        }

        return cur;
    }
}
