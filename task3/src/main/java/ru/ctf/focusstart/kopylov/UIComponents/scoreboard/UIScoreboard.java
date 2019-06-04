package ru.ctf.focusstart.kopylov.UIComponents.scoreboard;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.Scoreboard;
import ru.ctf.focusstart.kopylov.logic.game.GameManager;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UIScoreboard {
    private JFrame scoreboardFrame = new JFrame("Scoreboard");
    private GameManager gameManager;

    public UIScoreboard(GameManager gameManager) {
        this.gameManager = gameManager;
        scoreboardFrame.add(createLeaderboard());

        scoreboardFrame.setMinimumSize(new Dimension(200, 200));
        scoreboardFrame.pack();
        scoreboardFrame.dispose();
    }

    private JPanel createLeaderboard() {
        JPanel leaderboard = new JPanel();
        leaderboard.setOpaque(true);
        leaderboard.setBackground(Palette.BACKGROUND_COLOR);
        leaderboard.setBorder(new BorderUIResource.EmptyBorderUIResource(8,16,8,16));

        List<Integer> scoreList = new ArrayList<>();
        for (Double score : gameManager.getScoreboard().getScoreList()) {
            scoreList.add(score.intValue());
        }
        scoreList.sort(Comparator.comparingInt(Integer::intValue).reversed());
        for (int i = 0; i < (scoreList.size() < 10 ? scoreList.size() : 10); i++) {
            JLabel label = new JLabel((i + 1) + ": " + scoreList.get(i).toString());
            label.setForeground(Palette.DARK_TEXT_COLOR);
            leaderboard.add(label);
        }

        leaderboard.setLayout(new GridLayout((scoreList.size() < 10 ? scoreList.size() : 10), 1, 8, 4));

        return leaderboard;
    }

    public void open() {
        scoreboardFrame.setVisible(true);
    }
}
