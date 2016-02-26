package com.cheny.gof;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ConcreteBuilder extends Builder {

    private String a1;
    private String a2;

    @Override
    public void setAttribute(String a1, String a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    @Override
    public Object getProduct() {
        return this.a1+this.a2;
    }
}
