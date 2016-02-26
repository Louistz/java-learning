package com.cheny.gof;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Singleton3 {

    private static volatile Singleton3 instance = null;

    private Singleton3() {}

    public static Singleton3 getInstance() {

        if(null == instance){
            synchronized(Singleton3.class){
                if(null == instance){
                    instance = new Singleton3();
                }
            }

        }
        return instance;
    }
}
