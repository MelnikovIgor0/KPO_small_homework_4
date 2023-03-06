package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBuffer<T> {
    private List<T> data;
    private int bufferSize;
    private Random sleeppingRandomizer;

    public DataBuffer(int bufferSize) {
        data = new ArrayList<T>();
        this.bufferSize = bufferSize;
        sleeppingRandomizer = new Random();
    }

    public void add(T x) throws BufferOverflowException, InterruptedException {
        if (full()) {
            throw new BufferOverflowException();
        }
        data.add(x);
        Thread.sleep(sleeppingRandomizer.nextInt(100));
    }

    public T pop() throws BufferEmptyException, InterruptedException {
        if (empty()) {
            throw new BufferEmptyException();
        }
        T ans = data.get(0);
        data.remove(0);
        Thread.sleep(sleeppingRandomizer.nextInt(100));
        return ans;
    }

    public boolean empty() {
        return data.size() == 0;
    }

    public boolean full() {
        return data.size() >= bufferSize;
    }
}
