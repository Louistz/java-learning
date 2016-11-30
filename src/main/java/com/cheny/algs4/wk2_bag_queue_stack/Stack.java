package com.cheny.algs4.wk2_bag_queue_stack;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public interface Stack<T> {

    void push(String item);

    T pop();

    boolean isEmpty();

    int size();

    class Node<T>{
        private T item;
        private Node next;

        public Node(T item,Node next){
            this.item = item;
            this.next = next;
        }

        public boolean hasNext(){
            return this != null && this.next != null;
        }

        public Node next(){
            return this.next;
        }

        public T item(){
            return item;
        }
    }
}
