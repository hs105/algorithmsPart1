public class QuickUnionTest{
	public static void main(String[] args){

		QuickUnion qu=new QuickUnion(10);
		
		qu.union(4,3);
		qu.union(3,8);
		qu.union(6,5);
		qu.union(9,4);
		qu.union(2,1);
		qu.union(5,0);
		qu.union(7,2);
		qu.union(6,1);
		qu.union(7,3);

		qu.showIDs();
	}

}
