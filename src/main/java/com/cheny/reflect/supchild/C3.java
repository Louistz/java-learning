package com.cheny.reflect.supchild;

/**
 * TODO
 * <p>Filename: com.cheny.reflect.supchild.C3.java</p>
 * <p>Date: 2017-07-26 17:01.</p>
 *
 * @author <a href="mailto:chenyong1610@qianmi.com">of1610-chenyong</a>
 * @version V0.0.1
 */
public class C3 extends C2 {

    @Override
    protected String name() {
        System.out.println(super.name());
        return "c3";
    }

    public static void main(String[] args) {

        C3 c= new C3();
        System.out.println(c.name());
    }
}
