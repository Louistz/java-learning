package com.cheny.algs4.wk2_bag_queue_stack;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class FixedCapacityStackOfStrings implements Stack<String> {

    private String[] s;
    private int index;


    public FixedCapacityStackOfStrings(int size){
        s = new String[size];
        this.index = 0;
    }

    @Override
    public void push(String item) {
        s[index++] = item;
    }

    @Override
    public String pop() {
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        String item = s[--index];
        s[index] = null;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public int size() {
        return index;
    }
}
