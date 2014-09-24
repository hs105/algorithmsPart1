
/*
	A percolation class that conducts site control operation and provide interface for judging whether the site world percolates 
*/
public class Percolation {

	//create an N by N grid with two auxiliary grids: one is at top and the other at bottom; the two artificial grids are always open
	private WeightedQuickUnionUF uf;
	
	public Percolation(int N) throws Exception{
		if(N<=0) throw 	new IllegalArgumentException("parameter must be positive.");
		this.N=N;
		A=new boolean[N][N];
		uf=new WeightedQuickUnionUF(N*N + 2);//two auxiliary grids: one at top and one at bottom of the grid.
		initializeGrids();
	}

	//initialize the grids
	private void initializeGrids() throws Exception{
		for(int j=1; j<=N; j++){
			int index=indexOfGrid(1, j);//the first/top row is automatically connected to the (artificial) top grid
			assert (index>=1 & index<=N);
			uf.union(index, 0);
		}
		//uf.showIDs();
		//System.out.println();
		for(int j=1; j<=N; j++){
			int index=indexOfGrid(N, j);//the last row is automatically connected to the (artificial) bottom grid
			uf.union(index, N*N+1);
		}
		//uf.showIDs();
	}

	public WeightedQuickUnionUF getUF(){
		return uf;
	}

	@Override
	public String toString(){
		String s="";
		try{
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(isOpen(i,j)) s+="o";
					else s+="x";
				}
				s+="\n";
			}
		}catch(Exception e){
			System.err.println("Exception:"+e.getMessage());
		}
		return s;
	}

	private final boolean[][] A;
	private final int N;
	private int openSites=0;
	
	//exclusing the two artificial 
	public int numberOfOpenSites(){
		return openSites;
	}

	public int numberOfOpenSites2(){
		int sum=0;
		for(int i=0; i<A.length;i++){
			for(int j=0; j<A[0].length;j++){
				if(A[i][j]) sum++; 
			}
		}
		return sum;
	}

	//a helper function that returns the 1-based index of a grid i,j
	private int indexOfGrid(int i, int j) throws Exception{
		if(i>N || i<=0 || j>N || j<=0) throw new IndexOutOfBoundsException("Parameter i,j must be in [1,N]");
		return (i-1)*N +j -1 +1; //plus because 0 is reserved for the top artificial grid 
	}

	//open cell (i,j)
	public void open(int i, int j) throws Exception{
		if(i>N || i<=0 || j>N || j<=0)  throw new IndexOutOfBoundsException("Parameter must be between [1,N]");
		if(!isOpen(i,j)){
			//System.out.println("opening site "+i+","+j);
				
			A[i-1][j-1]=Boolean.TRUE;
			openSites++;
			
			//union with neighboring grids
			int index=indexOfGrid(i,j);
			if(i>1){
				if(isOpen(i-1,j)){
					//System.out.println("union with up");
					uf.union(index, indexOfGrid(i-1,j));
				}
			}
			
			if(i<N){
				if(isOpen(i+1,j)) {
					//System.out.println("union with down");
					uf.union(index, indexOfGrid(i+1,j));
				}
			}

			if(j>1){
				if(isOpen(i,j-1)) {
					//System.out.println("Union with left");
					uf.union(index, indexOfGrid(i, j-1));
				}
			}
			
			if(j<N){
				if(isOpen(i,j+1)){
					//System.out.println("Union with right");
					uf.union(index, indexOfGrid(i, j+1));
				}
			}

		}
	}

	//test whehter cell (i,j) is open
	public boolean isOpen(int i, int j) throws Exception{
		if(i>N || i<=0 || j>N || j<=0) throw new IndexOutOfBoundsException("Parameter must be between [1,N]");
		return A[i-1][j-1];
	}
	
	//test whehter cell (i,j) is filled
	public boolean isFull(int i, int j) throws Exception{
		return !isOpen(i,j);
	}
	
	//test whether the grid percolates: can be quickly done because of the two artificial grids. otherwise need to check for whether the top line is connected to the bottom line which is N*N complexity
	public boolean percolates(){
		return uf.connected(0, N*N+1);//the two top and bottom artificial grids connnected or not
	}


	//test client
	public static void main(String[] args){
	
	}

}
