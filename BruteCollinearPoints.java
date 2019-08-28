import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment [] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

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

        for(int i = 0; i < len-3; i++)
            for(int j = i+1; j < len-2; j++)
                for(int k = j+1; k < len-1; k++)
                    for(int l = k+1; l < len; l++){
                        double slp1 = tmp[i].slopeTo(tmp[j]);
                        double slp2 = tmp[j].slopeTo(tmp[k]);
                        double slp3 = tmp[k].slopeTo(tmp[l]);
                        if(slp1 == slp2 && slp2 == slp3) {
//                            System.out.print(tmp[i] + "  " + tmp[j] + "  "+ tmp[k] + "  ");
//                            System.out.println(tmp[l]);
//                            System.out.println();
                            seg.add(new LineSegment(tmp[i], tmp[l]));
                        }
                    }
        segments = seg.toArray(new LineSegment[seg.size()]);
    }

    // the number of line segments
    public int numberOfSegments(){
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
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
//        // print and draw the line segments
//        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
//    }
}
