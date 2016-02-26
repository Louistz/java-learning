package com.cheny.gof.component;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public abstract class Component{

    public void add(Component component){
        throw new UnsupportedOperationException();
    }

    public void remove(Component component){
        throw new UnsupportedOperationException();
    }

    public Component getChild(int i){
        throw new UnsupportedOperationException();
    }

    public void operation(){
        throw new UnsupportedOperationException();
    }
}
