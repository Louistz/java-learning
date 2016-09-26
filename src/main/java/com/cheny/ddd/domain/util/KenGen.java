package com.cheny.ddd.domain.util;

import java.util.UUID;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class KenGen {

    private KenGen(){}

    public static String getId(){
        return UUID.randomUUID().toString();
    }
}
