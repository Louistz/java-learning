package com.cheny.ddd.domain.model;

import com.cheny.ddd.domain.Entity;
import com.cheny.ddd.domain.util.FeeCalculator;
import com.cheny.ddd.domain.util.KenGen;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class Trader extends Entity{


    public Trader(Owner owner,List<Goods> goodsList,Address address){
        this.id = KenGen.getId();
        this.owner = owner;
        this.goodsList = goodsList;
        this.address = address;
        this.fee = FeeCalculator.calculateFee(owner.getCurrentGPS(),address);
        this.state = State.OPENED;
    }

    public void fetch(Shipper shipper){
        this.shipper = shipper;
    }


    private String id;
    private List<Goods> goodsList;

    private Owner owner;
    private Shipper shipper;

    private Address address;

    private BigDecimal fee;

    private State state;

    public enum State {

        OPENED(1),
        PAYED(2),
        FETCHED(3),
        OBTAINED(4),
        DELIVERED(5),
        CLOSED(6),
        CANCELED(7);

        private State(int value){
            this.value = value;
        }

        private int value;
        public int getValue(){
            return value;
        }
    }
}
