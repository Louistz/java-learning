package com.cheny.algorithm.graph.udgraph;

import edu.princeton.cs.introcs.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class AdjListGraph implements Graph{

    private int edge;
    private int vertex;
    private List<Integer>[] adj;

    public AdjListGraph(int v){
        this.vertex = v;
        this.edge = 0;
        adj = new List[v];

        for(int i=0;i<adj.length;i++){
            adj[i] = new ArrayList<Integer>();
        }
    }

    public AdjListGraph(In in){
        this(in.readInt());
        int edge = in.readInt();
        for(int i=0;i<edge;i++){
            int v = in.readInt();
            int w = in.readInt();

            addEdge(v,w);
        }
    }


    @Override
    public int V() {
        return vertex;
    }

    @Override
    public int E() {
        return edge;
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);

        edge ++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
