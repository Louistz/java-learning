package com.cheny.concurrency;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class MyThread implements Runnable{

    private int i = 0;
    @Override
    public void run() {
        try{
            while (true){
                System.out.println(i++);
                System.out.println(i++);
                Thread.sleep(3000L);
                System.out.println(Thread.interrupted());
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("isInterrupted1:"+Thread.currentThread().isInterrupted());
            System.out.println(Thread.interrupted());
            System.out.println("isInterrupted2:" + Thread.currentThread().isInterrupted());
            System.out.println(Thread.interrupted());
        }


    }
}
