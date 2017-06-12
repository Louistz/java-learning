package com.cheny.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * TODO
 * <p>Filename: com.cheny.reflect.ReflectTest.java</p>
 * <p>Date: 2017-06-06 16:41.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class ReflectTest {

    private int a = 100;

    public static void main(String[] args) throws Exception{

        //class

        Class cls = ReflectTest.class;
        Class cls1 = Class.forName("com.cheny.reflect.ReflectTest");

        System.out.println(cls.toString());
        System.out.println(cls1.toString());

        System.out.println(cls.getName()+"|" + cls.getCanonicalName() + "|" + cls.getSimpleName() + " | " + cls.getTypeName());
        System.out.println(cls.getModifiers());


        //method
        System.out.println(Arrays.asList(cls.getDeclaredMethods()).toString());
        System.out.println(Arrays.asList(cls.getMethods()).toString());

        Method method = cls.getDeclaredMethods()[0];
        System.out.println(Modifier.isAbstract(method.getModifiers()));
        System.out.println(Modifier.isFinal(method.getModifiers()));
        System.out.println(Modifier.isNative(method.getModifiers()));
        System.out.println(Modifier.isPublic(method.getModifiers()));
        System.out.println(Modifier.isStatic(method.getModifiers()));

        method.getReturnType();
        method.getParameters();

        //field
        Field[] fields = cls.getDeclaredFields();
        System.out.println(Arrays.asList(fields).toString());
        Field field = fields[0];
        System.out.println(field.getType().toString() + "|" + field.getGenericType().toString());


        //constructor
        Constructor[] cs = cls.getConstructors();
        System.out.println(Arrays.asList(cs).toString());
        Constructor c = cs[0];
        ReflectTest rt = (ReflectTest)c.newInstance();
        System.out.println(field.get(rt));

        method.setAccessible(true);
       // method.invoke(null,(Object)new String[]{});

    }
}
