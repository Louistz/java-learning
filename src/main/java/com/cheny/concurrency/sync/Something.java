package com.cheny.concurrency.sync;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Something {

    public synchronized void a(){
        System.out.println("a start");
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("a end");
    }

    public synchronized void b(){
        System.out.println("b start");
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("b end");
    }

    public synchronized static void c(){
        System.out.println("c start");
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("c end");
    }

    public synchronized static void d(){
        System.out.println("d start");
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("d end");
    }



    public static void main(String[] args) {
        Something s = new Something();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                s.a();
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                s.b();
            }
        });
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Something.c();
            }
        });
        t3.start();
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                Something.d();
            }
        });
        t4.start();
    }
}
