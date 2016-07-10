package com.cheny.algorithm.search;

import java.util.Iterator;

/**
 *  * <p>红黑树</p>
 *
 *  1:“平衡”：所有空连接(对应叶子节点)到根节点的距离相同。 树的高度维持在lgN .
 *  2: 2-3平衡树的实现过于繁琐，考虑红黑树.
 *  3: 红黑树的思想：用标准二叉树（全部2-节点构成）和一些额外信息（代替3-节点）来表示2-3树。
 *
 * 红黑树的思想：
 * 用标准二叉树（全部2-节点构成）和一些额外信息（代替3-节点）来表示2-3树。
 *
 *            下面是一棵2-3树：
 *                                       （  M  ）
 *                                      /        \
 *                           3-节点 - (E J)       (R) - 2-节点
 *                                 /   |  \      /  \
 *                              (A C) (H) (L)  (P)   (S X)
 *            红黑树：由一个红色的左链接相连的两个2-节点来表示一个3-节点 。 来将一个2-3树转为一个红黑树
 *                                          （a      b）
 *                                         /     |     \
 *                                     (x<a) (a<x<b)  (x>b)
 *                                           转换为:
 *                                      [a]   =   (b)
 *                                    /    \        \
 *                                (x<a)  (a<x<b)    (x>b)
 *
 *
 *
 *  红黑树定义(从链接的角度)：
 *  1、红链接均为左链接
 *  2、没有任何一个节点同时与两个红链接相连
 *  3、该树完美黑色平衡。 任意空连接到根节点的路径上的黑色链接数量相同
 *
 *  红黑树定义(从节点的角度)：
 *  １、每个结点或是红的，或是黑的。
 *  ２、根结点是黑的。
 *  ３、每个叶结点（NIL）是黑的。
 *  ４、如果一个结点是红的，则它的两个儿子都是黑的。
 *  ５、对每个结点，从该结点到其子孙的所有路径上包含相同数目的黑结点。
 *
 *
 *  为什么需要左旋和右旋？
 *  因为
 *
 */
public class RedBlackBST<K extends Comparable,V> implements ST<K,V>{

    private static final boolean RED     = true;
    private static final boolean BLACK   = false;

    private Node root;

    private class Node{
        private K key;                 //键
        private V value;               //值
        private Node left,right;      //左右子节点
        private boolean color;        //父节点指向该节点的链接颜色
        private int n;                 //该节点树的节点总数

        public Node(K key, V value, boolean color, int n){
            this.key = key;
            this.value = value;
            this.color = color;
            this.n = n;
        }
    }

    public boolean isRed(Node node){
        if(null == node) return false;
        return node.color == RED;
    }

    public Node rotateLeft(Node node){
        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        x.n = node.n;
        node.n = size(node.left) + size(node.right) + 1;
        return x;
    }

    public Node rotateRight(Node node){
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        x.n = node.n;
        node.n = size(node.left) + size(node.right) + 1;
        return x;
    }

    public void flipColors(Node n){
        n.left.color = BLACK;
        n.right.color = BLACK;
        n.color = RED;
    }


    @Override
    public void put(K k, V v) {
        root = put(root,k,v);
        root.color = BLACK;
    }

    public Node put(Node node ,K k , V v){
        if(null == node){
            return new Node(k,v,RED,1);
        }
        int cmp = k.compareTo(node.key);
        if(cmp < 0) node.left = put(node.left,k,v);
        else if(cmp > 0) node.right = put(node.right,k,v);
        else node.value = v;

        if(isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        //颜色转换后，有可能会出现红色的右节点。但这是个递归方法，会一直进行红链接传递，传递到最上层，有可能导致根节点是红色，
        // 因此，需要将根节点设置为黑色 。
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public V get(K k) {
        return get(root,k);
    }

    public V get(Node node, K k){
        if(null == node){
            return null;
        }
        int cmp = k.compareTo(node.key);
        if(cmp > 0){
            return get(node.right,k);
        }else if(cmp < 0) {
            return get(node.left,k);
        }else {
            return node.value;
        }
    }

    @Override
    public boolean contains(K k) {
        return false;
    }

    @Override
    public void delete(K k) {

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
        return size(root);
    }

    public int size(Node node){
        if(null == node) return 0;
        return size(node.left) + size(node.right) + 1;
    }
}
