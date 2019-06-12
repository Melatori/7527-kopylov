package ru.ctf.focusstart.kopylov.part2;

import java.util.concurrent.ArrayBlockingQueue;

public class Main2 {
    private static final int PRODUCER_COUNT = 3;
    private static final int CONSUMER_COUNT = 4;
    private static final int QUEUE_CAPACITY = 40;

    public static void main(String[] args) {
        ArrayBlockingQueue<SomeData> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        Thread[] producers = new Thread[PRODUCER_COUNT];
        Thread[] consumers = new Thread[CONSUMER_COUNT];

        for (int i = 0; i < PRODUCER_COUNT; i++) {
            Producer producer = new Producer(queue);
            producer.setPeriodMs(200);
            producers[i] = new Thread(producer);
            producers[i].start();
        }

        for (int i = 0; i < CONSUMER_COUNT; i++) {
            Consumer consumer = new Consumer(queue);
            consumer.setPeriodMs(400);
            consumers[i] = new Thread(consumer);
            consumers[i].start();
        }

        try {
            Thread.sleep(10000);
            for (int i = 0; i < PRODUCER_COUNT; i++) {
                producers[i].interrupt();
            }
            for (int i = 0; i < CONSUMER_COUNT; i++) {
                consumers[i].interrupt();
            }
        } catch (InterruptedException ignore) {
        }
    }
}
