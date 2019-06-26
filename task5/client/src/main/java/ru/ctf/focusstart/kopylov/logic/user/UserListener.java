package ru.ctf.focusstart.kopylov.logic.user;

import ru.ctf.focusstart.kopylov.logic.connection.ConnectionListener;

public interface UserListener extends ConnectionListener {
    void handleUsernameOccupiedEvent();

    void handleUsernameReceivedEvent();
}
