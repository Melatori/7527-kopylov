package ru.ctf.focusstart.kopylov;

import ru.ctf.focusstart.kopylov.user.manager.UsersManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ServerManager {
    private ServerSocket serverSocket;
    private UsersManager manager = new UsersManager();
    private boolean interrupted = false;
    private static ServerManager ourInstance = new ServerManager();

    static ServerManager getInstance() {
        return ourInstance;
    }

    private ServerManager() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server shutdown");
            stopServer();
            manager.stopServer();
        }));
    }

    void startServer() {
        try {
            PropertiesReader propertiesReader = new PropertiesReader();
            serverSocket = new ServerSocket(propertiesReader.getSocket());
        } catch (IOException ignored) {
            System.err.println("Can't get server properties file");
            return;
        }

        manager.startServer();
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
                    Thread createUser = new Thread(() -> manager.createNewUser(userSocket));
                    createUser.start();
                }
            } catch (Exception e) {
                System.err.println("Something went wrong on accepting client sockets: " + e.getMessage());
            }
        }
    }
}
