package com.cheny.algorithm.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        node.n = size(node.left) + size(node.right) + 1;
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

    public K minK(){
        Node minKNode = minKNode(root);
        if(null == minKNode){
            return null;
        }else{
            return minKNode.key;
        }
    }

    private Node minKNode(Node node){
        if(null == node){
            return null;
        }else if( null == node.left){
            return node;
        }else{
            return minKNode(node.left);
        }
    }

    public K select(int k){
        Node n = select(root, k);
        return null == n ? null : n.key;
    }

    //返回排名为k的节点
    public Node select(Node node ,int k){
        if(node == null){
            return null;
        }
        int kcmp = size(node.left);
        if(k < kcmp){
            return select(node.left,k);
        }else if(k>0){
            return select(node.right,k-kcmp-1);
        }else{
            return node;
        }
    }

    public void delMin(){
        root =delMin(root);
    }
    public Node delMin(Node node){
        if(node.left == null){
            return node.right;
        }
        node.left = delMin(node.left);
        node.n = size(node.left) + size(node.right) + 1;
        return node;
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
        root = delete(root,k);
    }


    private Node delete(Node node , K key){
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            node.left = delete(node.left,key);
        }else if(cmp > 0){
            node.right = delete(node.right, key);
        }else{

            //将要被删除的节点如果只有一个左节点或者右节点,返回左或者右节点即可
            if(null == node.left) return node.right;
            if(null == node.right) return node.left;

            //有两个子节点的话, 在右子树中找到最小的那个节点,将其和被删除节点互换,
            // 被删除节点即是这棵右子树的最小节点了,删除右子树的这个最小节点即可.
            Node tmp = node;
            node = min(tmp.right);
            node.left = tmp .left;
            node.right = delMin(node.left);
        }
        //重新计算n值
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node min(Node node){
        if(null == node){
            return null;
        }
        if(node.left == null){
            return node;
        }else{
            return min(node.left);
        }
    }

    @Override
    public Iterator<K> keys() {
        return new Iterator<K>() {

            private int index = 0;
            private List<K> ks = new ArrayList<>(root.n);
            {
                add2List(ks,root);
            }

            @Override
            public boolean hasNext() {
                return index<ks.size();
            }

            @Override
            public K next() {
                return ks.get(index++);
            }

            //将二叉节点的key从小到大放到list中
            private void add2List(List<K> list, Node node){
                if(null == node){
                    return;
                }
                add2List(list,node.left);
                list.add(node.key);
                add2List(list,node.right);
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node){
        return null == node ? 0 : node.n ;
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
