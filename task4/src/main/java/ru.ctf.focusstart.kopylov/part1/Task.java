package ru.ctf.focusstart.kopylov.part1;

import java.util.List;

public class Task implements Runnable{
    private List<Integer> list;

    Task(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ComplexMathThings.makeVeryComplexCalculation(list.get(i)));
        }
    }
}
