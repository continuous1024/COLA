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


class LatchSample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new FirstBatchWorker(latch));
            t.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new SecondBatchWorker(latch));
            t.start();
        }
        // 注意这里也是演示目的的逻辑，并不是推荐的协调方式
        while ( latch.getCount() != 1 ){
            Thread.sleep(100L);
        }
        System.out.println("Wait for first batch finish");
        latch.countDown();
    }
}
class FirstBatchWorker implements Runnable {
    private CountDownLatch latch;
    public FirstBatchWorker(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("First batch executed!");
        latch.countDown();
    }
}
class SecondBatchWorker implements Runnable {
    private CountDownLatch latch;
    public SecondBatchWorker(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Second batch executed!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
