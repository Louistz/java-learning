package com.cheny.concurrency.thread;

import sun.net.www.http.KeepAliveCache;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestThreadGroup {

    public static void main(String[] args){

        ThreadGroup g1 = new ThreadGroup("g1");
        ThreadGroup g2 = new ThreadGroup("g2");

        for(int i = 0;i<10;i++){
            if(i%2 == 0){
                new Thread(g1,new Calculator(i)).start();
            }else {
                new Thread(g2,new Calculator(i)).start();
            }
        }
        g1.activeCount();
        g2.activeCount();

    }
}
