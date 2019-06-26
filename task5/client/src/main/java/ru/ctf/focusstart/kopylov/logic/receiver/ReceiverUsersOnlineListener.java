package ru.ctf.focusstart.kopylov.logic.receiver;

import java.util.List;

public interface ReceiverUsersOnlineListener {
    void handleSetUsersList(List<String> users);
    void handleAddToUsersList(String user);
    void handleRemoveFromUsersList(String user);
}
