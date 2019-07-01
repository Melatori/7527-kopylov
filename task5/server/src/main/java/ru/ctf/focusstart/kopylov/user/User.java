package ru.ctf.focusstart.kopylov.user;

import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.user.maintainer.Maintainer;
import ru.ctf.focusstart.kopylov.user.monitor.Monitor;
import ru.ctf.focusstart.kopylov.user.receiver.Receiver;
import ru.ctf.focusstart.kopylov.user.receiver.ReceiverAuthMessageListener;
import ru.ctf.focusstart.kopylov.user.receiver.ReceiverMessageListener;
import ru.ctf.focusstart.kopylov.user.receiver.ReceiverSystemMessageListener;
import ru.ctf.focusstart.kopylov.user.sender.Sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User implements ConnectionBrokenListener, ReceiverSystemMessageListener {
    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    private Monitor monitor;
    private Maintainer maintainer;

    private List<UserDisconnectedListener> disconnectedListeners = new ArrayList<>();

    private String username;

    public User(Socket socket) {
        this.socket = socket;
        try {
            receiver = new Receiver(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            sender = new Sender(new PrintWriter(socket.getOutputStream()));
            monitor = new Monitor();
            maintainer = new Maintainer(sender);

            receiver.addConnectionBrokenListener(this);
            receiver.addSystemMessageListener(this);
            sender.addConnectionBrokenListener(this);

            receiver.startReceiveMessages();
        } catch (IOException e) {
            System.err.println("Can't establish connection");
        }
    }

    public void disconnect() {
        try {
            receiver.stopReceiveMessages();
            socket.close();
        } catch (IOException e) {
            System.err.println("Connection broken on trying to close");
        }
    }

    public void sendMessage(Message message) {
        sender.sendMessage(message);
    }

    public void maintainConnection() {
        maintainer.maintain();
    }

    public void sendServerCloseMessage() {
        maintainer.sendServerCloseMessage();
    }

    public boolean isDisconnected() {
        return monitor.isDisconnected();
    }

    public void usernameWasOccupied() {
        Message message = new Message("Server", "User already exists", "Auth");
        sender.sendMessage(message);
        disconnect();
    }

    public void usernameSet() {
        Message message = new Message("Server", "Username set", "Auth");
        sender.sendMessage(message);
        receiver.setUsername(username);
    }

    public void addUserDisconnectedListener(UserDisconnectedListener listener) {
        disconnectedListeners.add(listener);
    }

    public void addMessageListener(ReceiverMessageListener listener) {
        receiver.addMessageListener(listener);
    }

    public void addAuthMessageListener(ReceiverAuthMessageListener listener) {
        receiver.addAuthMessageListener(listener);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void handleConnectionBrokenEvent() {
        for (UserDisconnectedListener listener : disconnectedListeners) {
            listener.handleUserDisconnectedEvent(this);
        }
    }

    @Override
    public void handleSystemMessageReceivedEvent(Message message) {
        if (message.getMessage().equals("Ping")) {
            monitor.connectionAlive();
        } else if (message.getMessage().equals("Shutdown")) {
            for (UserDisconnectedListener listener : disconnectedListeners) {
                listener.handleUserDisconnectedEvent(this);
            }
        }
    }
}
