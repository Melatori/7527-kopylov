package ru.ctf.focusstart.kopylov.part2;

import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {
    private ArrayBlockingQueue<SomeData> queue;
    private long periodMs = 100;
    private boolean isWorking = true;

    Producer(ArrayBlockingQueue<SomeData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isWorking) {
            try {
                Thread.sleep(periodMs);
                SomeData data = new SomeData();
                try {
                    queue.add(data);
                    System.out.println(Thread.currentThread().getName() + ": Added data \"" + data.number + "\" to queue");
                } catch (IllegalStateException ignored) {
                    System.out.println(Thread.currentThread().getName() + ": No more space in queue");
                    boolean isSend = false;
                    long startTime = System.currentTimeMillis();
                    while (!isSend) {
                        Thread.sleep(periodMs);
                        try {
                            queue.add(data);
                            isSend = true;
                            System.out.println(Thread.currentThread().getName() + ": Added data \"" + data.number + "\" to queue after " +
                                    ((double) (System.currentTimeMillis() - startTime) / 1000) + " seconds of waiting");
                        } catch (IllegalStateException ignore) {
                        }
                    }
                }
            } catch (InterruptedException ignore) {
                isWorking = false;
            }
        }
    }

    void setPeriodMs(long periodMs) {
        this.periodMs = periodMs;
    }
}
