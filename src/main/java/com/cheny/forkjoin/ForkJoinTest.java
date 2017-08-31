package com.cheny.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * <p>Filename: com.cheny.forkjoin.ForkJoinTest.java</p>
 * <p>Date: 2017-08-17 14:28.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0;i<10000;i++){
            sb1.append(i+"A");
            sb2.append(i+"a");
        }
        long start = System.currentTimeMillis();
        for(int i = 0;i<1000;i++){
            System.out.print(i+":| ");
            System.out.print(pool.invoke(new TaskA(sb1.toString()))+",");
            System.out.println(pool.invoke(new TaskA(sb2.toString())));
        }
        System.out.println("cost:"+(System.currentTimeMillis()-start));

    }
}
