package com.cheny.concurrency.practice.schduledmessage;

import java.util.concurrent.TimeUnit;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        final OrderConsumeHandler consumer = new OrderConsumeHandler();
        final OrderProduceHandler producer = new OrderProduceHandler();

        ThreadPoolFactory.INSTANCE.getScheduledPool().scheduleAtFixedRate(new Runnable() {
            public void run() {
                try{
                    consumer.consume();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },1L,1L, TimeUnit.SECONDS);

        ThreadPoolFactory.INSTANCE.getScheduledPool().scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    producer.produce();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 1L, 1L, TimeUnit.SECONDS);
    }
}
