package com.cheny.algorithm.exc;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class FindMax {

    public static void main(String[] args) {
        int a[] = {31,-41,59,26,-53,58,97,-93,-23,84};
        System.out.println(findMax(a));
        System.out.println(findMax2(a));
    }



    public static int findMax(int[] a){
        int max = 0;
        int tmp = 0;

        for(int i=0;i<a.length;i++){
            if(a[i]+tmp >= 0){
                tmp +=a[i];
            }else{
                tmp = 0;
            }
            max = max(max,tmp);

        }
        return max;

    }

    public static int findMax2(int[] a){
        int maxsofar = 0;
        int maxhere = 0;
        for(int i=0;i<a.length;i++){
            maxhere = max(maxhere+a[i],0);
            maxsofar = max(maxhere,maxsofar);
        }
        return maxsofar;
    }


    public static int max(int a,int b){
        return a>=b ? a : b;
    }


}
