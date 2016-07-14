package com.cheny.algorithm.graph.udgraph;

import com.cheny.algorithm.graph.Graph;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class DepthFirstSearch {

    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G , int S){
        marked = new boolean[G.V()];
    }

    public void dfs(Graph G , int v){
        marked[v] = true;
        count ++;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
    }

    public boolean isMarked(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }

}
