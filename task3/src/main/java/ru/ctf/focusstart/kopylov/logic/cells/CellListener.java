package ru.ctf.focusstart.kopylov.logic.cells;

public interface CellListener {
    void handleOpenCellEvent(int num);
    void handleMarkCellEvent();
    void handleUnmarkCellEvent();
}
