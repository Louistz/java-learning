package com.cheny.ddd.application;

import com.cheny.ddd.domain.model.Address;
import com.cheny.ddd.domain.model.Goods;
import com.cheny.ddd.domain.model.Owner;
import com.cheny.ddd.domain.model.Shipper;
import com.cheny.ddd.domain.model.Trader;
import com.cheny.ddd.domain.service.TradeService;

import java.util.List;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TradeApplication {

    public Trader crateTrader(Owner owner,List<Goods> goodsList,Address address){
        return new TradeService().open(owner,goodsList,address);
    }

    public void fetchTrader(Shipper shipper,Trader trader){
        new TradeService().fetch(shipper,trader);
    }
}
