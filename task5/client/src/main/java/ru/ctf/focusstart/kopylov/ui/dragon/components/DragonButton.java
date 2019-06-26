package ru.ctf.focusstart.kopylov.ui.dragon.components;

import ru.ctf.focusstart.kopylov.ui.Palette;

import javax.swing.*;

public class DragonButton extends JButton {
    public DragonButton() {
        super();

        setBorderPainted(false);
        setFocusPainted(false);
        setBackground(Palette.SECONDARY);
        setForeground(Palette.TEXT_ON_SECONDARY);
    }

    public DragonButton(String text) {
        super(text);

        setBorderPainted(false);
        setFocusPainted(false);
        setBackground(Palette.SECONDARY);
        setForeground(Palette.TEXT_ON_SECONDARY);
    }
}
