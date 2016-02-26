package com.cheny.gof.strategy;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ConcreteStrategy1 implements IStrategy {

    @Override
    public void algorithmInterface() {
        System.out.println("do Something using strategy 1.");
    }
}
