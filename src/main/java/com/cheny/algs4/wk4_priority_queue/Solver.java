package com.cheny.algs4.wk4_priority_queue;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

/**
 * <p>
 * Solver
 * goal board的逆序对数是0,偶数
 * block滑动不会改变board的逆序对数奇偶性.而交换相邻的block则会.
 * 因为,我们同步去摸索board和board twin的可达路径.
 * 一个可达另外一个必不可达,但总有一个会达到Goal board.
 * </p>
 *
 * @author chenyong
 * @version 1.0
 * @since 1.0
 */
public class Solver {

    private ArrayList<Board> solution;
    private boolean solvable = false;
    private int moves;

    public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
        if (initial == null) throw new java.lang.NullPointerException();

        ArrayList<Board> processed = new ArrayList<>();
        MinPQ<Node> searchQueue = new MinPQ<>();
        searchQueue.insert(new Node(initial, 0, null));
        Node current = null;


        ArrayList<Board> twinProcessed = new ArrayList<>();
        MinPQ<Node> twinSearchQueue = new MinPQ<>();
        twinSearchQueue.insert(new Node(initial.twin(), 0, null));
        Node twinCurrent = null;

        while (!searchQueue.isEmpty() && !twinSearchQueue.isEmpty()) {
            // board
            current = searchQueue.delMin();
            processed.add(current.board);
            if (current.board.isGoal()) {
                solvable = true;
                moves = current.move;
                break;
            } else {
                Iterable<Board> neighbors = current.board.neighbors();

                for (Board neighbor : neighbors) {
                    boolean shouldAdd = true;
                    if (shouldAdd && current.prev != null && neighbor.equals(current.prev.board)) {
                        shouldAdd = false;
                    }
                    if (shouldAdd && !processed.isEmpty()) {
                        for (Board process : processed) {
                            if (process.equals(neighbor)) {
                                shouldAdd = false;
                                break;
                            }
                        }
                    }

                    if (shouldAdd) {
                        searchQueue.insert(new Node(neighbor, current.move + 1, current));;
                    }
                }
            }

            // twinBoard
            twinCurrent = twinSearchQueue.delMin();
            twinProcessed.add(twinCurrent.board);
            if (twinCurrent.board.isGoal()) {
                break;
            } else {
                Iterable<Board> neighbors = twinCurrent.board.neighbors();
                for (Board neighbor : neighbors) {
                    boolean shouldAdd = true;
                    if (shouldAdd && twinCurrent.prev != null
                            && neighbor.equals(twinCurrent.prev.board)) {
                        shouldAdd = false;
                    }
                    if (shouldAdd && !twinProcessed.isEmpty()) {
                        for (Board process : twinProcessed) {
                            if (process.equals(neighbor)) {
                                shouldAdd = false;
                                break;
                            }
                        }
                    }

                    if (shouldAdd) {
                        twinSearchQueue.insert(new Node(neighbor, twinCurrent.move + 1, twinCurrent));;
                    }
                }
            }
        }

        if (solvable) {
            solution = new ArrayList<>();
            Node cur = current;
            while (cur != null) {
                solution.add(cur.board);
                cur = cur.prev;
            }
        }
    }

    public boolean isSolvable() { // is the initial board solvable?
        return solvable;
    }

    public int moves() { // min number of moves to solve initial board; -1 if unsolvable
        if (!solvable) return -1;
        return moves;
    }

    public Iterable<Board> solution() { // sequence of boards in a shortest solution; null if
                                        // unsolvable
        if (!solvable) {
            return null;
        } else {
            return new SolutionBoards();
        }
    }

    private class SolutionBoards implements Iterable<Board> {
        public Iterator<Board> iterator() {
            return new SolutionIter();
        }

        class SolutionIter implements Iterator<Board> {
            private int index = solution.size() - 1;

            public boolean hasNext() {
                return index > -1;
            }

            public Board next() {
                if(index < 0){
                    throw new NullPointerException();
                }
                return solution.get(index--);
            }
        }
    }

    private class Node implements Comparable<Node> {

        private Node prev;
        private Board board;
        private int move;

        private int priority;

        public Node(Board board, int move, Node prev) {
            this.prev = prev;
            this.board = board;
            this.move = move;
            this.priority = this.move + this.board.manhattan();
        }


        public int manhattan() {
            System.out.println(this + ":" + board.manhattan());
            return board.manhattan();
        }

        @Override
        public int compareTo(Node that) {
            return this.manhattan() - that.manhattan();
        }
    }


    public static void main(String[] args) { // solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(new File("src/main/resources/8puzzle/puzzle4x4-80.txt"));
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
