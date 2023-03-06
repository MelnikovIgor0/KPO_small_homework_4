package org.example;

import java.util.Random;

public class Producer implements Runnable {
    private DataBuffer<Integer> buffer;
    private int counter;

    public Producer(DataBuffer<Integer> buf, int capacity) {
        buffer = buf;
        counter = capacity;
    }

    public void run() {
        Random randomizer = new Random();
        while (counter > 0) {
            if (buffer.full()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {
                }
            } else {
                synchronized (buffer) {
                    try {
                        Integer value = randomizer.nextInt(100);
                        System.out.println("Producer generated value: " + value.toString());
                        buffer.add(value);
                        --counter;
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }
}
