package com.aclockwiseorange.concurrency.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int MAX_NUMBER_OF_ITEMS = 100;
    private static final int MAX_NUMBER_OF_WORKERS = 5;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(MAX_NUMBER_OF_WORKERS, new Shipper());
        AtomicInteger itemCount = new AtomicInteger(0);
        ExecutorService workerService = Executors.newFixedThreadPool(MAX_NUMBER_OF_WORKERS);

        for (int i = 0; i < MAX_NUMBER_OF_WORKERS; i++)
            workerService.execute(new Worker(barrier, itemCount, MAX_NUMBER_OF_ITEMS));
    }
}