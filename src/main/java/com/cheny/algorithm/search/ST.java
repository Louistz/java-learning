package com.cheny.algorithm.search;

import java.util.Iterator;

/**
 * <p>Symbol Tables</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface ST<K,V> {

    void put(K k, V v);

    V get(K k);

    boolean contains(K k);

    void delete(K k);

    Iterator<K> keys();

    boolean isEmpty();

    int size();

}
