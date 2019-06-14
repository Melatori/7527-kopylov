package ru.ctf.focusstart.kopylov;

import ru.ctf.focusstart.kopylov.messages.Message;
import ru.ctf.focusstart.kopylov.users.UserManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {
    private ServerSocket serverSocket;
    private boolean interrupted = false;
    private static ServerManager ourInstance = new ServerManager();

    public static ServerManager getInstance() {
        return ourInstance;
    }

    private ServerManager() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stopServer();
            UserManager.getInstance().handleServerCloseEvent();
        }));
    }

    void startServer() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader();
        serverSocket = new ServerSocket(propertiesReader.getSocket());

        UserManager.getInstance().handleServerStartEvent();
        Thread userWaiting = new Thread(this::waitingForNewUsers);
        userWaiting.start();
    }

    private void stopServer() {
        interrupted = true;
    }

    private void waitingForNewUsers() {
        while (!interrupted) {
            try {
                while (!interrupted) {
                    Socket userSocket = serverSocket.accept();
                    UserManager.getInstance().createNewUser(userSocket);
                }
            } catch (Exception e) {
                System.err.println("Something went wrong on accepting client sockets: " + e.getMessage());
            }
        }
    }

    public void sendMessageToAllUsers(Message message) {
        UserManager.getInstance().sendMessageToAllUsers(message);
    }
}
