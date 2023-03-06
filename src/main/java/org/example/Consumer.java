package org.example;


import com.sun.source.tree.SynchronizedTree;

public class Consumer implements Runnable {
    private DataBuffer<Integer> buffer;
    private int counter = 0;

    public Consumer(DataBuffer<Integer> buf, int capacity) {
        buffer = buf;
        counter = capacity;
    }

    public void run() {
        while (counter > 0) {
            if (buffer.empty()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {
                }
            } else {
                synchronized (buffer) {
                    try {
                        Integer value = buffer.pop();
                        System.out.println("Consumer read value: " + value.toString());
                        --counter;
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }
}
