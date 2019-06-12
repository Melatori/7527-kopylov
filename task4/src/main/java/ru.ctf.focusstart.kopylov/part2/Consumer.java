package ru.ctf.focusstart.kopylov.part2;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {
    private ArrayBlockingQueue<SomeData> queue;
    private long periodMs = 150;
    private boolean isWorking = true;
    private long processingMs = 20;

    Consumer(ArrayBlockingQueue<SomeData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isWorking) {
            try {
                Thread.sleep(periodMs);
                SomeData data = queue.take();
                System.out.println(Thread.currentThread().getName() + ": Data \"" + data.number + "\" was taken from queue");
                Thread.sleep(processingMs);
                System.out.println(Thread.currentThread().getName() + ": Data \"" + data.number + "\" was consumed");
            } catch (InterruptedException ignore) {
                isWorking = false;
            }
        }
    }

    void setPeriodMs(long periodMs) {
        this.periodMs = periodMs;
    }
}
