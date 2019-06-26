package ru.ctf.focusstart.kopylov.user.monitor;

import java.util.concurrent.atomic.AtomicInteger;

public class Monitor {
    private volatile AtomicInteger disconnectTimer = new AtomicInteger(0);

    public void connectionAlive() {
        disconnectTimer.set(0);
    }

    public boolean isDisconnected() {
        return !(disconnectTimer.incrementAndGet() < 20);
    }
}
