package com.cheny.concurrency;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestThred {

    public static void main(String[] args) {
        try{
            Thread a = new Thread(new MyThread());
            a.start();
            System.out.println("main:" + a.isInterrupted());

            a.interrupt();
            System.out.println("main:" + a.isInterrupted());

            Thread.sleep(3000L);
            System.out.println("main:" + a.isInterrupted());
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
