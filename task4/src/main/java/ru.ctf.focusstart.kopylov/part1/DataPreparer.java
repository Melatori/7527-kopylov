package ru.ctf.focusstart.kopylov.part1;

import java.util.ArrayList;
import java.util.List;

class DataPreparer {
    private DataPreparer() {

    }

    static List<List<Integer>> divideToNArrays(int n, List<Integer> list) {
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            lists.add(new ArrayList<>());
        }

        while (list.size() > 0) {
            lists.get(Math.floorMod(list.size(), n)).add(list.remove(0));
        }

        return lists;
    }

    static ArrayList<Integer> getNumsFromOneToN(int n) {
        ArrayList <Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(i + 1);
        }
        return a;
    }
}
