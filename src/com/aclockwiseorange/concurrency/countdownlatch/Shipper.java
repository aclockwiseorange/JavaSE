package com.aclockwiseorange.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Shipper implements Runnable {
    private final CountDownLatch latch;

    public Shipper(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Items shipped");
    }
}
