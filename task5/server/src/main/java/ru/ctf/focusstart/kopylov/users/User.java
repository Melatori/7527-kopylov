package ru.ctf.focusstart.kopylov.users;

import ru.ctf.focusstart.kopylov.ServerManager;
import ru.ctf.focusstart.kopylov.messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class User {
    private final String name;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    User(String name, Socket socket, BufferedReader reader, PrintWriter writer) {
        this.name = name;
        this.socket = socket;
        this.reader = reader;
        this.writer = writer;
    }

    void sendMessage(String message) {
        writer.println(message);
        writer.flush();

        System.out.println("User \"" + name + "\" was sent a message \"" + message + "\"");
    }

    boolean listenForMessage() {
        try {
            if (reader.ready()) {
                Message message = new Message(this, reader.readLine());
                ServerManager.getInstance().sendMessageToAllUsers(message);
            }
        } catch (IOException e) {
            System.out.println("Connection to \"" + name + "\" was failed");
            return false;
        }
        return true;
    }

    boolean isDisconnected() {
        try {
            if (socket.getInputStream().read() == -1) {
                return true;
            }
        } catch (IOException e) {
            return true;
        }
        return false;
    }

    void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Err on closing user socket: " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }
}
