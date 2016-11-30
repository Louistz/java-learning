package com.cheny.algs4.wk2_bag_queue_stack;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <p>
 * Subset
 * </p>
 *
 * @author chenyong
 * @version 1.0
 * @since 1.0
 */
public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (StdIn.hasNextLine() && !StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        Iterator<String> iterator = queue.iterator();
        for (int i = 0; i < k; i++) {
            StdOut.println(iterator.next());
        }
    }
}
