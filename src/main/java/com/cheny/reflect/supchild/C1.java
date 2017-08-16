package com.cheny.reflect.supchild;

/**
 * TODO
 * <p>Filename: com.cheny.reflect.supchild.C1.java</p>
 * <p>Date: 2017-07-26 16:59.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public abstract class C1 {

    protected abstract String name();

    public void say(){
        System.out.println("hello" + name());
    }
}
