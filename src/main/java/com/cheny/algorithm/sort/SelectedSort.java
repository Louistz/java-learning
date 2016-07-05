package com.cheny.algorithm.sort;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class SelectedSort extends AbstractSort {

    /*min1,min2,x,x,x,x..../
    /**
     * @param a 数组
     */
    @Override
    public void sort(Comparable[] a) {
        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                if(less(a[j],a[i])){
                    exchange(a,i,j);
                }
            }
        }
    }
}
