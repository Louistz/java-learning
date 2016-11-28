package com.cheny.algs4.wk1_union_find;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class PercolationStats {

    private double[] x;
    private int n;
    private int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.x = new double[trials];
        this.n = n;
        this.trials = trials;

        init();
    }

    private void init() {
        int t = -1;
        StdRandom.setSeed(System.currentTimeMillis());
        while (++t < trials) {
            Percolation p = new Percolation(n);
            int index = 0;
            while (index < n * n) {
                int randomRow = StdRandom.uniform(1, n + 1);
                int randomCol = StdRandom.uniform(1, n + 1);
                if (p.isOpen(randomRow, randomCol)) {
                    continue;
                }
                index++;
                p.open(randomRow, randomCol);

                if (p.percolates()) {
                    x[t] = ((double) index) / ((double) (n * n));
                    break;
                }
            }
        }
    }

    public double mean() {
        return StdStats.mean(x);
    }

    public double stddev() {
        return StdStats.stddev(x);
    }

    public double confidenceLo() {
        return (mean() - 1.96 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return (mean() + 1.96 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats pstats = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %f\n", pstats.mean());
        StdOut.printf("stddev                  = %f\n", pstats.stddev());
        StdOut.printf("95%s confidence interval = %f, %f\n", "%", pstats.confidenceLo(),
                pstats.confidenceHi());
    }
}
