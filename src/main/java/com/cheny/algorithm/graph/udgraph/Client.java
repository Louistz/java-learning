package com.cheny.algorithm.graph.udgraph;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

import java.io.File;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) {
        File file = new File("src/main/resources/tinyG.txt");
        Graph G = new AdjListGraph(new In(file));
        int s = 0;
        BreadthFirstPath path = new BreadthFirstPath(G,s);
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
