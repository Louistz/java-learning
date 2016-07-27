package com.cheny.algorithm.graph.digraph;

import com.cheny.algorithm.graph.Graph;
import edu.princeton.cs.introcs.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>有向图</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Digraph implements Graph{

    private final int v;
    private int edge;

    private List<Integer>[] adj;


    public Digraph(int v){
        this.v = v;
        this.edge = 0;
        adj = new List[v];
        for(int i=0;i<v;i++){
            adj[i] = new ArrayList<>();
        }
    }

    public Digraph reverse(){
        Digraph r = new Digraph(v);
        for(int i = 0 ;i < v ; i++){
            for(int w : adj(i)){
                r.addEdge(w,i);
            }
        }
        return r;
    }

    @Override
    public int V() {
        return v;
    }

    @Override
    public int E() {
        return edge;
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        edge ++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
