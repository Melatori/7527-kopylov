package ru.ctf.focusstart.kopylov;

import ru.ctf.focusstart.kopylov.logic.ClientManager;
import ru.ctf.focusstart.kopylov.ui.components.MainWindow;

public class Client {

    public static void main(String[] args) {
        ClientManager manager = new ClientManager();
        new MainWindow(manager);
    }
}