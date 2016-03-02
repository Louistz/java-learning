package com.cheny.concurrency.practice.schduledmessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public enum  ThreadPoolFactory {

    INSTANCE;

    private  final ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
    private  final ExecutorService typicalPool = Executors.newCachedThreadPool();

    public ScheduledExecutorService getScheduledPool(){
        return scheduledPool;
    }
    public ExecutorService getTypicalPool(){
        return typicalPool;
    }
}
