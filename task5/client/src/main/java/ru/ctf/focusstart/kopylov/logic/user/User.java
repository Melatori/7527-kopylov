package ru.ctf.focusstart.kopylov.logic.user;

import ru.ctf.focusstart.kopylov.logic.connection.Connector;
import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.logic.receiver.ReceiverUsernameMessageListener;

import java.util.ArrayList;
import java.util.List;

public class User implements ReceiverUsernameMessageListener {
    private List<UserListener> listeners = new ArrayList<>();
    private List<UserAuthorizationListener> authorizationListeners = new ArrayList<>();
    private String name;
    private Connector connector;

    public User(Connector connector) {
        this.connector = connector;
        this.connector.getReceiver().addUsernameListener(this);
    }

    public void setUsername(String name) {
        this.name = name;

        Message message = new Message("Unknown client", name, "Auth");
        connector.getSender().sendMessage(message);
    }

    public void regainUsername() {
        if (name != null) {
            setUsername(name);
        }
    }

    public void addListener(UserListener listener) {
        listeners.add(listener);
    }

    public void addAuthorizationListener(UserAuthorizationListener listener) {
        authorizationListeners.add(listener);
    }

    private void notifyAuthorizationComplete() {
        for (UserAuthorizationListener listener : authorizationListeners) {
            listener.handleAuthorizationCompleteEvent();
        }
    }

    private void notifyAuthorizationFailed() {
        for (UserAuthorizationListener listener : authorizationListeners) {
            listener.handleAuthorizationFailedEvent();
        }
    }

    @Override
    public void handleUnableUsernameMessageEvent() {
        connector.closeConnection();
        name = null;
        notifyAuthorizationFailed();
        for (UserListener listener : listeners) {
            listener.handleUsernameOccupiedEvent();
        }
    }

    @Override
    public void handleAcceptedUsernameMessageEvent() {
        notifyAuthorizationComplete();
        for (UserListener listener : listeners) {
            listener.handleUsernameReceivedEvent();
        }
    }
}
