package ru.ctf.focusstart.kopylov.user.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.user.ConnectionBrokenListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Receiver {
    private Thread receive;
    private BufferedReader reader;
    private String username;

    private List<ReceiverMessageListener> messageListeners = new ArrayList<>();
    private List<ReceiverSystemMessageListener> systemMessageListeners = new ArrayList<>();
    private List<ReceiverAuthMessageListener> authMessageListeners = new ArrayList<>();
    private List<ConnectionBrokenListener> brokenListeners = new ArrayList<>();

    public Receiver(BufferedReader reader) {
        this.reader = reader;
        receive = new Thread(this::receiveMessage);
    }

    public void startReceiveMessages() {
        receive.start();
    }

    public void stopReceiveMessages() {
        receive.interrupt();
    }

    public void addMessageListener(ReceiverMessageListener listener) {
        messageListeners.add(listener);
    }

    public void addSystemMessageListener(ReceiverSystemMessageListener listener) {
        systemMessageListeners.add(listener);
    }

    public void addAuthMessageListener(ReceiverAuthMessageListener listener) {
        authMessageListeners.add(listener);
    }

    public void addConnectionBrokenListener(ConnectionBrokenListener listener) {
        brokenListeners.add(listener);
    }

    private void receiveMessage() {
        while (true) {
            try {
                if (reader.ready()) {
                    ObjectMapper mapper = new ObjectMapper();
                    Message message = mapper.readValue(reader.readLine(), Message.class);

                    if (username != null) {
                        message.setAuthor(username);
                    }

                    if (!message.getType().equals("System"))
                        System.out.println("Get message: " + message.toString());

                    if (message.getType().equals("Auth")) {
                        notifyAuthMessage(message);
                    } else if (message.getType().equals("System")) {
                        notifySystemMessage(message);
                    } else {
                        notifyMessage(message);
                    }
                }
            } catch (IOException e) {
                System.err.println("Can't read a message. Connection problem");
                notifyConnectionBroken();
            }
        }
    }

    private void notifyMessage(Message message) {
        for (ReceiverMessageListener listener : messageListeners) {
            listener.handleMessageReceivedEvent(message);
        }
    }

    private void notifySystemMessage(Message message) {
        for (ReceiverSystemMessageListener listener : systemMessageListeners) {
            listener.handleSystemMessageReceivedEvent(message);
        }
    }

    private void notifyAuthMessage(Message message) {
        for (ReceiverAuthMessageListener listener : authMessageListeners) {
            listener.handleAuthMessageReceivedEvent(message);
        }
    }

    private void notifyConnectionBroken() {
        for (ConnectionBrokenListener listener : brokenListeners) {
            listener.handleConnectionBrokenEvent();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
