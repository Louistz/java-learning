package com.cheny.ddd.domain.persistence;

import com.cheny.ddd.domain.model.Trader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public enum TraderRepository {
    INSTANCE;

    private Map<String,Object> db = new ConcurrentHashMap<String, Object>();

    public Trader getById(String id){
        return (Trader)db.get(id);
    }

    public void add(Trader trader){
        db.put(trader.getId(),trader);
    }

    public void update(Trader trader){
        if(db.containsKey(trader.getId())){
            db.put(trader.getId(),trader);
        }
    }

    public void delete(String id){
        db.remove(id);
    }
}
