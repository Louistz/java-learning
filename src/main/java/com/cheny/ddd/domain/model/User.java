package com.cheny.ddd.domain.model;

import com.cheny.ddd.domain.Entity;
import com.cheny.ddd.domain.ValueObject;

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
public class User extends ValueObject{

    private String id;
    private String name;
    private String telephoneNumber;
    private List<Address> addressList;
}
