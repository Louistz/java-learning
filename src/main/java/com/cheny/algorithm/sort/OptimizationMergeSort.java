package com.cheny.algorithm.sort;

/**
 * Created by cheney on 2016/7/5.
 * 归并排序在数组较短的时候，递归性能会下降。所以一般在数组长度较小的时候使用插入排序，其他使用归并排序。
 * 如Collections#sort就是如此。
 */
public class OptimizationMergeSort extends AbstractSort {

    private static final int INSERTIONSORT_THRESHOLD = 7;

    @Override
    public void sort(Comparable[] a) {
        Comparable[] tmp = a.clone();
        mergeSort(tmp, a, 0, a.length);
    }

    private void mergeSort(Comparable[] src,Comparable[] dst, int start,int end){

        int length = end -start;
        if(length < INSERTIONSORT_THRESHOLD){
            for(int i=start;i<end;i++){
                for(int j=i;j>start && less(dst[j],dst[j-1]);j--){
                    exchange(dst,j,j-1);
                }
            }
            return;
        }

        int mid = (start +end) >>> 1;

        mergeSort(dst, src, start, mid);
        mergeSort(dst, src, mid, end);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (less(src[mid-1],src[mid])) {
            System.arraycopy(src, start, dst, start, end-start);
            return;
        }

        merge(src,dst,start,mid,end);

    }
    private void merge(Comparable src[],Comparable[] dst, int start, int mid, int end) {

        for (int i = start,p=start,m=mid; i < end; i++) {
            if(m >= end || p<mid && less(src[p],src[m])){
                dst[i] = src[p++];
            }else{
                dst[i] = src[m++];
            }
        }
    }
}
