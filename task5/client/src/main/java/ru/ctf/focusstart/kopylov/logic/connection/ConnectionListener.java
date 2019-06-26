package ru.ctf.focusstart.kopylov.logic.connection;

public interface ConnectionListener {
    void handleConnectionEstablishEvent();
    void handleConnectionProblemEvent();
}
