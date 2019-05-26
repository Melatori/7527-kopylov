package ru.ctf.focusstart.kopylov.UIComponents.header;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.Game;
import ru.ctf.focusstart.kopylov.logic.score.Score;

import javax.swing.*;
import java.awt.*;

public class UIHeader {
    private JPanel head = new JPanel();

    public UIHeader() {
        UIGameTimer timer = new UIGameTimer();
        UIScore score = new UIScore();
        UINewGameButton newGame = new UINewGameButton();

        Game.getStopwatch().addListeners(timer);
        Score.addListeners(score);

        head.setBackground(Palette.getHeadColor());
        head.setBorder(BorderFactory.createLineBorder(Palette.getHeaderBackgroundColor(), 5));
        head.setPreferredSize(new Dimension(20,40));

        head.setLayout(new GridLayout(1,3));
        head.setLayout(new BorderLayout());
        head.add(timer.getTimer(), BorderLayout.WEST);
        head.add(newGame.getButton(), BorderLayout.CENTER);
        head.add(score.getScore(), BorderLayout.EAST);
    }

    public JPanel getHead() {
        return head;
    }
}
