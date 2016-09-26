package com.cheny.ddd.domain.service;

import com.cheny.ddd.domain.factory.TraderFactory;
import com.cheny.ddd.domain.model.Address;
import com.cheny.ddd.domain.model.Goods;
import com.cheny.ddd.domain.model.Owner;
import com.cheny.ddd.domain.model.Shipper;
import com.cheny.ddd.domain.model.Trader;
import com.cheny.ddd.domain.persistence.TraderRepository;

import java.util.List;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TradeService {

    public Trader open(Owner owner,List<Goods> goodsList,Address address){
        Trader trader =  TraderFactory.newTrade(owner,goodsList,address);
        TraderRepository.INSTANCE.add(trader);
        return trader;
    }

    public void fetch(Shipper shipper,Trader trader){
        trader.setShipper(shipper);
        trader.setState(Trader.State.FETCHED);
        TraderRepository.INSTANCE.update(trader);
    }

}
