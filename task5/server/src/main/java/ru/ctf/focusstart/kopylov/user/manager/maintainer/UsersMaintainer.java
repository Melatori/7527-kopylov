package ru.ctf.focusstart.kopylov.user.manager.maintainer;

import ru.ctf.focusstart.kopylov.user.User;

import java.util.concurrent.LinkedBlockingQueue;

public class UsersMaintainer {
    private volatile LinkedBlockingQueue<User> users = new LinkedBlockingQueue<>();

    private Thread maintain;

    public UsersMaintainer() {
        maintain = new Thread(() -> {
            while (true) {
                for (User user : users) {
                    user.maintainConnection();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        });
    }

    public void startMaintain() {
        maintain.start();
    }

    public void stopMaintain() {
        maintain.interrupt();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void disconnectUser(User user) {
        users.remove(user);
    }
}
