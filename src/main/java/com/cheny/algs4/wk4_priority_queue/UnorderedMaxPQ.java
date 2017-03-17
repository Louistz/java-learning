package com.cheny.algs4.wk4_priority_queue;

import java.util.NoSuchElementException;

/**
 * <p>UnorderedMaxPQ</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class UnorderedMaxPQ implements MaxPQ {

    private Comparable[] items;
    private int n;

    public UnorderedMaxPQ(int capacity){
        items = new Comparable[capacity];
    }

    @Override
    public void insert(Comparable x) {
        if(n == items.length){
            int min = 0;
            for (int i = 0; i < n; i++) {
                if (less(items, i, min)) {
                    min = i;
                }
            }
            exchange(items,min,n - 1);
            items[n - 1] = x;
        }else{
            items[n++] = x;
        }
    }

    @Override
    public Comparable delMax() {
        if(n < 0){
            throw new NoSuchElementException();
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (less(items, max, i)) {
                max = i;
            }
        }
        exchange(items,max,n-1);
        Comparable x = items[--n];
        items[n] = null;
        return x;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Comparable max() {
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (less(items, max, i)) {
                max = i;
            }
        }
        return items[max];
    }

    @Override
    public int size() {
        return n;
    }
    
    private boolean less(Comparable[] a, int x, int y){
        return a[x].compareTo(a[y]) < 0;
    }
    private void exchange(Comparable[] a, int x, int y){
        Comparable tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }
}
