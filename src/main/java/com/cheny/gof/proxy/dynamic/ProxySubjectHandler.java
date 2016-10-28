package com.cheny.gof.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 *     动态代理 类是通过反射生成的,具体生成是根据提供的接口来的.
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ProxySubjectHandler implements InvocationHandler {

    private Subject subject;

    public ProxySubjectHandler(Subject subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//        if(null == subject){
//            subject = new RealSubject();
//        }
//        subject.request();
//        return null;
        System.out.println("Hello,World");

        return method.invoke(subject,args);
    }
}
