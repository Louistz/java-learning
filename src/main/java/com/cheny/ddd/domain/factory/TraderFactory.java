package com.cheny.ddd.domain.factory;

import com.cheny.ddd.domain.model.Address;
import com.cheny.ddd.domain.model.Goods;
import com.cheny.ddd.domain.model.Owner;
import com.cheny.ddd.domain.model.Trader;

import java.util.List;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TraderFactory {

    private TraderFactory(){}

    public static Trader newTrade(Owner owner,List<Goods> goodsList,Address address){
        return new Trader(owner,goodsList,address);
    }
}
