package com.cheny.algorithm.search;

import java.util.Iterator;

/**
 * <p>二叉查找树(BST)</p>
 * <p>定义:一颗二叉树,每个节点都包含可比较的键(及其值),
 * 且每个节点的键都大于左子树的任意节点,小于右子树的任意节点</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BST<K extends Comparable,V> implements ST<K,V> {

    private Node root;

    @Override
    public void put(K k, V v) {
        root = put(root,k,v);
    }

    private Node put(Node node , K k , V v){
        if(null == node) return new Node(k,v,1);

        int cmp = k.compareTo(node.key);
        if(cmp < 0) {
            node.left = put(node.left,k,v);
        }else if(cmp > 0){
            node.right = put(node.right,k,v);
        }else{
            node.value = v;
        }
        node.n = node.left.n + node.right.n + 1;
        return node;
    }

    @Override
    public V get(K k) {
        return get(root,k);
    }

    private V get(Node node , K key){
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            return get(node.left,key);
        }else if(cmp > 0){
            return get(node.right,key);
        }else{
            return node.value;
        }
    }

    @Override
    public boolean contains(K k) {
        return contains(root,k);
    }

    private boolean contains(Node node, K key){
        if(node == null){
            return false;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            return contains(node.left, key);
        }else if(cmp > 0){
            return contains(node.right, key);
        }else{
            return true;
        }
    }

    @Override
    public void delete(K k) {

    }

    private void delete(Node node , K key){
        if(node == null){
            return;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            delete(node.left,key);
        }else if(cmp > 0){
            delete(node.right, key);
        }else{
            node = node.left;
        }
    }

    @Override
    public Iterator<K> keys() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        if(null == root){
            return 0;
        }else{
            return root.n;
        }
    }



    private class Node{

        private K key;
        private V value;

        private Node left,right;
        private int n;

        public Node(K key, V value, int n){
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }
}
