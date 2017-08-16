package com.cheny.reflect;

import java.util.Arrays;

/**
 * TODO
 * <p>Filename: com.cheny.reflect.ReflectTest2.java</p>
 * <p>Date: 2017-07-22 15:02.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class ReflectTest2 {

    public static void main(String[] args) {
        System.out.println(Arrays.asList(A.class.getDeclaredFields()));
        System.out.println(Arrays.asList(B.class.getSuperclass().getDeclaredFields()));


        System.out.println(A.class.isAssignableFrom(B.class));
        System.out.println(A.class.isAssignableFrom(Object.class));
        System.out.println(B.class.isAssignableFrom(A.class));
        System.out.println(A.class.isAssignableFrom(A.class));
        System.out.println(Object.class.isAssignableFrom(A.class));

        System.out.println(C.class.isAssignableFrom(A.class));
        System.out.println(C.class.isAssignableFrom(B.class));
        System.out.println(C.class.isAssignableFrom(C.class));
        System.out.println(Object.class.isAssignableFrom(C.class));

        Object a = new A();

        System.out.println(a.getClass());
    }
}
