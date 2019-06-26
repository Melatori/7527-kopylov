package ru.ctf.focusstart.kopylov.logic.receiver;

import ru.ctf.focusstart.kopylov.Message;

public interface ReceiverSystemMessageListener {
    void handleSystemMessageEvent(Message message);
}
