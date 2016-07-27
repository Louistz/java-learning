package com.cheny.algorithm.string;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class LSD implements StringSort{

    @Override
    public void sort(String[] a) {
        int w = a[0].length();

        int N = a.length;
        int R = 256;

        String aux[] = new String[N];

        for(int i = w-1; i >= 0;i--){
            int[] count = new int[R+1];

            for(int r=0;r<N;r++){
                count[a[r].charAt(i) + 1] ++;
            }

            for(int r=0; r< R;r++){
                count[r+1] += count[r];
            }

            for(int r=0;r<N;r++){
                aux[count[a[r].charAt(i)]] = a[r];
            }

            for(int r=0;r<N;r++){
                a[r] = aux[r];
            }
        }
    }
}
