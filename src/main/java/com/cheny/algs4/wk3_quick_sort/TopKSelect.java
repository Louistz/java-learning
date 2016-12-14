package com.cheny.algs4.wk3_quick_sort;

import com.cheny.algorithm.sort.MergeSort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <p>topK</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TopKSelect {


    public Comparable select(Comparable[] a, int k){

        int lo = 0,lh = a.length - 1;

        while (lh > lo){
            int j = partition(a,lo,lh);
            if(j < k){
                lo = j + 1;
            } else
            if(j > k){
                lh = j - 1;
            }else {
                return a[k];
            }
        }
        return a[k];
    }

    public int partition(Comparable[] a, int lo, int lh) {

        int i = lo, j = lh + 1;

        while (true) {

            while (less(a[++i], a[lo])) {
                if (i == lh) {
                    break;
                }
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    private boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private void exchange(Comparable[] a, int x , int y){
        Comparable tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }


    public static void main(String[] args) {
        TopKSelect tks = new TopKSelect();
        Integer[] a = new Integer[30];
        for(int i=0;i<a.length;i++){
            a[i] = StdRandom.uniform(0, 1000);
        }


        MergeSort ms = new MergeSort();
        Integer[] b = new Integer[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        ms.sort(b);
        for(int i=0;i<b.length;i++){
            System.out.print(b[i] + " ");
        }
        System.out.println();
        StdRandom.shuffle(a);
        Integer topK = (Integer)tks.select(a,2);

        System.out.println(topK);
    }
}
