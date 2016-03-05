package com.cheny.concurrency.practice.test;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class WaitTest1 {

    public static void main(String[] args) {
        A a = new A();
        ThreadA ta = new ThreadA("ta",a);

        synchronized(a) { // 通过synchronized(ta)获取“对象ta的同步锁”
            try {
                System.out.println(Thread.currentThread().getName()+" main start ta");
                ta.start();

                System.out.println(Thread.currentThread().getName() + " block itself on a ");
                // 主线程等待
                a.wait();

                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread{
        private  A a ;
        public ThreadA(String name, A a) {
            super(name);
            this.a = a;
        }

        public void run() {
            synchronized (a) { // 通过synchronized(this)获取“当前对象的同步锁”
                System.out.println(Thread.currentThread().getName()+" wakup others which locked on a ");
                a.notify();    // 唤醒“当前对象上的等待线程”
            }
        }
    }

    static class A {

    }
}
