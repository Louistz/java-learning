package com.cheny.gof.visitor;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public abstract class Node {

    public abstract void accept(Visitor v);
}
