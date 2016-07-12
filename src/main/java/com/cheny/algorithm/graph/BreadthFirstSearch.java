package com.cheny.algorithm.graph;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BreadthFirstSearch {

    private boolean[] marked;
    private int count;

    public BreadthFirstSearch(Graph G , int s){
        marked = new boolean[G.V()];
    }

    public void bfs(Graph G , int s){
        Deque<Integer> deque = new Deque<Integer>() {
            @Override
            public void addFirst(Integer integer) {

            }

            @Override
            public void addLast(Integer integer) {

            }

            @Override
            public boolean offerFirst(Integer integer) {
                return false;
            }

            @Override
            public boolean offerLast(Integer integer) {
                return false;
            }

            @Override
            public Integer removeFirst() {
                return null;
            }

            @Override
            public Integer removeLast() {
                return null;
            }

            @Override
            public Integer pollFirst() {
                return null;
            }

            @Override
            public Integer pollLast() {
                return null;
            }

            @Override
            public Integer getFirst() {
                return null;
            }

            @Override
            public Integer getLast() {
                return null;
            }

            @Override
            public Integer peekFirst() {
                return null;
            }

            @Override
            public Integer peekLast() {
                return null;
            }

            @Override
            public boolean removeFirstOccurrence(Object o) {
                return false;
            }

            @Override
            public boolean removeLastOccurrence(Object o) {
                return false;
            }

            @Override
            public boolean add(Integer integer) {
                return false;
            }

            @Override
            public boolean offer(Integer integer) {
                return false;
            }

            @Override
            public Integer remove() {
                return null;
            }

            @Override
            public Integer poll() {
                return null;
            }

            @Override
            public Integer element() {
                return null;
            }

            @Override
            public Integer peek() {
                return null;
            }

            @Override
            public void push(Integer integer) {

            }

            @Override
            public Integer pop() {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Iterator<Integer> iterator() {
                return null;
            }

            @Override
            public Iterator<Integer> descendingIterator() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        }
    }

    public boolean isMarked(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }
}
