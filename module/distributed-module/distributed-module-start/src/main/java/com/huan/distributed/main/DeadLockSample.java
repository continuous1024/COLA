package com.huan.distributed.main;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 区分线程状态 -> 查看等待目标 -> 对比 Monitor 等持有状态
 * https://en.wikipedia.org/wiki/Banker%27s_algorithm
 * https://docs.oracle.com/javase/7/docs/technotes/guides/lang/cl-mt.html
 */
public class DeadLockSample extends Thread {
    private String first;
    private String second;

    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(3000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    public static void scanDeadLock() {
        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        Runnable dlCheck = () -> {
            // 要注意的是，对线程进行快照本身是一个相对重量级的操作，还是要慎重选择频度和时机。
            long[] threadIds = mbean.findDeadlockedThreads();
            if (threadIds != null) {
                ThreadInfo[] threadInfos = mbean.getThreadInfo(threadIds);
                System.out.println("Detected deadlock threads:");
                for (ThreadInfo threadInfo : threadInfos) {
                    System.out.println(threadInfo.getThreadName());
                }
            }
        };

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // 稍等5秒，然后每10秒进行一次死锁扫描
        scheduler.scheduleAtFixedRate(dlCheck, 5L, 10L, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        scanDeadLock();

        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);
//        DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
//        DeadLockSample t2 = new DeadLockSample("Thread2", lockA, lockB);
        t1.start();
        //t1.start();  // 两次调用一个线程的start方法会抛出java.lang.IllegalThreadStateException
        t2.start();
        t1.join();
        t2.join();
    }
}
