package ru.ctf.focusstart.kopylov.gamefield;

import ru.ctf.focusstart.kopylov.cells.Cell;
import ru.ctf.focusstart.kopylov.utils.FieldBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameField {
    private JPanel field;
    private int height = 10;
    private int width = 10;
    private int bombCount = 10;

    public GameField() {
        field = new JPanel();

        initializeField();
    }

    public void initializeField() {
        Cell[][] cells = FieldBuilder.buildField(height,width,bombCount);

        for (Cell[] itemRow: cells) {
            for (Cell itemCol: itemRow){
                attachEvent(itemCol);

                field.add(itemCol.getButton());
            }
        }

        field.setLayout(new GridLayout(10,10));
    }

    private void attachEvent(Cell cell) {
        cell.getButton().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1){
                    System.out.println(cell.pressLeftMouseButton());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    cell.pressRightMouseButton();
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
        return field;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }
}
