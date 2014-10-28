import java.util.Iterator;

public class RandomizedQueueTest{
    
    private RandomizedQueue<Integer> rq=new RandomizedQueue<>();
    
    public RandomizedQueue<Integer> getQueue(){
        return rq;
    }
    

    public void sampleTest1() throws Exception{
        rq.enqueue(1);
        assert rq.sample()==1;
    }

    public void sampleTest2() throws Exception{
        enqueueTest();
        getQueue().sample();
        assert getQueue().size()==3;
    }

    public void enqueueTest() throws Exception{
        for(int i=0;i<3;i++){
            rq.enqueue(Integer.valueOf(i));
        }
        assert "0 1 2".equals(rq+"");
    }

    public void dequeueTest() throws Exception{
        getQueue().dequeue();
        assert getQueue().size()==2;
        getQueue().dequeue();
        assert getQueue().size()==1;
        getQueue().dequeue();
        assert getQueue().size()==0;
        assert getQueue().isEmpty();
    }

    public void iteratorTest(){
        Iterator<Integer> it=getQueue().iterator();
        while(it.hasNext()){
            System.out.println(it.next()+"");
        }
        /*for(int e:getQueue()){
            System.out.println(e);
        }
        */
    }

    public static void main(String[] args) throws Exception{
      RandomizedQueueTest rt=new RandomizedQueueTest();
      /*
      rt.sampleTest1(); 
      rt.getQueue().clear();
      rt.enqueueTest();
      rt.dequeueTest();
      rt.getQueue().clear();
      
      rt.enqueueTest();
      rt.dequeueTest();

      rt.getQueue().clear();
      rt.sampleTest2();

      rt.getQueue().clear();
      */
      rt.enqueueTest();
      assert rt.getQueue().size()==3;
      rt.iteratorTest();
    }

}
