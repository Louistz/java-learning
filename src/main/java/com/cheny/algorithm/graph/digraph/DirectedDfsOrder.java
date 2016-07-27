package com.cheny.algorithm.graph.digraph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class DirectedDfsOrder {


    private boolean marked[];

    private Queue<Integer> pre;           //前序排列
    private Queue<Integer> post;          //后序排列
    private Stack<Integer> reversePost;   //逆后序排列

    public DirectedDfsOrder(Digraph G){

        this.marked = new boolean[G.V()];
        this.pre = new ArrayDeque<>();
        this.post = new ArrayDeque<>();
        this.reversePost = new Stack<>();

        for(int v = 0; v<G.V(); v++){
            if(!marked[v]){
                dfs(G,v);
            }
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;

        pre.add(v);
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    public Stack<Integer> reversePost(){
        return reversePost;
    }

}
