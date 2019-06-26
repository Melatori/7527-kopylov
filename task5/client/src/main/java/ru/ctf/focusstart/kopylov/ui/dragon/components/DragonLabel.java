package ru.ctf.focusstart.kopylov.ui.dragon.components;

import javax.swing.*;

public class DragonLabel extends JLabel implements DragonComponent {
    public DragonLabel(String text) {
        super(text);
    }

    public DragonLabel() {
        super();
    }

    @Override
    public void showD() {
        setVisible(true);
    }

    @Override
    public void hideD() {
        setVisible(false);
    }
}
