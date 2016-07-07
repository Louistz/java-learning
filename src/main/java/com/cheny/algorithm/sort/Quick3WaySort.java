package com.cheny.algorithm.sort;

/**
 * <p>基于三向切分的快速排序,在相同数据较多的情况下,速度更快</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Quick3WaySort extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    private void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo){
            return;
        }
        Comparable v = a[lo];
        int i=lo+1, le = lo, gt = hi;

        while (i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0){
                exchange(a,i++,le++);
            }else if(cmp > 0){
                exchange(a,i,gt--);
            }else {
                i ++;
            }
        }
        sort(a,lo,le-1);
        sort(a,gt+1,hi);

    }
}
