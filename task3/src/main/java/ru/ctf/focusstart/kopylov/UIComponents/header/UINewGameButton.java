package ru.ctf.focusstart.kopylov.UIComponents.header;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.Game;

import javax.swing.*;
import java.awt.*;

class UINewGameButton {
    private JButton button = new JButton();

    UINewGameButton() {
        button.setOpaque(true);
        button.setBackground(Palette.getHeaderBackgroundColor());
        button.setForeground(Palette.getTextColor());
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setText("New game");
        button.setSize(80,40);
        button.setPreferredSize(new Dimension(80,30));
        button.addActionListener((e) -> Game.restartGame());
    }

    JButton getButton() {
        return button;
    }
}
