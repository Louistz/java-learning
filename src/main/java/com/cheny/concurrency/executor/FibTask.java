package com.cheny.concurrency.executor;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class FibTask extends RecursiveTask<Integer>{

    private static final int threshold = 13;
    private int number;

    public FibTask(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }

    @Override
    protected Integer compute() {
        int n = number;
        int result = 0;
        if(n <= threshold){
            result =  seqFib(n);
        }else{
            FibTask t1 = new FibTask(n - 1);
            FibTask t2 = new FibTask(n - 2);
            invokeAll(t1, t2);
            result = t1.join() + t2.join();
        }
        System.out.println("f("+n+")"+"->"+result);
        return result;
    }

    private int seqFib(int n){
        if(n == 1){
            return 0;
        }else if(n == 2){
            return 1;
        }else {
            return seqFib(n-1) + seqFib(n-2);
        }
    }
}
