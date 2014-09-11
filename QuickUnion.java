public class QuickUnion{
	private int[] id;
	public QuickUnion(int N){
		id=new int[N];
		for(int i=0;i<id.length;i++) id[i]=i;
	}
	
	public void showIDs(){
		for(int i=0;i<id.length;i++){
			System.out.println(id[i]);
		}
	}

	public boolean connected(int p, int q){ return id[p]==id[q];}
	public void union(int p, int q){
		if(root(p)==root(q)) return;
		id[root(p)]=root(q);
	}
	//find root of the tree that p belongs to
	public int root(int p){
		int i=p;
		while(id[i]!=i){
			i=id[i];
		}
		return i;
	}

}
