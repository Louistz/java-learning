package com.cheny.guava.concurrency;

import com.google.common.util.concurrent.RateLimiter;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestRateLimiter {

    public static void main(String[] args){
        RateLimiter limiter = RateLimiter.create(1);
        while(true){
            limiter.acquire();
            test();
        }
//        if(limiter.tryAcquire()){
//            test();
//        }else {
//            System.out.println("访问过于频繁");
//        }

    }
    public static void test(){
        System.out.println("Hello World");

    }
}
