public class QuickFind{

//	private int N;//not necessary

	private int id[];
	public QuickFind(int N){
		id=new int[N];
		for(int i=0;i<N;i++){
			id[i]=i;
		}
	}

	public void showIDs(){
		for(int i=0;i<id.length;i++) System.out.println(id[i]);
	}

	public boolean find(int p, int q){return id[p]==id[q];}
	public void union(int p, int q){
		int pid=id[p];
		int qid=id[q];
		if(pid==qid) return;
		//id[p]=qid;
		for(int i=0;i<id.length;i++){
			if(id[i]==pid) id[i]=qid;
		}
	}	

}
