package com.aclockwiseorange.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int MAX_NUMBER_OF_ITEMS = 100;
    private static final int MAX_NUMBER_OF_WORKERS = 5;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(MAX_NUMBER_OF_ITEMS);
        AtomicInteger itemCount = new AtomicInteger(0);
        ExecutorService workerService = Executors.newFixedThreadPool(MAX_NUMBER_OF_WORKERS);

        for (int i=0; i<MAX_NUMBER_OF_WORKERS; i++)
            workerService.execute(new WorkerThread(latch, itemCount, MAX_NUMBER_OF_ITEMS));

        new ShipperThread(latch).run();
       // workerService.shutdown();
    }
}
