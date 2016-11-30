package com.cheny.algs4.wk2_bag_queue_stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <p>
 * RandomizedQueue
 * </p>
 *
 * @author chenyong
 * @version 1.0
 * @since 1.0
 */
public class RandomizedQueue1<Item> implements Iterable<Item> {

    private Node head;
    private int size;

    public RandomizedQueue1() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (null == item) {
            throw new NullPointerException();
        }
        Node h = head;
        head = new Node(item, h);
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int i = 0;
        int index = StdRandom.uniform(0, size);

        Node h;
        Node c = head;
        Node pre = null;
        while (i < index) {
            pre = c;
            c = c.next();
            i++;
        }
        h = c;

        if (c != head) {
            pre.next = c.next();
        } else {
            head = c.next();
        }
        size--;

        return h.item;
    } // remove and return a random item

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int i = 0;
        int index = StdRandom.uniform(0, size);
        Node c = head;
        while (i++ < index) {
            c = c.next();
        }
        return c.item;
    } // return (but do not remove) a random item

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }


    private class Node {
        private Item item;
        private Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public Node next() {
            return this.next;
        }

        public Item item() {
            return item;
        }
    }

    private class QueueIterator implements Iterator<Item> {

        private int cursor = 0;
        private int[] index;

        public QueueIterator() {
            index = new int[size];
            for (int x = 0; x < index.length; x++) {
                index[x] = x;
            }
            StdRandom.shuffle(index);
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node h = head;
            int i = 0;
            while (i < index[cursor]) {
                h = h.next();
                i++;
            }
            cursor++;

            return h.item();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue1<String> randomizedQueue = new RandomizedQueue1<>();

        for (char i = 'A'; i <= 'H'; i++) {
            randomizedQueue.enqueue(String.valueOf(i));
        }

        int f[] = new int[randomizedQueue.size];

        String ch = "B";
        int i = 1;
        while (i <= 8000) {
            Iterator<String> iterator = randomizedQueue.iterator();
            int j = 0;
            String c = "";
            while (iterator.hasNext() && !c.equals(ch)) {
                c = iterator.next();
                j++;
            }
            f[j - 1] += 1;

            i++;
        }
        for (int x = 0; x < f.length; x++)
            System.out.println(f[x]);

    }
}
