package com.cheny.concurrency.practice.schduledmessage;

import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Set;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class OrderConsumeHandler {

    private Jedis jedis = new Jedis("127.0.0.1",6379);

    public void consume() throws ParseException{
        Calendar cal = Calendar.getInstance();
        long now = DateFormatUtil.removeMills(cal);
        Set<String> orders = jedis.zrange("orders",0,now);
        if(orders.isEmpty()){
            System.out.println("当前没有订单要处理");
        }else{
            for(String orderId:orders){
                System.out.println("订单处理中：" + orderId);
                jedis.zrem("orders",orderId);
            }
        }

    }
}
