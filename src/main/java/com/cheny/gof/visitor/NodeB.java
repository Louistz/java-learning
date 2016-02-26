package com.cheny.gof.visitor;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class NodeB extends Node {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public String operationB(){
        return "NodeB";
    }
}
