package com.cheny.others;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *     一个最简单的LRU实现
 *
 *     非线程安全
 *     不可用并发读的读写锁，LinkedHansMap的读是非线程安全的
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V>{

    private int cacheSize;

    public LRUCache(int cacheSize){
        super(16,0.75f,true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() >= cacheSize;
    }
}
