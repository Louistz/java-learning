package com.cheny.algs4.wk4_priority_queue;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <p>
 * HeapSort
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class HeapSort {

    public void sort(Comparable[] a) {
        Comparable[] copyOfA = new Comparable[a.length + 1];
        System.arraycopy(a, 0, copyOfA, 1, a.length);

        int N = a.length;
        build(copyOfA,N);
        sort(copyOfA, N);

        System.arraycopy(copyOfA,1,a,0,a.length);
    }

    private void build(Comparable[] a, int N) {
        for (int i = N / 2; i >= 1; i--) {
            sink(a, i, N);
        }
    }

    private void sort(Comparable[] a, int N){
        while (N > 1) {
            exchange(a, 1, N--);
            sink(a,1,N);
        }
    }

    private void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a[j], a[j + 1])) j++;
            if (less(a[j], a[k])) break;
            exchange(a, k, j);
            k = j;
        }
    }


    private boolean less(Comparable x, Comparable y) {
        return x.compareTo(y) < 0;
    }

    private void exchange(Comparable[] a, int x, int y) {
        Comparable tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void main(String[] args) {
        HeapSort ms = new HeapSort();
        Integer[] a = new Integer[30];
        for(int i=0;i<a.length;i++){
            a[i] = StdRandom.uniform(0, 1000);
        }

        ms.sort(a);

        for(int i=0;i<a.length;i++){
            System.out.print(a[i] + " ");
        }
    }
}
