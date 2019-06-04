package ru.ctf.focusstart.kopylov.UIComponents.mainwindow;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.UIComponents.field.UIGameField;
import ru.ctf.focusstart.kopylov.UIComponents.header.UIHeader;
import ru.ctf.focusstart.kopylov.logic.game.GameManager;
import ru.ctf.focusstart.kopylov.logic.game.GameRestartListener;

import javax.swing.*;
import java.awt.*;

public class UIMainWindow implements GameRestartListener {
    private JFrame mainFrame;
    private GameManager gameManager;

    public UIMainWindow(GameManager gameManager) {
        this.gameManager = gameManager;
        gameManager.addGameRestartListener(this);
        createWindow();
    }

    @Override
    public void handleRestartGame() {
        mainFrame.setMinimumSize(new Dimension(gameManager.getField().getWidth() * 40,gameManager.getField().getHeight() * 40 + 40));
        mainFrame.getContentPane().validate();
        mainFrame.getContentPane().repaint();
    }

    private void createWindow() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setMenu();
        mainFrame.getJMenuBar().setBackground(Palette.HEADER_COLOR);
        mainFrame.getJMenuBar().setBorderPainted(false);

        mainFrame.setLayout(new BorderLayout());

        gameManager.newGame();
        UIGameField gameField = new UIGameField(gameManager);
        mainFrame.add(gameField.getField(), BorderLayout.CENTER);
        UIHeader header = new UIHeader(gameManager);
        mainFrame.add(header.getHead(), BorderLayout.NORTH);

        mainFrame.setResizable(true);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void setMenu() {
        UIMenu headMenu = new UIMenu(gameManager);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(headMenu.getMenu());

        mainFrame.setJMenuBar(jMenuBar);
    }
}
