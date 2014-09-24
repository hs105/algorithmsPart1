public class PercolationTest{
	
	public static void main(String[] args) throws Exception{
		Percolation p=new Percolation(2);
	
		/*p.open(1,1);
		System.out.println(p);
		p.open(2,2);
		System.out.println(p);
		assert !p.percolates();
		p.open(1,2);
		assert(p.percolates());
		*/
		/*
		p.open(1,2);
		p.open(2,2);
		System.out.println(p);
		assert p.percolates();
		assert p.numberOfOpenSites()==2;
		*/

		

		WeightedQuickUnionUF qu=p.getUF();
		assert qu.connected(0,1);
		assert qu.connected(0,2);
		assert qu.connected(5,3);
		assert qu.connected(5,4);
		
		//qu.connected(6,5);

	
	}
}
