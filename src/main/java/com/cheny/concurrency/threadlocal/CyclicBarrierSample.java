package com.cheny.concurrency.threadlocal;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class CyclicBarrierSample {

    public class TotalTask implements Runnable{
        @Override
        public void run() {
            doSomething();
        }

        public void doSomething(){
            System.out.println(Thread.currentThread().getName()+" do Some Total task.");
        }
    }
    public class BillTask extends Thread{
        private CyclicBarrier barrier;
        public BillTask(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        public void run(){
            doSomeTask();
            try{
                barrier.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
        }

        public void doSomeTask(){
            System.out.println(Thread.currentThread().getName()+" is doing task......");
        }

    }

    public void doTask(){
        CyclicBarrier barrier = new CyclicBarrier(3,new TotalTask());

        new BillTask(barrier).start();
        new BillTask(barrier).start();
        new BillTask(barrier).start();
    }

    public static void main(String[] args) {
        CyclicBarrierSample s = new CyclicBarrierSample();
        s.doTask();
    }
}
