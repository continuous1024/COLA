package com.huan.distributed.main;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadSample {
    ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
}
