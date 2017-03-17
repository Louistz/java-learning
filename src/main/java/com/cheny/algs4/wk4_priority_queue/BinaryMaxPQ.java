package com.cheny.algs4.wk4_priority_queue;

import java.util.NoSuchElementException;

/**
 * <p>BinaryMaxPQ</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BinaryMaxPQ implements MaxPQ {

    private Comparable[] items;
    private int n;

    public BinaryMaxPQ(int capacity){
        items = new Comparable[capacity + 1];
    }

    @Override
    public void insert(Comparable x) {
        items[++n] = x;
        swim(n);
    }

    @Override
    public Comparable delMax() {
        Comparable max = items[1];
        exchange(items,1,n--);
        sink(1);
        items[n+1] = null;
        return max;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Comparable max() {
        if(n == 0){
            throw new NoSuchElementException();
        }
        return items[1];
    }

    @Override
    public int size() {
        return n;
    }


    private void swim(int k){
        while (k > 1 && less(items[k/2],items[k])){
            exchange(items,k,k/2);
            k = k/2;
        }
    }


    private void sink(int k){
        while (k*2 <= n){
            int j = 2 * k;
            if(j < n && less(items[j],items[j+1])) j++;
            if(less(items[j],items[k])) break;
            exchange(items,k,j);
            k = j;
        }
    }

    private boolean less(Comparable x, Comparable y){
        return x.compareTo(y) < 0;
    }
    private void exchange(Comparable[] a, int x, int y){
        Comparable tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }
}
