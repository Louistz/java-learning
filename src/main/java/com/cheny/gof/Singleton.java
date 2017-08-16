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
public class Singleton {

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
