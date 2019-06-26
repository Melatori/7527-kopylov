package ru.ctf.focusstart.kopylov.ui.dragon.components;

import javax.swing.*;

public class DragonPanel extends JPanel implements DragonComponent {
    public void showD() {
        this.setEnabled(true);
        this.setVisible(true);
    }

    public void hideD() {
        this.setEnabled(false);
        this.setVisible(false);
    }
}
