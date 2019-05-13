package ru.ctf.focusstart.kopylov.header;

import javax.swing.*;
import java.awt.*;

public class GameTimer {
    private JLabel timer = new JLabel();

    public GameTimer() {
//        timer.setBackground(Color.BLACK);
//        timer.setForeground(Color.WHITE);

        timer.setText(String.format("%3d : %2d", 0, 0));
    }

    public JLabel getTimer() {
        return timer;
    }
}
