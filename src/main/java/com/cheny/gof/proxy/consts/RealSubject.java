package com.cheny.gof.proxy.consts;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("real subject do something.");
    }
}
