package com.cheny.algs4.wk4_priority_queue;

import java.util.NoSuchElementException;

/**
 * <p>OrderedMaxPQ</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class OrderedMaxPQ implements MaxPQ {

    private Comparable[] items;
    private int n;

    public OrderedMaxPQ(int capacity){
        items = new Comparable[capacity];
    }

    @Override
    public void insert(Comparable x) {
        if(n == 0){
            items[n++] = x;
            return;
        }

        int i = n;
        while (--i >= 0){
            if(less(items[i],x)){
                break;
            }
        }

        if(items[i].compareTo(x) == 0){
            return;
        }
        if(n == items.length){
            for(int j = 0;j<i;j++){
                items[j] = items[j+1];
            }
            items[i] = x;
        }else{
            for(int j = n;j > i;j--){
                items[j] = items[j-1];
            }
            items[i+1] = x;
            n++;
        }
    }

    @Override
    public Comparable delMax() {
        if(n <= 0){
            throw new NoSuchElementException();
        }
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
        return items[n-1];
    }

    @Override
    public int size() {
        return n;
    }
    
    private boolean less(Comparable x, Comparable y){
        return x.compareTo(y) <= 0;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> pq = new OrderedMaxPQ(3);

        pq.insert(1);
        pq.insert(3);
        pq.insert(4);
        pq.insert(5);
        pq.insert(3);
        pq.insert(6);

        pq.delMax();
        pq.insert(7);
        pq.delMax();
        pq.insert(100);
        System.out.println();
    }
}
