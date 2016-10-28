package com.cheny.gof.proxy.consts;

/**
 * <p>
 *     静态代理是 所执行的task由别人来代理执行,类还是创建的那个类.
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ProxySubject implements Subject {

    private RealSubject realSubject;

    @Override
    public void request() {
        if(null == realSubject){
            realSubject = new RealSubject();
        }
        realSubject.request();
    }
}
