package ru.ctf.focusstart.kopylov.UIComponents.header;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.score.ScoreListener;

import javax.swing.*;
import java.awt.*;

class UIScore implements ScoreListener {
    private JLabel score = new JLabel();

    UIScore() {
        score.setOpaque(true);
        score.setBackground(Palette.HEADER_BACKGROUND_COLOR);
        score.setForeground(Palette.TEXT_COLOR);
        score.setPreferredSize(new Dimension(40,30));

        score.setText(String.format("%3d", 0));
    }

    @Override
    public void handleChangeScoreEvent(int score) {
        this.score.setText(String.format("%4d", score));
    }

    JLabel getScore() {
        return score;
    }
}
