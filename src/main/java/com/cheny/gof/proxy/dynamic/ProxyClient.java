package com.cheny.gof.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ProxyClient {

    public static void main(String[] args) {

        RealSubject realSubject = new RealSubject();
        InvocationHandler handler = new ProxySubjectHandler(realSubject);

        Subject proxySubject = (Subject)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class},handler);

        proxySubject.request();


        Subject noImplProxySubject  = (Subject)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class},handler);
        noImplProxySubject.request();
    }
}
