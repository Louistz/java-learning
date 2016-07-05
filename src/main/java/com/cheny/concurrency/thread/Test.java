package com.cheny.concurrency.thread;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        Thread t = new Thread(new Calculator(100));
        try {

            t.start();
            Thread.sleep(1000L);
            Thread.sleep(1000L);
            t.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
