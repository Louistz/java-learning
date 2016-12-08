package com.cheny.algs4.wk3_merge_sort;


import edu.princeton.cs.algs4.StdRandom;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class MergeSort {

    private static int CUTOFF = 7;

    public void sort(Comparable[] a){
        Comparable[] aux  = new Comparable[a.length];
        sort(a,aux,0,a.length-1);
    }

    public void sort(Comparable[] a, Comparable[] aux, int start, int end){
        if(start <= end + CUTOFF - 1){
            insertSort(a,start,end);
            return;
        }

        int mid = start + (end-start)/2;
        sort(a,aux,start,mid);
        sort(a,aux,mid+1,end);
        if(less(a,mid,mid+1)) return;
        merge(a,aux,start,mid,end);
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
        return a[m].compareTo(a[n]) < 0;
    }

    private void insertSort(Comparable[]a, int lo, int hi){
        if(lo == hi){
            return;
        }
        for(int i=lo + 1;i<=hi;i++){
            for(int j=i;j>lo;j--){
                if(less(a,j,j-1)){
                    exchange(a,j,j-1);
                }
            }
        }
    }

    private void exchange(Comparable[] a, int x , int y){
        Comparable tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        Integer[] a = new Integer[30];
        for(int i=0;i<a.length;i++){
            a[i] = StdRandom.uniform(0,1000);
        }

        ms.sort(a);

        for(int i=0;i<a.length;i++){
            System.out.print(a[i] + " ");
        }
    }
}
