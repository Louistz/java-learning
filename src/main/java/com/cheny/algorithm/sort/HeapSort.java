package com.cheny.algorithm.sort;

/**
 * <p>堆排序</p>
 *
 * 优先级队列的父节点元素总是大于(小于)左右子节点
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 *
 *                  (T)
 *                /    \
 *              (S)    (R)
 *             /  \    /  \
 *           (P)  (N) (O) (A)
 *
 */
public class HeapSort extends AbstractSort {

    /**
     * 构造一个二叉堆,然后不停的移除最大元素即排好了顺序
     * @param a
     */
    @Override
    public void sort(Comparable[] a) {
        MaxPQ heap = new MaxPQ(a);

        int i=a.length;
        while (!heap.isEmpty()){
            a[--i] = heap.popMax();
        }
    }

    /**
     * 二叉堆,优先级队列
     * @param <T>
     */
    private class MaxPQ<T extends Comparable> {
        /***根元素从1开始,0不放值***/

        private T[] pq;
        private int n = 0;

        public MaxPQ(int maxN){
            pq = (T[])(new Comparable[maxN + 1]);
        }

        public MaxPQ(Comparable[] values){
            pq = (T[])(new Comparable[values.length + 1]);
            for(int i =0 ;i<values.length;i++){
                insert((T)values[i]);
            }
        }

        public boolean isEmpty(){
            return n == 0;
        }

        public int size(){
            return n;
        }

        public void insert(T t){
            pq[++n] = t;
            swim(n);
        }

        public T popMax(){
            T t = pq[1];
            exchange(1,n--);
            sink(1);
            pq[n+1] = defaultValue();
            return t;
        }

        /**
         * 排序的实质就是将 堆头最大的元素与最后的元素交换,然后堆头的元素下潜.
         */
        private void sort(){
            while (n > 1){
                exchange(1,n--);
                sink(1);
            }
        }


        /**
         * k位置元素在堆中下潜到适当位置
         * @param k  index
         */
        private void sink(int k){
            while (k*2 < n){
                int i = k*2;
                if(less(i,i+1)){
                    i++;
                }
                if(less(i,k)) {
                    break;
                }
                exchange(k,i);
                k = i;
            }
        }

        /**
         * k 位置元素上浮到适当位置
         * @param k
         */
        private void swim(int k){
            while ( k > 1 && less(k/2,k)){
                exchange(k/2,k);
                k = k/2;
            }
        }

        private void exchange(int i, int j){
            T v = pq[i];
            pq[i] = pq[j];
            pq[j] = v;
        }

        private boolean less(int i, int j){
            return pq[i].compareTo(pq[j]) < 0;
        }

        private T defaultValue(){
            return null;
        }
    }

}
