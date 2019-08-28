import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points){
        if(points == null)
            throw new IllegalArgumentException();

        int len = points.length;
        Point[] tmp = new Point[len];

        for(int i = 0; i < len; i++) {
            if (points[i] != null) // if any argument is null, throw exception
                tmp[i] = points[i];
            else
                throw new IllegalArgumentException();
        }

        Arrays.sort(tmp); // sort before identifying repeated points
        if(RepeatedPoints(tmp))
            throw new IllegalArgumentException();

        ArrayList<LineSegment> seg = new ArrayList<>();

        for(int p = 0; p < len-3; p++){
            // for a given point p, sort tmp by slopeOrder of all other points compare to p
            Arrays.sort(tmp);
            Arrays.sort(tmp, tmp[p].slopeOrder());

//            for(Point k: tmp){
//                System.out.println(k);
//            }
//
//            System.out.println();
//            System.out.println();
//            System.out.println();

            for(int i = 0, first = 1, last = 2; last < len; last++) {
                // if current slope of i with last
                while (last < len &&
                        Double.compare(tmp[i].slopeTo(tmp[first]), tmp[i].slopeTo(tmp[last])) == 0) {
                last++;
            }
            // if found at least 3 elements mark as one segment
            if (last - first >= 3 && tmp[i].compareTo(tmp[first]) < 0) {
                seg.add(new LineSegment(tmp[i], tmp[last - 1]));
            }
            // start again from last
                first = last;
            }
        }
        segments = seg.toArray(new LineSegment[seg.size()]);
    }

    // the number of line segments
    public int numberOfSegments(){
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments(){
//        LineSegment[] seg = segments;
        LineSegment[] seg = segments.clone();
        return seg;
    }

    private boolean RepeatedPoints(Point [] points){
        for(int i = 0; i < points.length-1; i++){
            if(points[i].compareTo(points[i+1]) == 0)
                return true;
        }
        return false;
    }

//    public static void main(String[] args) {
//
//        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
//
//        // draw the points
////        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
//
////        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
//    }
}
