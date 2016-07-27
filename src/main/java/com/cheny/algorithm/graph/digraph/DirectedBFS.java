package com.cheny.algorithm.graph.digraph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <p>有向图广度遍历</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class DirectedBFS {

    private boolean[] marked;

    public DirectedBFS(Digraph G , int s){
        marked = new boolean[G.V()];
        bfs(G,s);
    }

    public void bfs(Digraph G ,int s){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()){
            Integer v = queue.poll();
            for(int w : G.adj(v)){
                if(!marked[w]){
                    queue.add(w);
                    marked[w] = true;
                }

            }

        }




    }
}
