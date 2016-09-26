package com.cheny.ddd.domain.model;

import com.cheny.ddd.domain.ValueObject;

import lombok.Getter;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
@Getter
public class Address extends ValueObject{

    private String id;

    private String country;
    private String province;
    private String city;
    private String street;
    private String detail;
    private String mark;
}
