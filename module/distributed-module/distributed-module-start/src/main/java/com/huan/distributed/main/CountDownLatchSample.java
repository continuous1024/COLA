package com.huan.distributed.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatchSample {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            3, 16, 60,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(200)
        );
        for(int i = 0; i < 10; i++) {
            CountDownLatch latchA = new CountDownLatch(1);
            CountDownLatch latchB = new CountDownLatch(1);
            CountDownLatch latchC = new CountDownLatch(1);
            Runnable a = () -> {
                try {
                    latchB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                latchA.countDown();
            };

            Runnable b = () -> {
                latchB.countDown();
                try {
                    latchA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                latchC.countDown();
            };

            Runnable c = () -> {
                try {
                    latchC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
            };
            threadPoolExecutor.submit(a);
            threadPoolExecutor.submit(b);
            threadPoolExecutor.submit(c);
        }

        threadPoolExecutor.awaitTermination(50, TimeUnit.SECONDS);
    }
}
