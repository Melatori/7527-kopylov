package ru.ctf.focusstart.kopylov.logic.stopwatch;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Stopwatch {
    private boolean isOn = false;
    private int timeSec = 0;
    private List<StopwatchListener> listeners = new ArrayList<>();

    private Timer timer = new Timer(1000, e -> {
        for (StopwatchListener listener : listeners) {
            listener.setTime(++timeSec);
        }
    });

    public void start() {
        if (isOn) {
            return;
        }
        isOn = true;
        timer.restart();
    }

    public void addListeners(StopwatchListener listener) {
        listeners.add(listener);
    }

    public void stop() {
        isOn = false;
        timer.stop();
    }

    public void reset() {
        isOn = false;
        timer.stop();
        timeSec = 0;
        for (StopwatchListener listener : listeners) {
            listener.setTime(timeSec);
        }
    }

    public int getTimeSec() {
        return timeSec;
    }
}
