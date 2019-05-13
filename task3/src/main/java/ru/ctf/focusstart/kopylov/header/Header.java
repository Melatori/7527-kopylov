package ru.ctf.focusstart.kopylov.header;

import javax.swing.*;
import java.awt.*;

public class Header {
    private JPanel head = new JPanel();

    public Header() {
        GameTimer timer = new GameTimer();
        Score score = new Score();
        NewGameButton newGame = new NewGameButton();

        head.setLayout(new GridLayout(1,3));

        head.add(timer.getTimer());
        head.add(newGame.getButton());
        head.add(score.getScore());

        /*head.setLayout(new BorderLayout());

        head.add(timer.getTimer(), BorderLayout.WEST);
        head.add(newGame.getButton(), BorderLayout.CENTER);
        head.add(score.getScore(), BorderLayout.EAST);*/
    }

    public JPanel getHead() {
        return head;
    }
}
