package com.cheny.algorithm.search;

import java.util.Iterator;

/**
 * <p>无序KEY</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class SequentialSearchST<K,V> implements ST<K,V>{

    public Node first;

    @Override
    public void put(K k, V v) {
        for(Node n = first;n != null ; n= n.next){
            if(n.getKey().equals(k)){
                n.setValue(v);
                return;
            }
        }
        first = new Node(k,v,first);
    }

    @Override
    public V get(K k) {
        for(Node n = first;n != null ; n= n.next){
            if(n.getKey().equals(k)){
                return (V)n.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean contains(K k) {
        for(Node n = first;n != null ; n= n.next){
            if(n.getKey().equals(k)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(K k) {
        if(first == null){
            return;
        }
        if(first.getKey().equals(k)){
            first = first.next;
            return;
        }

        for(Node n = first;n.next != null ; n= n.next){
            if(n.next.getKey().equals(k)){
                n.next = n.next.next;
                return;
            }
        }
    }

    @Override
    public Iterator keys() {
        Iterator<K> iterator = new Iterator<K>() {

            private Node cursor = new Node(null, null, first);

            @Override
            public boolean hasNext() {
                return cursor.next != null;
            }

            @Override
            public K next() {
                Node next = cursor.next;
                cursor = cursor.next;
                return (K) next.getKey();
            }
        };
        return iterator;
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

    @Override
    public int size() {
        int i=0;
        for(Node n=first;n!=null;n=n.next){
            i++;
        }
        return i;
    }

    private class Node<K,V> {
        private K key;
        private V value;
        private Node next;

        public Node(K k, V v, Node first){
            this.key = k;
            this.value = v;
            this.next = first;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
