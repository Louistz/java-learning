package com.cheny.gof.visitor;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ConcreteVisitor implements Visitor {

    @Override
    public void visit(NodeA n) {
        System.out.println(n.operationA());
    }

    @Override
    public void visit(NodeB n) {
        System.out.println(n.operationB());
    }
}
