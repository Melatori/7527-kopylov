package ru.ctf.focusstart.kopylov.UIComponents.field;

import ru.ctf.focusstart.kopylov.UIComponents.Palette;
import ru.ctf.focusstart.kopylov.logic.field.Field;
import ru.ctf.focusstart.kopylov.logic.game.GameManager;
import ru.ctf.focusstart.kopylov.logic.cells.Cell;
import ru.ctf.focusstart.kopylov.logic.game.GameStartListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UIGameField implements GameStartListener {
    private JPanel fieldPanel;
    private Field fieldModel;

    public UIGameField(GameManager gameManager) {
        fieldPanel = new JPanel();
        fieldPanel.setOpaque(true);
        fieldPanel.setBackground(Palette.BACKGROUND_COLOR);

        fieldModel = gameManager.getField();
        gameManager.addGameStartListener(this);

        UICell[][] uiCells = buildField();
        initializeField(uiCells);
    }

    @Override
    public void handleNewGameEvent() {
        UICell[][] uiCells = buildField();
        initializeField(uiCells);
    }

    private UICell[][] buildField() {
        Cell[][] cells = fieldModel.getCells();
        UICell[][] field = new UICell[fieldModel.getHeight()][fieldModel.getWidth()];

        for (int i = 0; i < fieldModel.getHeight(); i++) {
            for (int j = 0; j < fieldModel.getWidth(); j++) {
                field[i][j] = new UICell();
                cells[i][j].addListener(field[i][j]);
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

        fieldPanel.setLayout(new GridLayout(fieldModel.getHeight(), fieldModel.getWidth()));
        fieldPanel.setSize(new Dimension(fieldModel.getWidth() * 20, fieldModel.getHeight() * 20));
    }

    private void attachEvent(int x, int y, UICell cell) {
        cell.getButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    fieldModel.openCell(x, y);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    fieldModel.markCell(x, y);
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
