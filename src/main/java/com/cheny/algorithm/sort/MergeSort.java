package com.cheny.algorithm.sort;


/**
 * Created by cheney on 2016/7/5.
 */
public class MergeSort extends AbstractSort {

    private Comparable[] tmp ;

    /**
     * 归并排序
     * 归并排序在数组较短的时候，递归性能会下降。所以一般在数组长度较小的时候使用插入排序，其他使用归并排序。
     * 如Collections#sort就是如此。
     * @param a
     */
    @Override
    public void sort(Comparable[] a) {
        tmp = new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private void sort(Comparable[] a, int start, int end){
        if(start >= end){ return ;}
        int mid = start + (end-start)/2;
        sort(a,start,mid);
        sort(a,mid+1,end);
        merge(a,start,mid,end);
    }

    private void merge(Comparable a[], int start, int mid, int end){
        for(int i=start;i<=end;i++){
            tmp[i] = a[i];
        }
        for(int k = start,i=start,j=mid+1; k<= end; k++){
            if( j > end || i<=mid && less(tmp[i],tmp[j])){
                a[k] = tmp[i++];
            }else{
                a[k] = tmp[j++];
            }
        }






    }
}
