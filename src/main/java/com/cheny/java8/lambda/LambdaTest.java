package com.cheny.java8.lambda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class LambdaTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("b", "a", "c", "e", "d");
        list.forEach(i -> System.out.print(i + ","));
        System.out.println();
        Collections.sort(list, (i1, i2) -> i1.compareTo(i2));
        list.forEach(i -> System.out.print(i + ","));

        list.forEach(System.out::println);


        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).
        distinct().mapToInt(num -> num * 2).
        peek(System.out::println).skip(2).limit(4).sum());

        System.out.println("//////////////////////");
        List<Integer> l = new ArrayList<>(1000000);
        for(int i=0;i<1000000;i++){
            l.add(i);
        }

        long start2 = System.currentTimeMillis();
        l.stream().filter(s -> s%2 == 0).map(num -> num*2
        );
        System.out.println("forEach:" + (System.currentTimeMillis() - start2));



        long start = System.currentTimeMillis();
        for(Integer i : l){
            if(i%2 == 0){
                i=i*2;
            }
        }
        System.out.println("for:" + (System.currentTimeMillis() - start));

    }
}
