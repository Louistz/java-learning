package com.cheny.algorithm.graph.udgraph;

import java.util.*;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BreadthFirstPath {

    private boolean[] marked;
    private final int s;

    private int[] edgeTo;

    public BreadthFirstPath(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    public void bfs(Graph G , int s){
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()){
            Integer v = queue.poll();
            for(int w : G.adj(v)){
                if(!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        for(int i = v ; i != s; i = edgeTo[i]){
            path.push(i);
        }
        path.push(s);

        List<Integer> list = new ArrayList<>();
        while (!path.isEmpty()){
            list.add(path.pop());
        }
        return list;
    }

}
