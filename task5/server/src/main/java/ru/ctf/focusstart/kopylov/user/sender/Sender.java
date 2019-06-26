package ru.ctf.focusstart.kopylov.user.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.user.ConnectionBrokenListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Sender {
    private PrintWriter writer;

    private List<ConnectionBrokenListener> listeners = new ArrayList<>();

    public Sender(PrintWriter writer) {
        this.writer = writer;
    }

    public void sendMessage(Message message) {
        ObjectMapper mapper = new ObjectMapper();

        if (!message.getType().equals("System"))
            System.out.println("Send message: " + message.toString());

        try {
            writer.println(mapper.writeValueAsString(message));
            writer.flush();
        } catch (IOException ignored) {
            System.err.println("Can't send the a message. Connection problem");
            notifyConnectionBroken();
        }
    }

    public void addConnectionBrokenListener(ConnectionBrokenListener listener) {
        listeners.add(listener);
    }

    private void notifyConnectionBroken() {
        for (ConnectionBrokenListener listener : listeners) {
            listener.handleConnectionBrokenEvent();
        }
    }
}
