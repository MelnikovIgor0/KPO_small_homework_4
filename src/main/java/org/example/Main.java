package org.example;

public class Main {
    private final static int BUFFER_SIZE = 5;
    private final static int NUMBER_OF_NUMBERS = 100;

    public static void main(String[] args) {
        DataBuffer<Integer> mainBuffer = new DataBuffer<>(BUFFER_SIZE);
        Runnable producer = new Producer(mainBuffer, NUMBER_OF_NUMBERS);
        Runnable consumer = new Consumer(mainBuffer, NUMBER_OF_NUMBERS);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}