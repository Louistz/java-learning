package com.cheny.ddd.domain.model;

import lombok.Getter;
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
public class Owner extends User {

    private Address currentGPS;
}
