package ru.ctf.focusstart.kopylov;

import ru.ctf.focusstart.kopylov.UIComponents.mainwindow.UIMainWindow;
import ru.ctf.focusstart.kopylov.logic.game.GameManager;

public class Minesweeper {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        UIMainWindow window = new UIMainWindow(gameManager);
    }
}
