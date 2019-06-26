package ru.ctf.focusstart.kopylov.logic.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.logic.connection.ConnectionBrokenListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Receiver {
    private List<ReceiverMessageListener> messageListeners = new ArrayList<>();
    private List<ReceiverUsersOnlineListener> usersOnlineListeners = new ArrayList<>();
    private List<ReceiverSystemMessageListener> systemMessageListeners = new ArrayList<>();
    private List<ReceiverUsernameMessageListener> usernameMessageListeners = new ArrayList<>();
    private List<ConnectionBrokenListener> connectionBrokenListeners = new ArrayList<>();
    private Thread receive;
    private BufferedReader reader;

    public Receiver() {
    }

    public Receiver(BufferedReader reader) {
        this.reader = reader;
    }

    public void startReceiveMessages() {
        receive = new Thread(this::receiveMessage);
        receive.start();
    }

    public void stopReceiveMessage() {
        receive.interrupt();
    }

    private void receiveMessage() {
        while (true) {
            try {
                if (reader.ready()) {
                    ObjectMapper mapper = new ObjectMapper();
                    Message message = mapper.readValue(reader.readLine(), Message.class);

                    if (!message.getMessage().equals("Ping"))
                        System.out.println("Get message: " + message.toString());

                    if (message.getType().equals("Auth")) {
                        notifyUsernameListeners(message);
                    } else if (isConnectionSystemMessage(message)) {
                        notifySystemListeners(message);
                    } else if (message.getType().equals("System")) {
                        notifySetUsersListListener(parseForUsers(message.getMessage()));
                    } else {
                        if (message.getAuthor().equals("Server")) {
                            addOrRemoveFromUsersOnline(message.getMessage());
                        }
                        notifyListeners(message);
                    }
                }
            } catch (IOException ignored) {
                System.err.println("Can't read a message. Connection problem");
                notifyConnectionBrokenListeners();
            }
        }
    }

    private boolean isConnectionSystemMessage(Message message) {
        return message.getType().equals("System") && (message.getMessage().equals("Ping") || message.getMessage().equals("Shutdown"));
    }

    private List<String> parseForUsers(String string) {
        List<String> list = Arrays.asList(string.split(" "));
        for (String name : list) {
            name = name.substring(1, name.length() - 1);
        }
        return list;
    }

    private void addOrRemoveFromUsersOnline(String string) {
        List<String> parsedString = Arrays.asList(string.split(" "));
        parsedString.set(1, parsedString.get(1).substring(1, parsedString.get(1).length() - 1));
        if (parsedString.get(3).equals("connected")) {
            notifyAddUserToOnlineList(parsedString.get(1));
        } else if (parsedString.get(3).equals("disconnected")) {
            notifyRemoveUserToOnlineList(parsedString.get(1));
        }
    }

    public void addMessageListener(ReceiverMessageListener listener) {
        messageListeners.add(listener);
    }

    public void addUserOnlineListener(ReceiverUsersOnlineListener listener) {
        usersOnlineListeners.add(listener);
    }

    public void addSystemListener(ReceiverSystemMessageListener listener) {
        systemMessageListeners.add(listener);
    }

    public void addUsernameListener(ReceiverUsernameMessageListener listener) {
        usernameMessageListeners.add(listener);
    }

    public void addConnectionBrokenListener(ConnectionBrokenListener listener) {
        connectionBrokenListeners.add(listener);
    }

    private void notifyListeners(Message message) {
        if (message.getAuthor().equals("Server")) {
            for (ReceiverMessageListener listener : messageListeners) {
                listener.handleNewMessageEvent(message);
            }
        } else {
            for (ReceiverMessageListener listener : messageListeners) {
                listener.handleNewServerMessageEvent(message);
            }
        }
    }

    private void notifySystemListeners(Message message) {
        for (ReceiverSystemMessageListener listener : systemMessageListeners) {
            listener.handleSystemMessageEvent(message);
        }
    }

    private void notifyUsernameListeners(Message message) {
        if (message.getMessage().equals("User already exists")) {
            for (ReceiverUsernameMessageListener listener : usernameMessageListeners) {
                listener.handleUnableUsernameMessageEvent();
            }
        } else {
            for (ReceiverUsernameMessageListener listener : usernameMessageListeners) {
                listener.handleAcceptedUsernameMessageEvent();
            }
        }
    }

    private void notifyConnectionBrokenListeners() {
        for (ConnectionBrokenListener listener : connectionBrokenListeners) {
            listener.handleConnectionBrokenEvent();
        }
    }

    private void notifySetUsersListListener(List<String> users) {
        for (ReceiverUsersOnlineListener listener : usersOnlineListeners) {
            listener.handleSetUsersList(users);
        }
    }

    private void notifyAddUserToOnlineList(String user) {
        for (ReceiverUsersOnlineListener listener : usersOnlineListeners) {
            listener.handleAddToUsersList(user);
        }
    }

    private void notifyRemoveUserToOnlineList(String user) {
        for (ReceiverUsersOnlineListener listener : usersOnlineListeners) {
            listener.handleRemoveFromUsersList(user);
        }
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}
