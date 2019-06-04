package ru.ctf.focusstart.kopylov.logic.cells;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<CellListener> listeners = new ArrayList<>();
    private boolean isMarked = false;
    private boolean isOpen = false;
    private final int CONTENT;

    public enum CellAction {
        BOOM,
        CELL_OPEN,
        EMPTY_CELL_OPEN,
        BOMB_MARKED,
        BOMB_UNMARKED,
        CELL_MARKED,
        CELL_UNMARKED,
        NOTHING
    }

    public Cell(int content) {
        if (content < -1 || content > 8) {
            content = 0;
            System.err.println("В ячейку был подан ошибочный параметр, заменен на пустую ячейку");
        }
        this.CONTENT = content;
    }

    public void addListener(CellListener cell) {
        listeners.add(cell);
    }

    public CellAction open() {
        if (isMarked) {
            return CellAction.NOTHING;
        }
        if (isOpen) {
            return CellAction.NOTHING;
        }
        isOpen = true;
        for (CellListener cell : listeners) {
            cell.open(CONTENT);
        }
        if (CONTENT == -1) {
            return CellAction.BOOM;
        }
        if (CONTENT == 0) {
            return CellAction.EMPTY_CELL_OPEN;
        }
        return CellAction.CELL_OPEN;
    }

    public CellAction mark() {
        if (isOpen) {
            return CellAction.NOTHING;
        }
        if (!isMarked) {
            isMarked = true;
            for (CellListener cell : listeners) {
                cell.mark();
            }
            if (CONTENT == -1) {
                return CellAction.BOMB_MARKED;
            }
            if (CONTENT == 0 || (CONTENT > 0 && CONTENT < 9)) {
                return CellAction.CELL_MARKED;
            }
        } else {
            isMarked = false;
            for (CellListener cell : listeners) {
                cell.unmark();
            }
            if (CONTENT == -1) {
                return CellAction.BOMB_UNMARKED;
            }
            if (CONTENT == 0 || (CONTENT > 0 && CONTENT < 9)) {
                return CellAction.CELL_UNMARKED;
            }
        }
        return null;
    }
}
