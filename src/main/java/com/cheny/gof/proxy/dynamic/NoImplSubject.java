package com.cheny.gof.proxy.dynamic;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class NoImplSubject {

    public void request(){
        System.out.println("我没有去实现subject,但我有和subject相同的 reqeust方法,我也可以被代理执行");
    }
}
