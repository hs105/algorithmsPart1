public class WeightedQuickUnionUFTest{

	public static void main(String[] args){
		WeightedQuickUnionUF qu=new WeightedQuickUnionUF(10);
/*		qu.union(4,3);
		qu.union(3,8);
		qu.union(6,5);
		qu.union(9,4);
		qu.union(2,1);
		qu.union(5,0);
		qu.union(7,2);
		qu.union(6,1);
		qu.union(7,3);
*/
		qu.union(7,4);
		qu.union(8,1);
		qu.union(6,9);
		qu.union(4,9);
		qu.union(0,6);
		qu.union(2,3);
		qu.union(1,3);
		qu.union(2,0);
		qu.union(3,5);

		qu.showIDs();
	}
	


}
