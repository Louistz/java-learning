package com.cheny.algs4.wk3_quick_sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <p>QuickSort</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class QuickSort {

    public void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }

    public void sort(Comparable[] a, int lo, int lh){
        if(lh <= lo){
            return;
        }
        int j = partition(a,lo,lh);
        sort(a,lo,j-1);
        sort(a,j+1,lh);
    }

    public int partition(Comparable[] a, int lo, int lh){
        int i = lo ,j=lh + 1;

        while (true){
            while (less(a[++i],a[lo])){
                if(i == lh){
                    break;
                }
            }
            while (less(a[lo],a[--j])){
                if(j == lo){
                    break;
                }
            }
            if(i>=j){
                break;
            }
            exchange(a,i,j);
        }

        // must be j , because a[j] must be smaller than a[lo].
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
        QuickSort ms = new QuickSort();
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
