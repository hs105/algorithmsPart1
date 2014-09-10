public class QuickFindTest{
	public static void main(String[] args){
		QuickFind qf=new QuickFind(10);
		assert(qf.find(1,2)==false);
		//qf.union(1,2);
		//assert(qf.find(1,2));
		qf.union(4,3);
		qf.union(3,8);
		qf.union(6,5);
		qf.union(9,4);
		qf.union(2,1);
		qf.union(8,9);
		qf.union(5,0);
		qf.union(7,2);
		qf.union(6,1);

		qf.showIDs();
	}

}
