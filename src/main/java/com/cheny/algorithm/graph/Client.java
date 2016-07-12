package com.cheny.algorithm.graph;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {
        Graph G = new AdjListGraph(new In("/Users/chenyong/wkspace/TDA/java-learning/src/main/java/com/cheny/algorithm/graph/tinyG.txt"));
        int s = 9;
        DepthFirstPath path = new DepthFirstPath(G,s);
        for(int v = 0; v < G.V();v++){
            StdOut.print(s+" to " + v + ": ");
            if(path.hasPathTo(v)){
                for(int x : path.pathTo(v)){
                    if(x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
                }
            }
            StdOut.println();
        }
    }
}
