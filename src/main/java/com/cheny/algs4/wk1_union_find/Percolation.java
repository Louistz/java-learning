package com.cheny.algs4.wk1_union_find;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Percolation {

    private int n;
    private int[][] grids;
    private WeightedQuickUnionUF uf;
    private int top;
    private int bottom;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <=0");
        }
        this.grids = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grids[i][j] = 0;
            }
        }

        this.n = n;
        this.top = n * n;
        this.bottom = n * n + 1;
        this.uf = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
        validate(row, col);
        grids[row - 1][col - 1] = 1;
        if (row == 1) {
            uf.union(top, getQFIndex(row, col));
        }
        if (row == n) {
            uf.union(bottom, getQFIndex(row, col));
        }

        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(getQFIndex(row, col), getQFIndex(row - 1, col));
        }
        if (row < n && isOpen(row + 1, col)) {
            uf.union(getQFIndex(row, col), getQFIndex(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(getQFIndex(row, col), getQFIndex(row, col - 1));
        }
        if (col < n && isOpen(row, col + 1)) {
            uf.union(getQFIndex(row, col), getQFIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grids[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        if(n == 1){
            return isOpen(1,1);
        }
        return  isOpen(row,col) && uf.connected(top, getQFIndex(row, col));
    }

    public boolean percolates() {
        if(n == 1){
            return isOpen(1,1);
        }
        return uf.connected(top, bottom);
    }

    private int getQFIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n) {
            throw new IndexOutOfBoundsException("index " + row + " is not between 1 and " + n);
        }
        if (col < 1 || col > n) {
            throw new IndexOutOfBoundsException("index " + col + " is not between 1 and " + n);
        }
    }
}
