package com.cheny.algs4.wk4_search_tree;

import java.util.Iterator;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class BST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {

    private Node root;

    @Override
    public void put(Key key, Value value) {
        root = put(root,key,value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }

        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = put(node.left, key, value);
        } else if (comp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        return node;
    }

    @Override
    public Value get(Key key) {

        Node node =root;
        while (node != null){
            int comp = key.compareTo(node.key);
            if (comp < 0) {
                node = node.left;
            } else if (comp > 0) {
                node = node.right;
            } else {
                break;
            }
        }
        return null == node ? null : node.value;
    }

    @Override
    public void delete(Key key) {
        root = delete(root,key);
    }

    private Node delete(Node x , Key key){
        if(null == x) return null;

        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = delete(x.left, key);
        } else if (comp > 0) {
            x.right = delete(x.right, key);
        } else {

            if(x.right == null) return x.left;
            if(x.left == null) return x.right;

            Node t = x;

            x = min(t.right);
            x.left = t.left;
            x.right = delMin(t.right);
        }
        return x;
    }

    private Node min(Node x){
        if(x == null){
            return null;
        }
        if(x.left == null){
            return x;
        }
        while (x.left != null){
            x = x.left;
        }
        return x;
    }


    private Node delMin(Node x){
        if(x == null){
            return x;
        }
        if(x.left == null){
            return x.right;
        }
        Node prev = null,curr = x;
        while (curr.left != null){
            prev = curr;
            curr = curr.left;
        }
        prev.left = curr.right;
        return x;
    }

    private Node delMin2(Node x){
        if(x.left == null){
            return x.right;
        }
        x.left = delMin2(x.left);
        return x;
    }

    @Override
    public boolean contains(Key key) {

        return contains(root,key);
    }
    private boolean contains(Node x,Key key){
        if(null == x){
            return false;
        }
        int comp = key.compareTo(x.key);
        if(comp < 0){
            return contains(x.left,key);
        }else if(comp > 0){
            return contains(x.right,key);
        }else {
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        return null == root;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node){
        if(null == node){
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BST<String,Integer> bst = new BST();
        bst.put("S",1);
        bst.put("E",2);
        bst.put("X",3);
        bst.put("A",4);
        bst.put("R",5);
        bst.put("C",6);
        bst.put("H",7);
        bst.put("M",8);


        bst.delete("C");

        System.out.println(bst.size());
    }
}
