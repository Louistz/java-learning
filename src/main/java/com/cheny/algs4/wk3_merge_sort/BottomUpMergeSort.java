package com.cheny.algs4.wk3_merge_sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BottomUpMergeSort {

    public void sort(Comparable[] a){
        Comparable[] aux  = new Comparable[a.length];
        for(int sz = 1; sz < a.length; sz =sz + sz){
            System.out.println("sz = " + sz);
            for(int lo = 0; lo < a.length - sz ; lo += sz+sz){
                int lh = lo+sz+sz - 1;
                int mid = lo + sz - 1;
                merge(a,aux,lo,mid, min(lh,a.length - 1));

                String s = String.format("merge(a,aux,%s,%s,%s)",lo,mid,min(lh,a.length-1));
                System.out.println(s);
            }
        }

    }

    private int min(int x , int y){
        return x < y ? x : y;
    }

    private void merge(Comparable[] a,Comparable[] aux, int lo, int mid, int hi){

        int n = hi-lo+1;
        System.arraycopy(a, lo, aux, lo, n);

        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if (less(aux,i,j)) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

    }

    private boolean less(Comparable[] a, int m , int n){
        return a[m].compareTo(a[n]) <= 0;
    }

    private void exchange(Comparable[] a , int m ,int n){
        Comparable t = a[m];
        a[m] = a[n];
        a[n] = t;
    }

    public static void main(String[] args) {
        BottomUpMergeSort ms = new BottomUpMergeSort();
        Integer[] a = new Integer[30];
        for(int i=0;i<a.length;i++){
            a[i] = StdRandom.uniform(0,1000);
        }


        Integer[] b = new Integer[a.length];
        System.arraycopy(a,0,b,0,a.length);

        ms.sort(a);

        MergeSort ms2 = new MergeSort();
        ms2.sort(b);

        for(Comparable x : a){
            System.out.print(x + " ");
        }
        System.out.println();
        for(Comparable x : b){
            System.out.print(x + " ");
        }
    }
}
