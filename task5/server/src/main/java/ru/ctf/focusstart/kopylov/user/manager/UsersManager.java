package ru.ctf.focusstart.kopylov.user.manager;

import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.user.User;
import ru.ctf.focusstart.kopylov.user.builder.UserBuilder;
import ru.ctf.focusstart.kopylov.user.builder.UserLoginListener;
import ru.ctf.focusstart.kopylov.user.manager.maintainer.UsersMaintainer;
import ru.ctf.focusstart.kopylov.user.manager.monitor.MonitorFoundDeadUserListener;
import ru.ctf.focusstart.kopylov.user.manager.monitor.UsersMonitor;
import ru.ctf.focusstart.kopylov.user.receiver.ReceiverMessageListener;

import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class UsersManager implements MonitorFoundDeadUserListener, UserLoginListener, ReceiverMessageListener {
    private final Object lock = new Object();

    private volatile LinkedBlockingQueue<User> users = new LinkedBlockingQueue<>();
    private UsersMonitor usersMonitor = new UsersMonitor();
    private UsersMaintainer usersMaintainer = new UsersMaintainer();

    public UsersManager() {
        usersMonitor.addFoundDeadUserListener(this);
    }

    public void startServer() {
        usersMonitor.startMonitor();
        usersMaintainer.startMaintain();
    }

    public void stopServer() {
        usersMonitor.stopMonitor();
        usersMaintainer.stopMaintain();
        for (User user : users) {
            user.sendServerCloseMessage();
            usersMonitor.disconnectUser(user);
            usersMaintainer.disconnectUser(user);
            user.disconnect();
        }
        users.clear();
    }

    public void createNewUser(Socket userSocket) {
        User user = new User(userSocket);
        UserBuilder builder = new UserBuilder(user, users);
        usersMonitor.addUser(user);
        usersMaintainer.addUser(user);
        builder.addLoginListener(this);
    }

    @Override
    public void handleFoundDeadUser(User user) {
        synchronized (lock) {
            System.out.println("in synchronized block");
            if (users.contains(user)) {
                users.remove(user);
                usersMaintainer.disconnectUser(user);
                usersMonitor.disconnectUser(user);
                user.disconnect();

                Message message = new Message("Server", "User \"" + user.getUsername() + "\" has disconnected", "Message");
                sendMessageToAllUsers(message);
            }
        }
    }

    @Override
    public void usernameHasSet(User user) {
        users.add(user);
        user.addMessageListener(this);
        user.usernameSet();

        sendUsernameListToUser(user);

        Message message = new Message("Server", "User \"" + user.getUsername() + "\" has connected", "Message");
        sendMessageToAllUsers(message);
    }

    private void sendMessageToAllUsers(Message message) {
        for (User user : users) {
            user.sendMessage(message);
        }
    }

    private void sendUsernameListToUser(User user) {
        StringBuilder builder = new StringBuilder();
        for (User u : users) {
            builder.append(u.getUsername());
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);

        Message message = new Message("Server", builder.toString(), "System");

        user.sendMessage(message);
    }

    @Override
    public void handleMessageReceivedEvent(Message message) {
        sendMessageToAllUsers(message);
    }
}
