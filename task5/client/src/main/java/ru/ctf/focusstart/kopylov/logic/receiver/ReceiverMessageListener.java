package ru.ctf.focusstart.kopylov.logic.receiver;

import ru.ctf.focusstart.kopylov.Message;

public interface ReceiverMessageListener {
    void handleNewServerMessageEvent(Message message);
    void handleNewMessageEvent(Message message);
}
