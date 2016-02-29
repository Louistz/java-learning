package com.cheny.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class CountDownLatchSample {

    //计算 n个线程并发执行task 所需的时间
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i = 0 ; i< nThreads; i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    try{
                        // thread wait here
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            //when task has been executed, endGate -= 1
                            endGate.countDown();
                        }
                    }catch (InterruptedException e){

                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        //nThread start together
        startGate.countDown();
        //nThread exected and wait others
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
