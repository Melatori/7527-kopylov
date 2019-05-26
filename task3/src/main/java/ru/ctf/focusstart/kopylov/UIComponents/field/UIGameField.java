package ru.ctf.focusstart.kopylov.UIComponents.field;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.FieldBuilder;
import ru.ctf.focusstart.kopylov.logic.Game;
import ru.ctf.focusstart.kopylov.logic.GameListener;
import ru.ctf.focusstart.kopylov.logic.cells.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UIGameField implements GameListener {
    private JPanel fieldPanel;

    public UIGameField() {
        fieldPanel = new JPanel();
        fieldPanel.setOpaque(true);
        fieldPanel.setBackground(Palette.getBackgroundColor());

        UICell[][] uiCells = buildField();
        initializeField(uiCells);
    }

    @Override
    public void createNewGame() {
        UICell[][] uiCells = buildField();
        initializeField(uiCells);
    }

    private UICell[][] buildField() {
        Cell[][] clawCells = FieldBuilder.buildField();
        UICell[][] field = new UICell[FieldBuilder.getHeight()][FieldBuilder.getWidth()];

        for (int i = 0; i < FieldBuilder.getHeight(); i++) {
            for (int j = 0; j < FieldBuilder.getWidth(); j++) {
                field[i][j] = new UICell();
                clawCells[i][j].addListener(field[i][j]);
            }
        }

        return field;
    }

    private void initializeField(UICell[][] cells) {
        fieldPanel.removeAll();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                attachEvent(i, j, cells[i][j]);

                fieldPanel.add(cells[i][j].getButton());
            }
        }

        fieldPanel.setLayout(new GridLayout(FieldBuilder.getHeight(), FieldBuilder.getWidth()));
        fieldPanel.setSize(new Dimension(FieldBuilder.getWidth() * 20, FieldBuilder.getHeight() * 20));
    }

    private void attachEvent(int x, int y, UICell cell) {
        cell.getButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Game.reactOnOpenCell(x, y);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    Game.reactOnMarkCell(x, y);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public JPanel getField() {
        return fieldPanel;
    }
}
