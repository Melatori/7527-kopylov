package ru.ctf.focusstart.kopylov.logic.connection;

import ru.ctf.focusstart.kopylov.logic.connection.maintainer.ConnectionMaintainer;
import ru.ctf.focusstart.kopylov.logic.connection.monitor.ConnectionMonitor;
import ru.ctf.focusstart.kopylov.logic.receiver.Receiver;
import ru.ctf.focusstart.kopylov.logic.sender.Sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connector implements ConnectionBrokenListener {
    private String host = "localhost";
    private String port = "4444";

    private boolean connected = false;

    private Socket socket = new Socket();

    private Sender sender = new Sender();
    private Receiver receiver = new Receiver();
    private ConnectionMonitor monitor;
    private ConnectionMaintainer maintainer;

    private List<ConnectionListener> listeners = new ArrayList<>();

    private void openConnection() {
        if ((host != null) && (port != null)) {
            openConnection(host, port);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void openConnection(String host, String port) {
        this.host = host;
        this.port = port;

        int connectorCounter = 0;

        while (!(connected || connectorCounter >= 10)) {
            try {
                socket = new Socket(host, Integer.valueOf(port));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                buildConnection(reader, writer);

                monitor.startMonitorServerConnection();
                maintainer.startMaintainServerConnection();

                connected = true;

                receiver.startReceiveMessages();
                notifyConnectionEstablish();
            } catch (IOException e) {
                System.err.println("Can't open connection to server");
                try {
                    Thread.sleep(500);
                    connectorCounter++;
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (connectorCounter >= 10) {
            notifyConnectionProblem();
        }
    }

    public void closeConnection() {
        try {
            monitor.stopMonitorServerConnection();
            maintainer.stopMaintainServerConnection();
            receiver.stopReceiveMessage();
            socket.close();
            connected = false;
            notifyConnectionProblem();
        } catch (IOException e) {
            System.err.println("Connection error");
        }
    }

    private void reestablishConnection() {
        closeConnection();
        openConnection();
    }

    private void buildConnection(BufferedReader reader, PrintWriter writer) {
        if (sender == null) {
            sender = new Sender(writer);
            sender.addConnectionBrokenListener(this);
        } else {
            sender.setWriter(writer);
        }

        if (receiver == null) {
            receiver = new Receiver(reader);
            receiver.addConnectionBrokenListener(this);
        } else {
            receiver.setReader(reader);
        }

        if (monitor == null) {
            monitor = new ConnectionMonitor();
            monitor.addListener(this);
            receiver.addSystemListener(monitor);
        }

        if (maintainer == null) {
            maintainer = new ConnectionMaintainer(sender);
        }
    }

    public void addListener(ConnectionListener listener) {
        listeners.add(listener);
    }

    private void notifyConnectionEstablish() {
        for (ConnectionListener listener : listeners) {
            listener.handleConnectionEstablishEvent();
        }
    }

    private void notifyConnectionProblem() {
        for (ConnectionListener listener : listeners) {
            listener.handleConnectionProblemEvent();
        }
    }

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public void handleConnectionBrokenEvent() {
        receiver.stopReceiveMessage();
        notifyConnectionProblem();
        reestablishConnection();
    }
}
