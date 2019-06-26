package ru.ctf.focusstart.kopylov.user.receiver;

import ru.ctf.focusstart.kopylov.Message;

public interface ReceiverSystemMessageListener {
    void handleSystemMessageReceivedEvent(Message message);
}
