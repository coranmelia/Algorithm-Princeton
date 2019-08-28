import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
// import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double[] results;
//    private int n;
    private final int trials;
    private static final double CONFIDENCE_95 = 1.96;
    private double mean, sd, intervalLo, intervalHi;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();


//        this.n = n;
        this.trials = trials;
        Percolation p;
        double nSquare = n*n;
        results = new double[trials]; // initialize new array of double's

        for(int i = 0; i < trials; i++) {
            p = new Percolation(n);

            int j, k, numOpen = 0;
            while(!p.percolates()) {
                j = StdRandom.uniform(1, n+1);
                k = StdRandom.uniform(1, n+1);
                if (p.isOpen(j,k))
                    continue;
                p.open(j,k);
                numOpen++;
            }
            results[i] = numOpen / nSquare;
        }

        mean = StdStats.mean(results);
        sd = StdStats.stddev(results);
        intervalLo = mean - CONFIDENCE_95 * stddev() / Math.sqrt(trials);
        intervalHi = mean + CONFIDENCE_95 * stddev() / Math.sqrt(trials);

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return sd;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return intervalLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return intervalHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
//        Stopwatch watch = new Stopwatch();
        PercolationStats ps = new PercolationStats(n, trials);
//        StdOut.printf("running time is: %f \n", watch.elapsedTime());
        StdOut.printf("mean                    = %f%n", ps.mean());
        StdOut.printf("stddev                  = %f%n", ps.stddev());
        StdOut.printf("95%% confidence interval = %f, %f%n", ps.confidenceLo(), ps.confidenceHi());
    }
}
