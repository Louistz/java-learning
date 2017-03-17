package com.cheny.dubbo;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Demo$Adaptive {

    public static void main(String[] args) {

        ClassLoader classLoader = Demo$Adaptive.class.getClassLoader();
        System.out.println(Demo$Adaptive.class.getResource(""));
        System.out.println(classLoader.getResource(""));
        System.out.println(classLoader.getSystemResource(""));
        while (classLoader != null){
            System.out.println(classLoader.toString());
            classLoader = classLoader.getParent();
        }
        System.out.println(ClassLoader.getSystemClassLoader().toString());
        System.out.println(Thread.currentThread().getContextClassLoader());


    }

    public void test(String s, Integer i){
        System.out.println();
    }
}
