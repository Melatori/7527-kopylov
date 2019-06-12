package ru.ctf.focusstart.kopylov.part1;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
    private static final int TO_N = 300000;

    public static void main(String[] args) {
        List<Integer> initialList = DataPreparer.getNumsFromOneToN(TO_N);

        int n = Runtime.getRuntime().availableProcessors();

        List<List<Integer>> preparedList = DataPreparer.divideToNArrays(n, initialList);

        List<Task> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tasks.add(new Task(preparedList.get(i)));
            threads.add(new Thread(tasks.get(i)));

            threads.get(i).start();
        }

        try {
            for (Thread thread: threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int sum = 0;

        for (List<Integer> data : preparedList) {
            for (Integer a : data) {
                sum += a;
            }
        }

        System.out.println("Sum: " + sum);
    }
}
