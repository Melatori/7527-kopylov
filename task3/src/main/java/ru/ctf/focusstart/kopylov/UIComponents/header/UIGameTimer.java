package ru.ctf.focusstart.kopylov.UIComponents.header;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.stopwatch.StopwatchListener;

import javax.swing.*;
import java.awt.*;

public class UIGameTimer implements StopwatchListener {
    private JLabel timer = new JLabel();

    UIGameTimer() {
        timer.setOpaque(true);
        timer.setBackground(Palette.HEADER_BACKGROUND_COLOR);
        timer.setForeground(Palette.TEXT_COLOR);
        timer.setPreferredSize(new Dimension(60,30));

        timer.setText(String.format("%03d : %02d", 0, 0));
    }

    @Override
    public void handleTimeChangedEvent(int time) {
        timer.setText(String.format("%03d : %02d", getMin(time), getSec(time)));
    }

    private int getMin(int time) {
        return time / 60;
    }

    private int getSec(int time) {
        return time - getMin(time) * 60;
    }

    JLabel getTimer() {
        return timer;
    }
}
