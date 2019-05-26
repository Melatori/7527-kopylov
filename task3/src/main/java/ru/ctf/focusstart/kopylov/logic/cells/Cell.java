package ru.ctf.focusstart.kopylov.logic.cells;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<CellListener> listeners = new ArrayList<>();
    public boolean IS_MARKED = false;
    public boolean IS_OPEN = false;
    private final int content;

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
        this.content = content;
    }

    public void addListener(CellListener cell) {
        listeners.add(cell);
    }

    public CellAction open() {
        if (IS_MARKED) {
            return CellAction.NOTHING;
        }
        if (IS_OPEN) {
            return CellAction.NOTHING;
        }
        IS_OPEN = true;
        for (CellListener cell : listeners) {
            cell.open(content);
        }
        if (content == -1) {
            return CellAction.BOOM;
        }
        if (content == 0) {
            return CellAction.EMPTY_CELL_OPEN;
        }
        return CellAction.CELL_OPEN;
    }

    public CellAction mark() {
        if (IS_OPEN) {
            return CellAction.NOTHING;
        }
        if (!IS_MARKED) {
            IS_MARKED = true;
            for (CellListener cell : listeners) {
                cell.mark();
            }
            if (content == -1) {
                return CellAction.BOMB_MARKED;
            }
            if (content == 0 || (content > 0 && content < 9)) {
                return CellAction.CELL_MARKED;
            }
        } else {
            IS_MARKED = false;
            for (CellListener cell : listeners) {
                cell.unmark();
            }
            if (content == -1) {
                return CellAction.BOMB_UNMARKED;
            }
            if (content == 0 || (content > 0 && content < 9)) {
                return CellAction.CELL_UNMARKED;
            }
        }
        return null;
    }
}
