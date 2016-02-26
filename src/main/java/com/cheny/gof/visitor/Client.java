package com.cheny.gof.visitor;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure o = new ObjectStructure();
        o.add(new NodeA());
        o.add(new NodeB());
        Visitor v = new ConcreteVisitor();
        o.action(v);
    }

}
