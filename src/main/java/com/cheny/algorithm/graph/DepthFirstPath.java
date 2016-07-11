package com.cheny.algorithm.graph;

import java.util.Stack;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class DepthFirstPath {

    private boolean[] marked;   //该顶点是否调用过深度搜索
    private int[] edgeTo;       //从起点到顶点i的已知路径上的最后一个顶点
    private final int start;

    public DepthFirstPath(Graph G , int start){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.start = start;

    }

    private void dfs(Graph G ,int start){
        marked[start] = true;
        for(int w : G.adj(start)){
            if(!marked[w]){
                edgeTo[w] = start;
                dfs(G,w);
            }
        }

    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        for(int i = v ; i != start; i = edgeTo[i]){
            path.push(i);
        }
        path.push(start);
        return path;
    }

}
