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
public class Goods extends ValueObject{
    private String id;
    private String description;
}
