package com.cheny.algorithm.graph.udgraph;

import com.cheny.algorithm.graph.Graph;

/**
 * <p>连通分量</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class CC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G){

        marked = new boolean[G.V()];
        id = new int[G.V()];
        //从每个节点开始深度遍历
        for(int s = 0; s < G.V(); s++){
            if(!marked[s]){
                count ++;
                dfs(G,s);
            }
        }

    }

    private void dfs(Graph G , int v){
        id[v] = count;
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
    }

    public  boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }
}
