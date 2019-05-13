package ru.ctf.focusstart.kopylov.mainwindow;

import ru.ctf.focusstart.kopylov.gamefield.GameField;
import ru.ctf.focusstart.kopylov.header.Header;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private JFrame frame;
    public MainWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setMenu();
        fillFrame();

        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(460,460);
    }

    private void setMenu() {
        Menu headMenu = new Menu();

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(headMenu.getMenu());

        frame.setJMenuBar(jMenuBar);
    }

    private void fillFrame() {
        frame.setLayout(new BorderLayout());

        Header header = new Header();
        GameField game = new GameField();

        frame.add(header.getHead(), BorderLayout.NORTH);
        frame.add(game.getField(), BorderLayout.CENTER);
    }
}
