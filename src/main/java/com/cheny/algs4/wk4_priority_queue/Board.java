package com.cheny.algs4.wk4_priority_queue;

import java.lang.Iterable;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

/**
 * <p>
 * Board
 * </p>
 *
 * @author chenyong
 * @version 1.0
 * @since 1.0
 */
public class Board {
    private int[][] blocks;
    private int n;
    private int blankX;
    private int blankY;

    private int manhattan;
    private int hamming;

    public Board(int[][] blocks) { // construct a board from an n-by-n array of blocks\
        if(null == blocks){
            throw new NullPointerException();
        }
        n = blocks.length;
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.blocks[i][j] = blocks[i][j];

                if (blocks[i][j] != 0) {
                    int io = (blocks[i][j] - 1) / dimension();
                    int jo = (blocks[i][j] - 1) % dimension();
                    manhattan += Math.abs(i - io) + Math.abs(j - jo);
                }
                if (blocks[i][j] != 0 && blocks[i][j] != position(i, j)) {
                    hamming++;
                }

                if (0 == blocks[i][j]) {
                    blankX = i;
                    blankY = j;
                }
            }
        }
    }

    public int dimension() { // board dimension n
        return n;
    }

    public int hamming() { // number of blocks out of place
        return hamming;
    }

    public int manhattan() { // sum of Manhattan distances between blocks and goal
        return manhattan;
    }

    public boolean isGoal() { // is this board the goal board?
        return manhattan() == 0;
    }

    public Board twin() { // a board that is obtained by exchanging any pair of blocks
        int x1, y1;
        int x2, y2;
        do {
            x1 = StdRandom.uniform(0, n);
            y1 = StdRandom.uniform(0, n);
            x2 = StdRandom.uniform(0, n);
            y2 = StdRandom.uniform(0, n);
        } while (blocks[x1][y1] == 0 || blocks[x2][y2] == 0 || blocks[x1][y1] == blocks[x2][y2]);

        swap(x1, y1, x2, y2);
        Board twinBoard = new Board(blocks);
        swap(x1, y1, x2, y2);
        return twinBoard;
    }

    public boolean equals(Object o) {// does this board equal y?
        if (this == o) return true;
        if (null == o) return false;
        if (!(o instanceof Board)) return false;

        Board board = (Board) o;
        if (board.n != this.n) {
            return false;
        }
        for (int i = 0; i < this.blocks.length; i++) {
            if (board.blocks[i].length != this.blocks[i].length) {
                return false;
            }
            for (int j = 0; j < this.blocks.length; j++) {
                if (board.blocks[i][j] != this.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {// all neighboring boards
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return new BoardIterator();
            }
        };
    }

    class BoardIterator implements Iterator<Board> {

        private Board[] boards;
        private int size;
        private int index;

        private BoardIterator() {
            boards = new Board[4];
            if (blankX > 0) {
                boards[size++] = swapBoard(blankX - 1, blankY, blankX, blankY);
            }
            if (blankX < dimension() - 1) {
                boards[size++] = swapBoard(blankX + 1, blankY, blankX, blankY);
            }
            if (blankY > 0) {
                boards[size++] = swapBoard(blankX, blankY - 1, blankX, blankY);
            }
            if (blankY < dimension() - 1) {
                boards[size++] = swapBoard(blankX, blankY + 1, blankX, blankY);
            }
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Board next() {
            if (index >= size) {
                throw new NullPointerException();
            }
            return boards[index++];
        }
    }

    public String toString() {// string representation of this board (in the output format specified below)
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%3d", blocks[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int position(int x, int y) {
        return x * dimension() + y + 1;
    }

    private void swap(int x1, int y1, int x2, int y2) {
        int tmp = blocks[x1][y1];
        blocks[x1][y1] = blocks[x2][y2];
        blocks[x2][y2] = tmp;
    }

    private Board swapBoard(int x1, int y1, int x2, int y2) {
        swap(x1, y1, x2, y2);
        Board board = new Board(blocks);
        swap(x1, y1, x2, y2);
        return board;
    }

    public static void main(String[] args) { // unit tests (not graded)
        int[][] blocks = { {11, 2}, {3, 110}};
        Board b = new Board(blocks);
        System.out.println(b);
        Iterator<Board> boardIterator = b.neighbors().iterator();
        while (boardIterator.hasNext()) {
            System.out.println(boardIterator.next());
        }
    }
}
