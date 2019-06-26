package ru.ctf.focusstart.kopylov.user.maintainer;

import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.user.sender.Sender;

public class Maintainer {
    private Sender sender;
    private Message messagePing = new Message("Server", "Ping", "System");
    private Message messageShutdown = new Message("Server", "Shutdown", "System");

    public Maintainer(Sender sender) {
        this.sender = sender;
    }

    public void maintain() {
        sender.sendMessage(messagePing);
    }

    public void sendServerCloseMessage() {
        sender.sendMessage(messageShutdown);
    }
}
