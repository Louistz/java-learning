package com.cheny.algorithm.sort;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class InsertSort extends AbstractSort{

    /* <-,<-,<-,x,x,x,x,...x*/
    @Override
    public void sort(Comparable[] a) {
        for(int i=1;i<a.length;i++){
            for(int j=i;j>0;j--) {
                if (less(a[j], a[j- 1])) {
                    exchange(a, j, j - 1);
                }
            }
        }
    }
}
