// import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n; // grid size n
    private boolean [][] gridarr;  // block or not
    private final WeightedQuickUnionUF uf; // percolates
    private final WeightedQuickUnionUF ufull; // for isFull()
    private final int sz; // total number of nodes
    private int count = 0; // number of open sites

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n <= 0)
            throw new java.lang.IllegalArgumentException();

        gridarr = new boolean[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                gridarr[i][j]= false;
            }
        }

        this.n = n;
        sz = n*n + 2; // add two additional parent nodes
        uf = new WeightedQuickUnionUF(sz); // empty union–find data structure, each site in its own component
        ufull = new WeightedQuickUnionUF(sz - 1); // all besides the top one

        for (int i = 1; i <= n; ++i) {
            uf.union(0, i); // now union top node with all i's in the first row
            uf.union(sz-1, sz-1-i); // union bottom node with all (sz-1)-i's in the last row
            ufull.union(0, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(row < 1 || col < 1 || row > n || col > n)
            throw new java.lang.IllegalArgumentException();

        if(gridarr[row-1][col-1] ==  false) {
            gridarr[row - 1][col - 1] = true;
            count++;


            int idx = getIndex(row, col);

            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(getIndex(row - 1, col), idx);
                ufull.union(getIndex(row - 1, col), idx);
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(getIndex(row + 1, col), idx);
                ufull.union(getIndex(row + 1, col), idx);
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(getIndex(row, col - 1), idx);
                ufull.union(getIndex(row, col - 1), idx);
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(getIndex(row, col + 1), idx);
                ufull.union(getIndex(row, col + 1), idx);
            }
        }

    }

    private int getIndex(int i, int j) {
        return (i - 1) * n + j;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 1 || row > n || col < 1 || col > n)
            throw new java.lang.IllegalArgumentException();
        return gridarr[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int i, int j){
        if (i < 1 || i > n || j < 1 || j > n)
            throw new java.lang.IllegalArgumentException();
        return isOpen(i,j) && ufull.connected(getIndex(i,j), 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        if (n == 1)
            return isOpen(1,1); // corner case n = 1
        return uf.connected(0, sz-1);
    }


    // test client (optional)
    public static void main(String[] args) {
//        StdDraw.clear();
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.setXscale(-.05*3, 1.05*3);
//        StdDraw.setYscale(-.05*3, 1.05*3);   // leave a border to write text
//        StdDraw.filledSquare(3/2.0, 3/2.0, 3/2.0);
//
//        // draw N-by-N grid
//        // int opened = 0;
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                StdDraw.setPenColor(StdDraw.BLACK);
//                StdDraw.filledSquare(col - 0.5, 3 - row + 0.5, 0.45);
//            }
//        }
    }

}
