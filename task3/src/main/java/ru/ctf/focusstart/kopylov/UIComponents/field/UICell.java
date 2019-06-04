package ru.ctf.focusstart.kopylov.UIComponents.field;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.cells.CellListener;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class UICell implements CellListener {
    private JButton button;
    private static final Icon FLAG_ICON = new ImageIcon((new ImageIcon("task3\\src\\main\\resources\\1f6a9-krasnyi-flag.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
    private static final Icon BOMB_ICON = new ImageIcon((new ImageIcon("task3\\src\\main\\resources\\1f4a3-bomba.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));

    UICell() {
        button = new JButton();
        button.setPreferredSize(new Dimension(40, 40));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    public void open(int number) {
        button.setOpaque(true);
        if (number == -1) {
            button.setIcon(BOMB_ICON);
            button.setBackground(Palette.EXPLODED_CELL_COLOR);
            button.setBorder(new BorderUIResource.LineBorderUIResource(Palette.EXPLODED_CELL_COLOR));
            return;
        } else if (number < 9 && number > 0) {
            button.setText(String.valueOf(number));
        }
        button.setBackground(Palette.OPENED_CELL_COLOR);
        button.setBorder(new BorderUIResource.LineBorderUIResource(Palette.CELL_BORDER_COLOR));
    }

    public void mark() {
        button.setIcon(FLAG_ICON);
    }

    public void unmark() {
        button.setIcon(null);
    }

    JButton getButton() {
        return button;
    }
}

