package ru.ctf.focusstart.kopylov.ui.dragon.components;

import ru.ctf.focusstart.kopylov.ui.Palette;

import javax.swing.*;

public class DragonInput extends JTextField {
    public DragonInput() {
        super();

        setBorder(BorderFactory.createLineBorder(Palette.SECONDARY_DARK, 1, false));
        setBackground(Palette.SECONDARY_LIGHT);
        setForeground(Palette.TEXT_ON_SECONDARY);
    }
}
