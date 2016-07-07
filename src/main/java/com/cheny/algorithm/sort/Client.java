package com.cheny.algorithm.sort;

import java.util.Random;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {

        final int N = 20;

        AbstractSort search = new HeapSort();
        Integer[] a = new Integer[N];
        Random random = new Random();
        for(int i=0;i<a.length;i++) {
            a[i] = random.nextInt(100) + 1;
        }
        search.show(a);

        long start = System.currentTimeMillis();
        search.sort(a);
        System.out.println("cost:"+(System.currentTimeMillis() - start)+"ms");
        assert search.isSorted(a);
        search.show(a);
    }
}
