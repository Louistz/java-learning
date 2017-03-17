package com.cheny.algs4.wk4_priority_queue;

/**
 * <p>MaxPQ</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface MaxPQ<Key extends Comparable<Key>> {

    void insert(Key key);

    Key delMax();

    boolean isEmpty();

    Key max();

    int size();
}
