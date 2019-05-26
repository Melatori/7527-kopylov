package ru.ctf.focusstart.kopylov.logic;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private static List<Double> scoreList = new ArrayList<>();

    static void addToBoard(double score) {
        scoreList.add(score);
    }

    public static List<Double> getScoreList() {
        return scoreList;
    }
}
