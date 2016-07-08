package com.cheny.algorithm.search;

import java.util.Iterator;

/**
 * <p>有序KEY</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BinarySearchST<K extends Comparable,V> implements ST<K,V> {

    private static final int DEFAULT_CAPACITY = 10;

    private K[] keys;

    private V[] values;

    private int N = 0;

    public BinarySearchST(){
        this(DEFAULT_CAPACITY);
    }

    public BinarySearchST(int capacity){
        this.keys = (K[])new Comparable[capacity];
        this.values = (V[])new Object[capacity];
    }

    private void resize(){
        int length = keys.length;
        if( N * 4 / 3 > length || length > N * 2){
            K[] tmpk = (K[])new Comparable[length*2];
            V[] tmpv = (V[])new Object[length*2];
            for(int i=0;i< length;i++){
                tmpk[i] = keys[i];
                tmpv[i] = values[i];
            }
            this.keys = tmpk;
            this.values = tmpv;
        }
    }

    //有序二分查找,找到比key小的数量
    private int rank(K key){
        int lo=0,hi = N -1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0){
                hi = mid - 1;
            }else if(cmp > 0){
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return lo;
    }

    //无序二分查找
    private int rank(K key, int lo , int hi){
        if(hi < lo){
            return lo;
        }
        int mid = (lo + hi)/2;
        int cmp = key.compareTo(keys[mid]);
        if(cmp < 0){
            return rank(key,lo,mid);
        }else if(cmp > 0){
            return rank(key,mid+1,hi);
        }else{
            return mid;
        }
    }

    @Override
    public void put(K k, V v) {
        int m = rank(k);
        if(keys[m] != null){
            if(k.compareTo(keys[m]) == 0){
                values[m] = v;
                return;
            }
            for(int i = N;i>m;i--){
                keys[i] = keys[i-1];
                values[i] = values[i-1];
            }
        }
        keys[m] = k;
        values[m] = v;
        N ++;
        resize();
    }

    @Override
    public V get(K k) {
        int m = rank(k);
        if(m < N && keys[m].compareTo(k) == 0){
            return values[m];
        }else{
            return null;
        }

    }

    @Override
    public boolean contains(K k) {
        int m = rank(k);
        return m < N && keys[m].compareTo(k) == 0;
    }

    @Override
    public void delete(K k) {
        int m = rank(k);
        if(m < N && keys[m].compareTo(k) == 0){
            for(int i=m;i<N;i++){
                keys[i] = keys[i+1];
                values[i] = values[i+1];
            }
            N -- ;
        }
        resize();
    }

    @Override
    public Iterator keys() {
        return new Iterator() {

            private int index = 0;
            @Override
            public boolean hasNext() {
                return index<N;
            }

            @Override
            public K next() {
                return keys[index++];
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return N<=0;
    }

    @Override
    public int size() {
        return N;
    }
}
