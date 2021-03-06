package ru.ctf.focusstart.kopylov.logic.connection.monitor;

import ru.ctf.focusstart.kopylov.Message;
import ru.ctf.focusstart.kopylov.logic.connection.ConnectionBrokenListener;
import ru.ctf.focusstart.kopylov.logic.receiver.ReceiverSystemMessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionMonitor implements ReceiverSystemMessageListener {
    private volatile AtomicInteger disconnectTimer = new AtomicInteger(0);
    private List<ConnectionBrokenListener> listeners = new ArrayList<>();

    private volatile boolean interrupted = false;

    public ConnectionMonitor() {
    }

    public void handleSystemMessageEvent(Message message) {
        if (message.getMessage().equals("Ping")) {
            handleServerAlive();
        } else if (message.getMessage().equals("Shutdown")) {
            for (ConnectionBrokenListener listener : listeners) {
                listener.handleConnectionBrokenEvent();
            }
        }
    }

    public void startMonitorServerConnection() {
        Thread monitor = new Thread(this::monitoring);
        interrupted = false;
        monitor.start();
    }

    public void stopMonitorServerConnection() {
        interrupted = true;
    }

    private void monitoring() {
        while (!interrupted) {
            if (isDisconnected()) {
                for (ConnectionBrokenListener listener : listeners) {
                    listener.handleConnectionBrokenEvent();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    private void handleServerAlive() {
        disconnectTimer.set(0);
    }

    private boolean isDisconnected() {
        return !(disconnectTimer.incrementAndGet() < 20);
    }

    public void addListener(ConnectionBrokenListener listener) {
        listeners.add(listener);
    }
}
