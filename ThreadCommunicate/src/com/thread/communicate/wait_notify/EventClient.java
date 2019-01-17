package com.thread.communicate.wait_notify;

import java.util.concurrent.TimeUnit;

public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    eventQueue.offer(new EventQueue.Event());
                }
            }
        }, "Producer").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    eventQueue.take();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Consumer").start();
    }
}
