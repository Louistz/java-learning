package com.cheny.algorithm.graph;

import com.cheny.gof.iterator.Iterator;

/**
 * <p>图</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface Graph {

    //顶点数
    int V();

    //边数
    int E();

    //添加一条边 v-w
    void addEdge(int v, int w);

    //与v相邻的所有顶点
    Iterable<Integer> adj(int v);

    String toString();
}
