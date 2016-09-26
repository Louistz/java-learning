package com.cheny.ddd.domain.util;

import com.cheny.ddd.domain.model.Address;

import java.math.BigDecimal;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class FeeCalculator {

    private FeeCalculator(){}

    public static BigDecimal calculateFee(Address startGPS, Address endGPS){
        return new BigDecimal("100");
    }
}
