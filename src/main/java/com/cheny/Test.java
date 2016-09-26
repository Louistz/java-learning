package com.cheny;

import java.net.InetAddress;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Test {
    public void  test(){
        System.out.println("Hello");

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for(int i=0;i<stackTraceElements.length;i++){
            StackTraceElement ste = stackTraceElements[i];
            System.out.println(ste.getClassName() +" ,"+ste.getMethodName());

        }
    }

    public static void main(String[] args) {
        try{
            InetAddress addr = InetAddress.getByName("www.baidu.com");
            System.out.println(addr);
        }catch (Exception e){

        }


    }
}
