package ru.ctf.focusstart.kopylov.cells;

import javax.swing.*;
import java.awt.*;

public class BombCell extends Cell {
    private static final Icon BOMB_ICON = new ImageIcon((new ImageIcon("task3\\src\\main\\resources\\1f4a3-bomba.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));

    @Override
    public CellAction pressLeftMouseButton() {
        if (isMarked) {
            return CellAction.NOTHING;
        }
        if (isOpen) {
            return CellAction.NOTHING;
        }
        button.setIcon(BOMB_ICON);
        isOpen = true;
        return CellAction.BOOM;
    }

    @Override
    public JButton getButton() {
        return button;
    }
}
