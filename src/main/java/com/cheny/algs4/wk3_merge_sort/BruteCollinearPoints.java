package com.cheny.algs4.wk3_merge_sort;

import java.util.Arrays;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author chenyong
 * @version 1.0
 * @since 1.0
 */
public class BruteCollinearPoints {
    private final static int MIN_LINE_POINTS = 4;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (null == points) {
            throw new NullPointerException();
        }
        Point[] ps = new Point[points.length];
        System.arraycopy(points,0,ps,0,points.length);

        Arrays.sort(ps);
        for (int i = 1; i < ps.length; i++) {
            if (ps[i].compareTo(ps[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        init(ps);
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[this.lineSegments.length];
        System.arraycopy(this.lineSegments,0,segments,0,segments.length);
        return segments;
    }

    private void init(Point[] points) {
        LineSegment[] lineSegments = new LineSegment[points.length * (points.length-1)];

        int cursor = 0;
        for (int i = 0; i <= points.length - MIN_LINE_POINTS; i++) {
            Point origin = points[i];
            for(int j=i+1;j<=points.length-MIN_LINE_POINTS+1;j++){
                Point op = points[j];
                Double slope = origin.slopeTo(op);

                int sp = 2;
                int lastI = j;


                for(int x = j+1;x<points.length;x++){
                    if (Double.compare(slope,origin.slopeTo(points[x])) == 0) {
                        sp++;
                        lastI = x;
                    }
                }

                if(sp >= MIN_LINE_POINTS){
                    lineSegments[cursor++] = new LineSegment(origin,points[lastI]);
                }
            }
        }
        this.lineSegments = new LineSegment[cursor];
        System.arraycopy(lineSegments, 0, this.lineSegments, 0, cursor);
    }
}