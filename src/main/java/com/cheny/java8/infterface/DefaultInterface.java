package com.cheny.java8.infterface;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface DefaultInterface {

    default String hello(){
        return "hello";
    }

    String test();
}
