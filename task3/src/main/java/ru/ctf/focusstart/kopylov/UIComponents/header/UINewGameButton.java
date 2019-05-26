package ru.ctf.focusstart.kopylov.UIComponents.header;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.Game;
import ru.ctf.focusstart.kopylov.logic.GameListener;
import ru.ctf.focusstart.kopylov.logic.GameStateListener;

import javax.swing.*;
import java.awt.*;

class UINewGameButton implements GameStateListener, GameListener {
    private JButton button = new JButton();

    UINewGameButton() {
        button.setOpaque(true);
        button.setBackground(Palette.getHeaderBackgroundColor());
        button.setForeground(Palette.getTextColor());
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setText("New game");
        button.setSize(80, 40);
        button.setPreferredSize(new Dimension(80, 30));
        button.addActionListener((e) -> Game.restartGame());

        Game.addGameStateListener(this);
        Game.addGameListener(this);
    }

    JButton getButton() {
        return button;
    }

    @Override
    public void onWinGame() {
        button.setBackground(Palette.getWinButtonColor());
    }

    @Override
    public void onLoseGame() {
        button.setBackground(Palette.getLoseButtonColor());
    }

    @Override
    public void createNewGame() {
        button.setBackground(Palette.getHeaderBackgroundColor());
    }
}
