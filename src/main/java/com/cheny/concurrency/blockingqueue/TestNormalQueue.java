package com.cheny.concurrency.blockingqueue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class TestNormalQueue {

    public static void main(String[] args){


        Queue<String> queue = new LinkedList<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");

        queue.remove();
        queue.remove();
        queue.remove();
    }
}
