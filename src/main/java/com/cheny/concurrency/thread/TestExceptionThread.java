package com.cheny.concurrency.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestExceptionThread {

    public static void main(String[] args){
        ExecutorService es  = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){

            if(i == 5){
                Future f = es.submit(new ExceptionThread("abc"));
            }else{
                es.submit(new ExceptionThread(String.valueOf(i)));
            }
        }

        System.out.println("Done");

    }
}
