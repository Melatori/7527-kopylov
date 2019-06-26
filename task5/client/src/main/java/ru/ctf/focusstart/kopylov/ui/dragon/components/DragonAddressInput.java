package ru.ctf.focusstart.kopylov.ui.dragon.components;

import ru.ctf.focusstart.kopylov.ui.Palette;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class DragonAddressInput extends JFormattedTextField {
    public DragonAddressInput() {
        super();

        try {
            MaskFormatter maskFormatter = new MaskFormatter("###.###.###.###:####");
            maskFormatter.setPlaceholder("127.000.000.001:4444");
            setFormatter(maskFormatter);
        } catch (ParseException ignored) {
        }

        setBorder(BorderFactory.createLineBorder(Palette.SECONDARY_DARK, 1, false));
        setBackground(Palette.SECONDARY_LIGHT);
        setForeground(Palette.TEXT_ON_SECONDARY);
    }
}
