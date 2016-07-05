package com.cheny.algorithm.sort;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ShellSort extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        int gap = a.length/2;

        while(gap >= 1){
            for(int i=0;i<a.length;i++){
                for(int j=i; j>= gap ;j-=gap){
                    if(less(a[j],a[j-gap])){
                        exchange(a,j,j-gap);
                    }
                }
            }
            gap = gap/2;
        }

    }
}
