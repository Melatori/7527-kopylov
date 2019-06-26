package ru.ctf.focusstart.kopylov.user.receiver;

import ru.ctf.focusstart.kopylov.Message;

public interface ReceiverAuthMessageListener {
    void handleAuthMessageReceivedEvent(Message message);
}
