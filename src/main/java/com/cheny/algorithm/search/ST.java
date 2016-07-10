package com.cheny.algorithm.search;

import java.util.Iterator;

/**
 * <p>Symbol Tables</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 *
 * 查找树：
 *1、无序查找 -> 有序查找 -> 二叉树查找 -> 平衡二叉树查找(2-3平衡树) -> 红黑树
 *2、散列表
 *
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
