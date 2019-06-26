package ru.ctf.focusstart.kopylov.logic.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.logic.connection.ConnectionBrokenListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Sender {
    private List<SenderListener> listeners = new ArrayList<>();
    private List<ConnectionBrokenListener> brokenListeners = new ArrayList<>();

    private PrintWriter writer;

    public Sender() {}

    public Sender(PrintWriter writer) {
        this.writer = writer;
    }

    public void sendMessage(String text) {
        Message message = new Message("Client", text, "Message");

        sendMessage(message);

        notifyListeners();
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
            notifyConnectionBrokenListeners();
        }
    }

    public void addListener(SenderListener listener) {
        listeners.add(listener);
    }

    public void addConnectionBrokenListener(ConnectionBrokenListener listener) {
        brokenListeners.add(listener);
    }

    private void notifyListeners() {
        for (SenderListener listener : listeners) {
            listener.handleMessageSentEvent();
        }
    }

    private void notifyConnectionBrokenListeners() {
        for (ConnectionBrokenListener listener : brokenListeners) {
            listener.handleConnectionBrokenEvent();
        }
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
}
