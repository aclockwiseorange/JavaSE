package com.aclockwiseorange.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class ShipperThread implements Runnable {
    private final CountDownLatch latch;

    public ShipperThread(CountDownLatch latch) {
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
