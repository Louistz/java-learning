package com.cheny.gof.observer;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface Observer {

    public void update(Observable observable, Object args);
}
