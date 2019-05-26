package ru.ctf.focusstart.kopylov.UIComponents.mainwindow;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.UIComponents.field.UIGameField;
import ru.ctf.focusstart.kopylov.UIComponents.header.UIHeader;
import ru.ctf.focusstart.kopylov.logic.FieldBuilder;
import ru.ctf.focusstart.kopylov.logic.Game;

import javax.swing.*;
import java.awt.*;

public class UIMainWindow {
    private static JFrame mainFrame;

    public UIMainWindow() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setMenu();
        mainFrame.getJMenuBar().setBackground(Palette.getHeadColor());
        mainFrame.getJMenuBar().setBorderPainted(false);

        mainFrame.setLayout(new BorderLayout());

        UIHeader header = new UIHeader();
        mainFrame.add(header.getHead(), BorderLayout.NORTH);

        UIGameField gameField = new UIGameField();
        Game.addListener(gameField);
        Game.newGame();
        mainFrame.add(gameField.getField(), BorderLayout.CENTER);

        mainFrame.setResizable(true);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setMinimumSize(new Dimension(FieldBuilder.getWidth() * 40,FieldBuilder.getHeight() * 40 + 40));
    }

    private void setMenu() {
        UIMenu headMenu = new UIMenu();

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(headMenu.getMenu());

        mainFrame.setJMenuBar(jMenuBar);
    }

    public static void refreshGameField() {
        mainFrame.setMinimumSize(new Dimension(FieldBuilder.getWidth() * 40,FieldBuilder.getHeight() * 40 + 40));
        mainFrame.getContentPane().validate();
        mainFrame.getContentPane().repaint();
    }
}
