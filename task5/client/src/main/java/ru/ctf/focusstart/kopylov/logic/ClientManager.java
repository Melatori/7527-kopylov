package ru.ctf.focusstart.kopylov.logic;

import ru.ctf.focusstart.kopylov.logic.connection.ConnectionListener;
import ru.ctf.focusstart.kopylov.logic.connection.Connector;
import ru.ctf.focusstart.kopylov.logic.receiver.Receiver;
import ru.ctf.focusstart.kopylov.logic.receiver.ReceiverMessageListener;
import ru.ctf.focusstart.kopylov.logic.receiver.ReceiverUsersOnlineListener;
import ru.ctf.focusstart.kopylov.logic.sender.Sender;
import ru.ctf.focusstart.kopylov.logic.sender.SenderListener;
import ru.ctf.focusstart.kopylov.logic.user.User;
import ru.ctf.focusstart.kopylov.logic.user.UserAuthorizationListener;
import ru.ctf.focusstart.kopylov.logic.user.UserListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientManager implements ConnectionListener {
    private Connector connector = new Connector();
    private Sender sender;
    private Receiver receiver;
    private User user;

    public ClientManager() {
        sender = connector.getSender();
        receiver = connector.getReceiver();
        user = new User(connector);
        connector.addListener(this);
    }

    public void setConnection(String address, String username) {
        List<String> serverAddress = new ArrayList<>(Arrays.asList(address.split(":")));
        connector.openConnection(serverAddress.get(0), serverAddress.get(1));
        if (connector.isConnected()) {
            user.setUsername(username);
        }
    }

    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

    public void shutdownConnection() {
        if (connector.isConnected()) {
            connector.closeConnection();
        }
    }

    public void addUserListener(UserListener listener) {
        user.addListener(listener);
    }

    public void addSenderListener(SenderListener listener) {
        sender.addListener(listener);
    }

    public void addConnectionListener(ConnectionListener listener) {
        connector.addListener(listener);
    }

    public void addAuthorizationListener(UserAuthorizationListener listener) {
        user.addAuthorizationListener(listener);
    }

    public void addUserOnlineListener(ReceiverUsersOnlineListener listener) {
        receiver.addUserOnlineListener(listener);
    }

    public void addMessageListener(ReceiverMessageListener listener) {
        receiver.addMessageListener(listener);
    }

    @Override
    public void handleConnectionEstablishEvent() {
        user.regainUsername();
    }

    @Override
    public void handleConnectionProblemEvent() {
    }
}
