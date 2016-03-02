package com.cheny.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestThreadPoolAndExecutor {

    public static void main(String[] args) throws Exception{
       // testExecutors();
        testCompletionService();
    }

    public static void testExecutors() throws Exception{
        ExecutorService es = Executors.newFixedThreadPool(5);
        List<Future> fl = new ArrayList<Future>();
        es.execute(new Runnable() {
            public void run() {

                System.out.println("hello");


            }
        });

        /**
         * 如果异常不捕获而抛出的话，会中断线程的执行，导致其他任务也执行不下去。
         */
        fl.add(es.submit(new Runnable() {
            public void run() {
                try{
                throw new IllegalArgumentException("error 2");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }));
        fl.add(es.submit(new Callable<String>() {
            public String call() throws Exception {
                return "hello 3";
            }
        }));

        for(int i=0 ;i<fl.size();i++){
            System.out.println(fl.get(i).get());
        }
        if(!es.isShutdown()){
            es.shutdown();
        }

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5,Executors.defaultThreadFactory());
        ses.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("Hello Sweety");
            }
        },1000L,1000L,TimeUnit.MILLISECONDS);

        ses.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("Honey");
            }
        },2000L,1000L,TimeUnit.MILLISECONDS);

        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(5);
    }



    public static void testCompletionService() throws Exception{
        ExecutorService es = Executors.newCachedThreadPool();

        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(es);

        Callable<Integer> task = new Callable<Integer>() {
            public Integer call() throws Exception {
                return Math.round(System.currentTimeMillis()/100000000);
            }
        };

        for(int i =0 ;i<100;i++){
            cs.submit(task);
        }

        Future<Integer> f = null;
        while ((f=cs.take()) != null){
            System.out.println(f.get());
        }
        es.shutdown();

    }



}
