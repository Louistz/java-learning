package com.cheny.concurrency.practice.schduledmessage;

import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class OrderProduceHandler {

    private AtomicLong idGenerator = new AtomicLong(0);
    private Jedis jedis = new Jedis("127.0.0.1",6379);

    public void produce() throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND,60);
        Map<String,Double> orders = new HashMap<String,Double>();
        for(int i=0;i<200;i++){
            idGenerator.getAndIncrement();
            orders.put(idGenerator.toString(), (double) DateFormatUtil.removeMills(cal));
            System.out.println("订单产生："+ (idGenerator.toString()));
        }
        jedis.zadd("orders",orders);
    }
}
