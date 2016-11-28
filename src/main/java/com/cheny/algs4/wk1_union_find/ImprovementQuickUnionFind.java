package com.cheny.algs4.wk1_union_find;

/**
 * <p>
 *     1.将小树挂到大树,减小树的高度. 使得节点最高为 lgN
 *     2. path compression : 路径压缩
 *
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class ImprovementQuickUnionFind {
    private int[] ids;
    private int[] sz;
    private int components;

    public ImprovementQuickUnionFind(int N){
        ids = new int[N];
        for(int i =0;i<N;i++){
            ids[i] = i;
            sz[i] = 1;
        }
        components = N;
    }

    //通过将子节点挂到其grandparent 节点,压缩路径. 使得树尽可能更平缓
    private int root(int p){
        while (p != ids[p]){
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);

        if(sz[i] > sz[j]){
            ids[j] = i;
            sz[i] += sz[j];
        }else{
            ids[i] = j;
            sz[j] += sz[i];
        }
        components --;
    }


}
