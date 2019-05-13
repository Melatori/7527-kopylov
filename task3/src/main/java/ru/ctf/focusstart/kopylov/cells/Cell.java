package ru.ctf.focusstart.kopylov.cells;

import javax.swing.*;
import java.awt.*;

public abstract class Cell {
    protected JButton button;
    protected static final Icon FLAG_ICON = new ImageIcon((new ImageIcon("task3\\src\\main\\resources\\1f6a9-krasnyi-flag.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));
    protected boolean isMarked = false;
    protected boolean isOpen = false;
    protected int content = 0;
    public static enum CellAction {
        BOOM,
        OPEN_CELLS,
        NOTHING
    }

    public Cell() {
        button = new JButton();
        button.setSize(40, 40);
//        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    public abstract CellAction pressLeftMouseButton();

    public void pressRightMouseButton() {
        if (!isMarked) {
            isMarked = true;
            button.setIcon(FLAG_ICON);
        } else {
            isMarked = false;
            button.setIcon(null);
        }
    }

    public abstract JButton getButton();

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

}
