package ru.ctf.focusstart.kopylov.users;

import ru.ctf.focusstart.kopylov.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class UserManager {
    private volatile LinkedBlockingQueue<User> users = new LinkedBlockingQueue<>();
    private static UserManager ourInstance = new UserManager();
    private Thread threadListener;
    private Thread connectionMonitor;

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
        threadListener = new Thread(this::startListenUsers);
        connectionMonitor = new Thread(this::monitorUsersConnection);
    }

    public void createNewUser(Socket userSocket) {
        String username = "tmp";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(userSocket.getOutputStream());
            username = reader.readLine();
            if (isUsernameAllowed(username)) {
                users.add(new User(username, userSocket, reader, writer));
            } else {
                writer.println("User already exists");
                writer.flush();
                userSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Can't create new user \"" + username + "\". Connection problem");
        }
    }

    private boolean isUsernameAllowed(String name) {
        boolean userExists = false;
        for (User user : users) {
            if (user.getName().equals(name)) {
                userExists = true;
                break;
            }
        }
        return !userExists;
    }

    private void startListenUsers() {
        //noinspectional InfiniteLoopStatement
        while (true) {
            for (User user : users) {
                if (!user.listenForMessage()) {
                    System.out.println("user \"" + user.getName() + "\" disconnected");
                    users.remove(user);
                }
            }
        }
    }

    public void handleServerStartEvent() {
        threadListener.start();
        connectionMonitor.start();
    }

    public void handleServerCloseEvent() {
        threadListener.interrupt();
        connectionMonitor.interrupt();
        for (User user : users) {
            user.disconnect();
        }
        users.clear();
    }

    private void monitorUsersConnection() {
        while (true) {
            for (User user : users) {
                if (user.isDisconnected()) {
                    System.out.println("user \"" + user.getName() + "\" disconnected");
                    users.remove(user);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void sendMessageToAllUsers(Message message) {
        for (User user : users) {
            user.sendMessage(message.getMessage());
        }
    }
}
