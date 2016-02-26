package com.cheny.concurrency.thread;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Calculator implements Runnable{

    private int number;

    public Calculator(int number){
        this.number = number;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.printf("%s : %d * %d = %d \n", Thread.currentThread().getName(),number,i,i*number);
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
