package com.cheny.algorithm.search;

/**
 * <p>排序</p>
 * ---------------------------------------
 * |分类 |基础排序O(n^2)   |高级排序O(nlogn)|
 * |交换 |冒泡排序	      |快排           |
 * |插入 |插入排序	      |希尔           |
 * |选择	 |选择排序	      |堆排序         |
 * ---------------------------------------
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractSort {

    public abstract void sort(Comparable[] a);

    public void exchange(Comparable[] a, int i, int j){
        Comparable ai = a[i];
        a[i] = a[j];
        a[j] = ai;
    }

    public boolean less(Comparable v1 , Comparable v2){
        return v1.compareTo(v2) < 0;
    }

    public boolean isSorted(Comparable[] a){
        for(int i=1;i<a.length;i++){
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }

    public void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
            if(i< a.length-1){
                System.out.print(",");
            }
        }
        System.out.println();
    }

}
