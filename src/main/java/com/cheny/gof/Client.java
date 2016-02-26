package com.cheny.gof;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {
        ConcretePrototype c = new ConcretePrototype(10);
        for(int i=0;i<10;i++){
            ConcretePrototype cl = (ConcretePrototype)c.clone();
            cl.show();
        }

        BuildDirector director = new BuildDirector();
        System.out.println(director.getAProduct());
        System.out.println(director.getBProduct());
    }
}
