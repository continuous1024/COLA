package com.huan.distributed.main;

import java.util.concurrent.*;

public class QueueSample {
    public static final String STOP_FLAG = "STOP";

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            3, 16, 60,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(200)
        );
//        BlockingQueue<String> queueA = new ArrayBlockingQueue<>(1);
//        BlockingQueue<String> queueB = new ArrayBlockingQueue<>(1);
//        BlockingQueue<String> queueC = new ArrayBlockingQueue<>(1);
        SynchronousQueue<String> queueA = new SynchronousQueue<>();
        SynchronousQueue<String> queueB = new SynchronousQueue<>();
        SynchronousQueue<String> queueC = new SynchronousQueue<>();

            Runnable a = () -> {
            while (true) {
                try {
                    String data = queueA.take();
                    if (STOP_FLAG.equals(data)) {
                        queueB.put(STOP_FLAG);
                        break;
                    }
                    System.out.println(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    queueB.put("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable b = () -> {
            while (true) {
                try {
                    String data = queueB.take();
                    if (STOP_FLAG.equals(data)) {
                        queueB.put(STOP_FLAG);
                        break;
                    }
                    System.out.println(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    queueC.put("C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable c = () -> {
            while (true) {
                try {
                    String data = queueC.take();
                    if (STOP_FLAG.equals(data)) {
                        queueB.put(STOP_FLAG);
                        break;
                    }
                    System.out.println(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        threadPoolExecutor.submit(a);
        threadPoolExecutor.submit(b);
        threadPoolExecutor.submit(c);

        for(int i = 0; i < 10; i++) {
            queueA.put("A");
            TimeUnit.MILLISECONDS.sleep(1);
        }
        queueA.put(STOP_FLAG);
        threadPoolExecutor.awaitTermination(50, TimeUnit.SECONDS);
    }
}
