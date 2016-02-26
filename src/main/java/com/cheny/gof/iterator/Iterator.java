package com.cheny.gof.iterator;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface Iterator<E> {

    boolean hasNext();
    E next();
    void remove();
}
