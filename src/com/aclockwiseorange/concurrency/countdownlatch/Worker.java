package com.aclockwiseorange.concurrency.countdownlatch;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Runnable{
    private final CountDownLatch latch;
    private final AtomicInteger itemsCount;
    private final int maxItemNumber;

    public Worker(CountDownLatch latch, AtomicInteger itemsCount, int maxItemNumber){
        this.latch = latch;
        this.itemsCount = itemsCount;
        this.maxItemNumber = maxItemNumber;
    };

    @Override
    public void run() {
        int itemNumber;
        while ((itemNumber = itemsCount.getAndIncrement()) < maxItemNumber) {
            System.out.println(Thread.currentThread().getName() + " finish item " + itemNumber);
            latch.countDown();
            int randomSleepTime = new Random().nextInt(200);
            try {
                Thread.sleep(randomSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
