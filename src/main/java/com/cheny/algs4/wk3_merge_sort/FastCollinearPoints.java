package com.cheny.algs4.wk3_merge_sort;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author chenyong
 * @version 1.0
 * @since 1.0
 */
public class FastCollinearPoints {

    private final static int MIN_LINE_POINTS = 4;
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (null == points || points.length == 0) {
            throw new NullPointerException();
        }

        Point[] ps = new Point[points.length];
        System.arraycopy(points, 0, ps, 0, points.length);

        Arrays.sort(ps);
        for (int i = 1; i < ps.length; i++) {
            if (ps[i].compareTo(ps[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        init(ps);
    }

    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[this.lineSegments.length];
        System.arraycopy(this.lineSegments, 0, segments, 0, segments.length);
        return segments;
    }

    // points has been sorted
    private void init(Point[] points) {

        LineSegment[] lineSegments = new LineSegment[points.length * (points.length - 1)];
        int cursor = 0;

        Point[] copyOfPoints = new Point[points.length];

        for (int i = 0; i <= points.length - MIN_LINE_POINTS; i++) {
            Point origin = points[i];

            // copy the ordered points
            System.arraycopy(points, i, copyOfPoints, i, points.length - i);
            // merge sort is stable
            Arrays.sort(copyOfPoints, i, copyOfPoints.length, origin.slopeOrder());


            for (int lo = i, lh = i; lh < copyOfPoints.length; lh++) {
                // condition1: max-continuous of segment lines with the same slope
                if (origin.slopeTo(copyOfPoints[lo]) != origin.slopeTo(copyOfPoints[lh])) {
                    if (lh - lo >= MIN_LINE_POINTS - 1
                            && !isRepeat(points, i, origin.slopeTo(copyOfPoints[lo]))) {
                        lineSegments[cursor++] = new LineSegment(origin, copyOfPoints[lh - 1]);
                    }
                    lo = lh;
                }
                // condition2: until the last point
                if (lh == copyOfPoints.length - 1
                        && origin.slopeTo(copyOfPoints[lo]) == origin.slopeTo(copyOfPoints[lh])
                        && lh - lo >= MIN_LINE_POINTS - 2
                        && !isRepeat(points, i, origin.slopeTo(copyOfPoints[lo]))) {
                    lineSegments[cursor++] = new LineSegment(origin, copyOfPoints[lh]);
                }
            }
        }

        this.lineSegments = new LineSegment[cursor];
        System.arraycopy(lineSegments, 0, this.lineSegments, 0, cursor);
    }

    /**
     * @param orderedPointsBeforeX ordered points
     * @param x
     * @param slope slope of new segment line
     * @return
     */
    private boolean isRepeat(Point[] orderedPointsBeforeX, int x, Double slope) {
        // the points before x is sorted ,and is smaller , they may be the start of a segment line.
        Point origin = orderedPointsBeforeX[x];
        for (int i = x - 1; i >= 0; i--) {
            if (orderedPointsBeforeX[i].slopeTo(origin) == slope) {
                return true;
            }
        }
        return false;
    }
}
