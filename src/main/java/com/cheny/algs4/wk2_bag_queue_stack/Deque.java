package com.cheny.algs4.wk2_bag_queue_stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * Deque
 * </p>
 *
 * @author chenyong
 * @version 1.0
 * @since 1.0
 */
public class Deque<Item> implements Iterable<Item> {

    private Object[] elements;
    private int head;
    private int tail;

    public Deque() {
        this.elements = new Object[2];
    }

    private Deque(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.elements = new Object[n + 1];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return complement(tail - head);
    }

    public void addFirst(Item item) {
        if (null == item) {
            throw new NullPointerException();
        }
        elements[head = (complement(head - 1))] = item;
        if (head == tail) {
            capacity();
        }
    }

    public void addLast(Item item) {
        if (null == item) {
            throw new NullPointerException();
        }
        elements[tail] = item;
        if ((tail = complement(tail + 1)) == head) {
            capacity();
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        @SuppressWarnings("unchecked")
        Item item = (Item) elements[head];
        elements[head] = null;
        head = complement(head + 1);

        if(size() > 0 && size() == elements.length / 4){
            decapacity();
        }

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        tail = complement(tail - 1);
        @SuppressWarnings("unchecked")
        Item item = (Item) elements[tail];
        elements[tail] = null;

        if(size() > 0 && size() == elements.length / 4){
            decapacity();
        }

        return item;
    }

    @Override
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {

            private int index = head;

            @Override
            public boolean hasNext() {
                return complement(tail - index) > 0;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int tmp = index;
                index = complement(index + 1);
                @SuppressWarnings("unchecked")
                Item item = (Item) elements[tmp];
                return item;
            }
        };
    }

    private int complement(int index) {
        if (index >= 0) {
            return index % elements.length;
        } else {
            return index % elements.length + elements.length;
        }
    }

    private void capacity() {
        int newCapacity = elements.length * 2;
        Object[] a = new Object[newCapacity];

        int p = head;
        int n = elements.length;
        int r = n - p;

        copy(elements, p, a, 0, r);
        copy(elements, 0, a, r, p);

        elements = a;
        head = 0;
        tail = n;
    }

    private void decapacity(){
        int newCapacity = size() == 0 ? 2 : size()*2;
        if(newCapacity == elements.length){
            return;
        }
        Object[] a = new Object[newCapacity];

        int h = head;
        int t = tail;
        int n = size();
        if(head < tail){
            copy(elements,h,a,0,n);
        }
        if(head > tail){
            int r = elements.length - h;
           copy(elements,h,a,0,r);
            copy(elements,0,a,r,t);
        }
        elements = a;
        head = 0;
        tail = n;
    }

    private void copy(Object[] a, int startA, Object[] b, int startB, int n) {
        int ax = startA;
        int bx = startB;
        int i = 0;
        while (i++ < n) {
            b[bx++] = a[ax++];
        }
    }

    public static void main(String[] args) {

    }

}
