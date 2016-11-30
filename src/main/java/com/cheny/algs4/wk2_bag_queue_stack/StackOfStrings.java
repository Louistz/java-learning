package com.cheny.algs4.wk2_bag_queue_stack;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class StackOfStrings implements Stack<String> {

    private int size;
    private Node<String> head;

    public StackOfStrings(){
        this.head = null;
        this.size = 0;
    }

    @Override
    public void push(String item) {
        Node tmp = head;
        Node node = new Node(item,tmp);
        head = node;
        this.size ++;
    }

    @Override
    public String pop() {
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        Node<String> tmp = head;
        head = head.next();
        size --;
        return tmp.item();

    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }
}
