package com.cheny.concurrency.threadlocal;

import java.util.Date;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Task implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Thread: " + Thread.currentThread().getName() + " Formatted Date: "
                    + ThreadLocalSample.threadSafeFormat(new Date()));
        }
    }
}
