package com.cheny.algs4.wk1_union_find;

/**
 * <p>union find
 * union all takes O(n^2)
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class UF {

    private int[] ids;
    private int components;

    public UF(int N){
        ids = new int[N];
        for(int i=0;i<N;i++){
            ids[i] = i;
        }
        components = N;
    }

    public void union(int p, int q){
        int pid = ids[p];
        int qid = ids[q];
        for(int i=0;i<ids.length;i++){
            if(ids[i] == qid){
                ids[i] = pid;
                components --;
            }
        }
    }


    public boolean connected(int p, int q){
        return ids[p] == ids[q];
    }

    public int find(int p){
        return ids[p];
    }

    public int count(){
        return components;
    }
}
