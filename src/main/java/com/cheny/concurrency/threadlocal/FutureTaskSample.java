package com.cheny.concurrency.threadlocal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class FutureTaskSample {

    private final FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            return "doSomething";
        }
    });

    private final Thread thread = new Thread(future);

    public void start(){
        thread.start();
    }

    public String getTaskValue() throws InterruptedException,ExecutionException{
        return future.get();
    }
}
