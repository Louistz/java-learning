package com.cheny.algs4.wk1_union_find;

/**
 * <p>
 *     union all takes O(N+)
 *     trees may too tall
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class QuickUnionFind {
    private int[] ids;
    private int components;

    public QuickUnionFind(int N){
        ids = new int[N];
        for(int i =0;i<N;i++){
            ids[i] = i;
        }
        components = N;
    }

    private int root(int p){
//        if(p == ids[p]){
//            return p;
//        }else{
//            return root(ids[p]);
//        }
        while (p != ids[p]) p = ids[p];
        return p;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        ids[i] = j;
        components --;
    }


}
