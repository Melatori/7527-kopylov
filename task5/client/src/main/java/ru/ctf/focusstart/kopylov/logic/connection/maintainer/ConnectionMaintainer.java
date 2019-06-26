package ru.ctf.focusstart.kopylov.logic.connection.maintainer;

import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.logic.sender.Sender;

public class ConnectionMaintainer {
    private Thread maintainer;
    private Sender sender;

    public ConnectionMaintainer(Sender sender) {
        this.sender = sender;
    }

    public void startMaintainServerConnection() {
        maintainer = new Thread(this::maintain);
        maintainer.start();
    }

    public void stopMaintainServerConnection() {
        maintainer.interrupt();

        Message message = new Message("Client", "Shutdown", "System");
        sender.sendMessage(message);
    }

    private void maintain() {
        Message message = new Message("Client", "Ping", "System");

        while (true) {
            this.sender.sendMessage(message);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
