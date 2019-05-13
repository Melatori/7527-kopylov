package ru.ctf.focusstart.kopylov.cells;

import javax.swing.*;

public class EmptyCell extends Cell {
    @Override
    public CellAction pressLeftMouseButton() {
        if (isOpen || isMarked) {
            return CellAction.NOTHING;
        }
        button.setText(String.valueOf(content));
        isOpen = true;
        return content == 0 ? CellAction.OPEN_CELLS : CellAction.NOTHING;
    }

    @Override
    public void pressRightMouseButton() {
        if (isOpen) {
            return;
        }
        if (!isMarked) {
            isMarked = true;
            button.setIcon(FLAG_ICON);
            button.setText(null);
        } else {
            isMarked = false;
            button.setIcon(null);
        }
    }

    @Override
    public JButton getButton() {
        return button;
    }
}
