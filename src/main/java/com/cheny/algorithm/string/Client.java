package com.cheny.algorithm.string;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {

        String[] array= {"F1DF3","AB9S4","BT42E"};

        StringSort sort = new LSD();
        sort.sort(array);
        for(String a : array){
            System.out.print(a+" ");
        }

    }
}
