package com.cheny.algorithm.sort;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 *
 *
 * //* <,<,<,<,<,<,<,[|],>,>,>,>,>,> *
 *
 */
public class QuickSort extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    private  void sort(Comparable[] a, int start , int end){
        /**小数组情况下,插入排序要比快速排序快.*/
        /* if(start >= end + M){
               insertSort(a,start,end);
           }
         */
        if(start >= end){
            return;
        }
        int mid = partition(a,start,end);
        sort(a,start,mid-1);
        sort(a,mid+1,end);
    }

    private int partition(Comparable[] a,int start, int end){

        Comparable v = a[start];
        int i=start, j=end+1;

        while (true){
            while (less(a[++i],v)) {
                if(i == end){
                    break;
                }
            }
            while (less(v,a[--j])){
                if(j == start){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            exchange(a,i,j);
        }
        exchange(a,start,j);
        return j;
    }
}
