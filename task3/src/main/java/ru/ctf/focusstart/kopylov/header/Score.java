package ru.ctf.focusstart.kopylov.header;

import javax.swing.*;
import java.awt.*;

public class Score {
    private JLabel score = new JLabel();

    public Score() {
//        score.setBackground(Color.BLACK);
//        score.setForeground(Color.WHITE);

        score.setText(String.format("%3d", 0));
    }

    public JLabel getScore() {
        return score;
    }
}
