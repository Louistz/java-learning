package com.cheny.gof.visitor;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface Visitor {

    public void visit(NodeA n);

    public void visit(NodeB n);
}
