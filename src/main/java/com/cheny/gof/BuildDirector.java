package com.cheny.gof;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BuildDirector {
    Builder builder = new ConcreteBuilder();
    public Object getAProduct(){

        builder.setAttribute("宝马","x7");
        return builder.getProduct();
    }

    public Object getBProduct(){
        builder.setAttribute("奥迪","Q5");
        return builder.getProduct();
    }
}
