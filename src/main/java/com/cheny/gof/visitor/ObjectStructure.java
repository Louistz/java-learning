package com.cheny.gof.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ObjectStructure {

    private List<Node> nodes = new ArrayList<>();

    public void action(Visitor v){
        for(Node n : nodes){
            n.accept(v);
        }
    }

    public void add(Node n){
        nodes.add(n);
    }
}
