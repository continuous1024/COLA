package com.huan.distributed.main;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(nextId::getAndIncrement);

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
}

/**
 * 线程可以有多个threadlocal，多个threadlocal保存的thread的threadLocals变量里面
 * threadLocals是一个ThreadLocalMap，它的Entry继承WeakReference<ThreadLocal<?>>，
 * 使用ThreadLocal的弱引用作为 Key 的，弱引用的对象在 GC 时会被回收。
 *
 */
public class ThreadLocalSample {
    public static void main(String[] args) {
       Integer threadId = ThreadId.get();
       System.out.println(threadId);
       threadId = ThreadId.get();
       System.out.println(threadId);

       new Thread(() -> {
           Integer id = ThreadId.get();
           System.out.println(id);
       }).start();

        new Thread(() -> {
            Integer id = ThreadId.get();
            System.out.println(id);
        }).start();
    }
}
