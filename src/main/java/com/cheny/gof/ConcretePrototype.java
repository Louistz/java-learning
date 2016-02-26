package com.cheny.gof;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ConcretePrototype extends Prototype {
    private int i;
    public ConcretePrototype(int i){
        this.i = i;
    }

    public void show(){
        System.out.println("原型实现类:"+i+".");
    }
}
