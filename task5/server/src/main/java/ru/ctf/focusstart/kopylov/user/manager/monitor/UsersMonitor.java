package ru.ctf.focusstart.kopylov.user.manager.monitor;

import ru.ctf.focusstart.kopylov.user.User;
import ru.ctf.focusstart.kopylov.user.UserDisconnectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class UsersMonitor implements UserDisconnectedListener {
    private volatile LinkedBlockingQueue<User> users = new LinkedBlockingQueue<>();

    private List<MonitorFoundDeadUserListener> listeners = new ArrayList<>();

    private Thread monitor;

    public UsersMonitor() {
        monitor = new Thread(() -> {
            while (true) {
                for (User user : users) {
                    if (user.isDisconnected()) {
                        handleUserDisconnectedEvent(user);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {
                }
            }
        });
    }

    public void addUser(User user) {
        users.add(user);
        user.addUserDisconnectedListener(this);
    }

    public void disconnectUser(User user) {
        users.remove(user);
    }

    public void startMonitor() {
        monitor.start();
    }

    public void stopMonitor() {
        monitor.interrupt();
    }

    public void addFoundDeadUserListener(MonitorFoundDeadUserListener listener) {
        listeners.add(listener);
    }

    @Override
    public void handleUserDisconnectedEvent(User user) {
        users.remove(user);
        for (MonitorFoundDeadUserListener listener : listeners) {
            listener.handleFoundDeadUser(user);
        }
    }
}
