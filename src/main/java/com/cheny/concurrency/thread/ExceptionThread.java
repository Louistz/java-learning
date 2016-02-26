package com.cheny.concurrency.thread;

import java.io.File;
import java.io.FileWriter;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ExceptionThread implements Runnable {

    private String number;

    public ExceptionThread (String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("===========" + Thread.currentThread()+"Start=========");
        System.out.println(Integer.valueOf(number));
        System.out.println(number);
        System.out.println("===========" + Thread.currentThread()+"END=========");
    }
}
