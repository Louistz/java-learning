package com.cheny.algorithm.graph.digraph;

/**
 * <p>有向图的强连通性</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class KosarajuSCC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];

        DirectedDfsOrder order = new DirectedDfsOrder(G.reverse());
        for(int s : order.reversePost()){
            if(!marked[s]){
                dfs(G,s);
                count ++;
            }
        }
    }

    private void dfs(Digraph G , int v){
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }

    }


}
