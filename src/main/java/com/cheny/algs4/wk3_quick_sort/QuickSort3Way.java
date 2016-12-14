package com.cheny.algs4.wk3_quick_sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class QuickSort3Way {

    public void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }

    public void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo){
            return;
        }

        int lt = lo, gt=hi;
        Comparable v = a[lo];
        int i = lo;

        while (i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0) exchange(a,lt++,i++);
            else if(cmp > 0) exchange(a,i,gt--);
            else i++;
        }
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);

    }

    private void exchange(Comparable[] a, int x , int y){
        Comparable tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void main(String[] args) {
        QuickSort3Way ms = new QuickSort3Way();
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
