package com.cheny.java8.infterface;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class DefaultInterfaceImpl implements DefaultInterface {

    @Override
    public String test() {
        return "test";
    }

    public static void main(String[] args) {
        DefaultInterfaceImpl l = new DefaultInterfaceImpl();
        System.out.println(l.hello());
        System.out.println(l.test());
    }
}
