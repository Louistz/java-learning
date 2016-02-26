package com.cheny.guava;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestCache {

    public static void main(String[] args){

        LoadingCache<String,Integer> cache = CacheBuilder.newBuilder()
                .concurrencyLevel(3)
                .expireAfterAccess(1,TimeUnit.DAYS)
                .expireAfterWrite(1,TimeUnit.DAYS)
                .initialCapacity(3)
                .maximumSize(100)
                .recordStats()
                .removalListener(new RemovalListener<String, Integer>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Integer> notification) {
                        System.out.println("Remove"+notification.getKey()+"->"+notification.getValue());
                    }
                })
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return key.length();
                    }
                });
        try{
            for(int i=0;i<20;i++){
                Integer s = cache.get(String.valueOf(i));
                System.out.println(i+"->"+s);
                TimeUnit.SECONDS.sleep(1);
            }
            for(int i=0;i<20;i++){
                Integer s = cache.get(String.valueOf(i));
                System.out.println(i+"->"+s);
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("命中率:"+cache.stats().toString());
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
