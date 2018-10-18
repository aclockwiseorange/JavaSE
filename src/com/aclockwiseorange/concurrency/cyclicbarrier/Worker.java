package com.aclockwiseorange.concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Runnable {
    private CyclicBarrier barrier;
    private final AtomicInteger itemsCount;
    private final int maxItemNumber;

    public Worker(CyclicBarrier barrier, AtomicInteger itemsCount, int maxItemNumber) {
        this.barrier = barrier;
        this.itemsCount = itemsCount;
        this.maxItemNumber = maxItemNumber;
    }
    @Override
    public void run() {
        int itemNumber;
        while ((itemNumber = itemsCount.getAndIncrement()) < maxItemNumber) {
            System.out.println(Thread.currentThread().getName() + " finish item " + itemNumber);
            int randomSleepTime = new Random().nextInt(200);
            try {
                Thread.sleep(randomSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
