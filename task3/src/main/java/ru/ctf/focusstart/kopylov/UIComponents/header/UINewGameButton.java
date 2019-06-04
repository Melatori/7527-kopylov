package ru.ctf.focusstart.kopylov.UIComponents.header;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.field.FieldListener;
import ru.ctf.focusstart.kopylov.logic.game.GameManager;
import ru.ctf.focusstart.kopylov.logic.game.GameStateListener;

import javax.swing.*;
import java.awt.*;

class UINewGameButton implements GameStateListener, FieldListener {
    private JButton button = new JButton();

    UINewGameButton(GameManager gameManager) {
        button.setOpaque(true);
        button.setBackground(Palette.HEADER_BACKGROUND_COLOR);
        button.setForeground(Palette.TEXT_COLOR);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setText("New game");
        button.setSize(80, 40);
        button.setPreferredSize(new Dimension(80, 30));
        button.addActionListener((e) -> gameManager.restartGame());

        gameManager.addGameStateListener(this);
        gameManager.getField().addListener(this);
    }

    JButton getButton() {
        return button;
    }

    @Override
    public void handleWinGame() {
        button.setBackground(Palette.WIN_BUTTON_COLOR);
    }

    @Override
    public void handleLoseGame() {
        button.setBackground(Palette.LOSE_BUTTON_COLOR);
    }

    @Override
    public void createNewGame() {
        button.setBackground(Palette.HEADER_BACKGROUND_COLOR);
    }
}
