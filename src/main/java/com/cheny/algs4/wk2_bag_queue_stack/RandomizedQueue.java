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
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Object[] elements;
    private int index;

    public RandomizedQueue() {
        elements = new Object[1];
        index = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return index;
    }

    public void enqueue(Item item) {
        if (null == item) {
            throw new NullPointerException();
        }
        if (index == elements.length) {
            resize(elements.length * 2);
        }
        elements[index++] = item;
        int x = StdRandom.uniform(0, index);

        @SuppressWarnings("unchecked")
        Item tmp = (Item) elements[x];
        elements[x] = item;
        elements[index - 1] = tmp;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (index > 0 && index == elements.length / 4) {
            resize(elements.length / 2);
        }
        @SuppressWarnings("unchecked")
        Item item = (Item) elements[--index];
        elements[index] = null;
        return item;
    } // remove and return a random item

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int x = StdRandom.uniform(0, index);
        @SuppressWarnings("unchecked")
        Item item = (Item) elements[x];
        return item;
    } // return (but do not remove) a random item

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }


    private void resize(int n) {
        Object[] copy = new Object[n];

        int min = elements.length < n ? elements.length : n;

        System.arraycopy(elements, 0, copy, 0, min);
        elements = copy;
    }

    private class QueueIterator implements Iterator<Item> {

        private int cursor = 0;
        private int[] rIndex;

        public QueueIterator() {
            rIndex = new int[index];
            for (int x = 0; x < rIndex.length; x++) {
                rIndex[x] = x;
            }
            StdRandom.shuffle(rIndex);
        }

        @Override
        public boolean hasNext() {
            return cursor < index;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            @SuppressWarnings("unchecked")
            Item item = (Item) elements[rIndex[cursor++]];
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();


        while (!randomizedQueue.isEmpty())
            System.out.println(randomizedQueue.dequeue());



        int f[] = new int[8];

        String ch = "D";
        int i = 1;
        while (i <= 8000) {
            for (char m = 'A'; m <= 'H'; m++) {
                randomizedQueue.enqueue(String.valueOf(m));
            }
            int j = 0;
            String c = "";
            while (!randomizedQueue.isEmpty()) {
                c = randomizedQueue.dequeue();
                j++;
                if (c.equals(ch)) {
                    f[j - 1] += 1;
                }
            }


            i++;
        }
        for (int x = 0; x < f.length; x++)
            System.out.println(f[x]);

    }
}
