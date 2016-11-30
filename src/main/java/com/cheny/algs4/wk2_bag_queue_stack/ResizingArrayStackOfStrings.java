package com.cheny.algs4.wk2_bag_queue_stack;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ResizingArrayStackOfStrings implements Stack<String> {

    private String[] s;
    private int index;


    public ResizingArrayStackOfStrings() {
        s = new String[1];
        this.index = 0;
    }

    @Override
    public void push(String item) {
        if (index == s.length) {
            resize(s.length * 2);
        }
        s[index++] = item;
    }

    @Override
    public String pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        if (index > 0 && index == s.length / 4) {
            resize(s.length / 2);
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

    private void resize(int n) {
        String[] copy = new String[n];
        for (int i = 0; i < n; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
