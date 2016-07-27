package com.cheny.algorithm.graph.digraph;

/**
 * <p>有向图深度遍历</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class DirectedDFS {

    private boolean[] marked;

    public DirectedDFS(Digraph G , int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    private void dfs(Digraph G , int s){
        marked[s] = true;
        for(int w : G.adj(s)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
    }
}
