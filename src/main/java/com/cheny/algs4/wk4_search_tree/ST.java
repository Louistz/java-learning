package com.cheny.algs4.wk4_search_tree;


/**
 * <p>search tree</p>
 *
 * elementary implementation:
 * 1. non-ordered array:   insert:O(N) search:O(N)
 * 2. ordered array :  insert: O(N) search:O(N)
 *
 * binary search tree
 *
 *
 * @author  chenyong
 * @version 1.0
 * @since 1.0
 */
public interface ST<Key extends Comparable<Key> ,Value> {

    void put(Key key, Value value);

    Value get(Key key);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    Iterable<Key> keys();

}
