package ru.ctf.focusstart.kopylov.header;

import javax.swing.*;

public class NewGameButton {
    private JButton button = new JButton();

    public NewGameButton() {
        button.setText("New game");
//        button.setSize(120,40);
    }

    public JButton getButton() {
        return button;
    }
}
