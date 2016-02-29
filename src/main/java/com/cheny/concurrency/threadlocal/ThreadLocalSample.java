package com.cheny.concurrency.threadlocal;

import java.text.DateFormat;
import java.util.Date;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ThreadLocalSample {

    public static void main(String[] args){
        Thread t1 = new Thread(new Task());
        Thread t2 = new Thread( new Task());

        t1.start();
        t2.start();
    }

    public static String threadSafeFormat(Date date){
        DateFormat formatter = PerThreadFormatter.getDateFormatter();
        return formatter.format(date);
    }
}
