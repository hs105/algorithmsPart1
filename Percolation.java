
/*
    A percolation class that conducts site control operation and provide interface 
    for judging whether the site world percolates 
*/
public class Percolation  {

    /*    create an N by N grid with two auxiliary grids: 
        one is at top and the other at bottom; 
        the two artif icial grids are always open
    */
    private final int N;
    private final boolean[][] A;
    private WeightedQuickUnionUF uf;
    
    public Percolation(int N) throws Exception {
        if (N  <= 0) 
            throw    new IllegalArgumentException("parameter must be positive.");
        this.N = N;
        A = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N*N + 2); 
    }
   
    //a helper function that returns the 1-based index of a grid i, j
    private int indexOfGrid(int i,  int j) throws Exception {
        if (i > N || i <= 0 || j > N || j <= 0) 
            throw new IndexOutOfBoundsException("Parameter i, j must be in [1, N]");
        return (i-1)*N +j -1 +1; 
            //plus because 0 is reserved for the top artif icial grid 
    }

    //open cell (i, j)
    public void open(int i,  int j) throws Exception {
        if (i > N || i <= 0 || j > N || j <= 0)  
            throw new IndexOutOfBoundsException("Parameter must be between [1, N]");
        if (!isOpen(i, j)) {
            A[i-1][j-1] = Boolean.TRUE;
            
            //union with the top and bottom grids
            int index = indexOfGrid(i, j);
            if (i == 1) { 
                uf.union(index, 0);
            }
            if (i == N) {
                uf.union(index, N*N+1);
            }

            //union with neighboring grids
            if (i > 1) {
                if (isOpen(i-1, j)) {
                    uf.union(index,  indexOfGrid(i-1, j));
                }
            }
            
            if (i < N) {
                if (isOpen(i+1, j))  {
                    uf.union(index,  indexOfGrid(i+1, j));
                }
            }

            if (j > 1) {
                if (isOpen(i, j-1))  {
                    uf.union(index,  indexOfGrid(i,  j-1));
                }
            }
            
            if (j < N) {
                if (isOpen(i, j+1)) {
                    uf.union(index,  indexOfGrid(i,  j+1));
                }
            }

        }
    }

    //test whehter cell (i, j) is open
    public boolean isOpen(int i,  int j) throws Exception {
        if (i > N || i <= 0 || j > N || j <= 0) 
            throw new IndexOutOfBoundsException("Parameter must be between [1, N]");
        return A[i-1][j-1];
    }
    
    /*
        test whehter cell (i, j) is full
        A full site is an open site that can be 
        connected to an open site in the top row
    */
    public boolean isFull(int i,  int j) throws Exception {
        if (i > N || i <= 0 || j > N || j <= 0) 
            throw new IndexOutOfBoundsException("Parameter must be between [1, N]");
        return isOpen(i, j) && uf.connected(indexOfGrid(i, j), 0);
    }
    
    /* 
        test whether the grid percolates: 
            can be quickly done because of the two artif icial grids. 
            otherwise need to check for whether the top line 
            is connected to the bottom line which is N*N complexity
    */
    public boolean percolates() throws Exception {
        //the two top and bottom artif icial grids connnected or not
        //cornercases
        if (N == 1) return isOpen(1, 1);
        if (N == 2) 
            return (isOpen(1, 1) && isOpen(2, 1)) || (isOpen(1, 2) && isOpen(2, 2));

        return uf.connected(0,  N*N+1); 
    }


    //test client
    public static void main(String[] args) {
    
    }

}
