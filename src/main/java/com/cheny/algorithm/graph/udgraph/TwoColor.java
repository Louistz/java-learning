package com.cheny.algorithm.graph.udgraph;

import com.cheny.algorithm.graph.Graph;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorAble = true;

    public TwoColor(Graph G){
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for(int s=0;s<G.V();s++){
            if(!marked[s]){
                dfs(G,s);
            }
        }
    }

    private void dfs(Graph G , int v){
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]){
                color[w] = !color[v];
                dfs(G,w);
            }else if(color[w] == color[v]){
                isTwoColorAble = false;
            }
        }
    }

    public boolean isTwoColorAble(){
        return isTwoColorAble;
    }
}
