package ru.ctf.focusstart.kopylov.logic;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private  List<Double> scoreList = new ArrayList<>();

    public void addToBoard(double score) {
        scoreList.add(score);
    }

    public List<Double> getScoreList() {
        return scoreList;
    }
}
