package ru.ctf.focusstart.kopylov.part2;

import java.util.concurrent.atomic.AtomicInteger;

class SomeData {
    private static AtomicInteger count = new AtomicInteger(0);
    int number;

    SomeData() {
        number = count.incrementAndGet();
    }
}
