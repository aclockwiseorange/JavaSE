package com.aclockwiseorange.concurrency.cyclicbarrier;

public class Shipper implements Runnable {
    @Override
    public void run() {
        System.out.println("Items shipped");
    }
}
