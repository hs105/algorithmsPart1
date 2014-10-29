import java.util.Iterator; 

/*
Tests 1-4 make random calls to enqueue(), dequeue(), and sample().
The probabilities of each operation are p1, p2, and p3, respectively.

Test 1: Calls to enqueue() (and dequeue() just to verify)
  *     5 random calls (p1 = 1.0, p2 = 0.0, p3 = 0.0)
    *    50 random calls (p1 = 1.0, p2 = 0.0, p3 = 0.0)
      *   500 random calls (p1 = 1.0, p2 = 0.0, p3 = 0.0)
        *  1000 random calls (p1 = 1.0, p2 = 0.0, p3 = 0.0)
Test 1: Calls to enqueue() (and dequeue() just to verify)
Test 2: Calls to enqueue() and dequeue()
Test 9: Create two independent iterators to same randomized queue

*/

public class RandomizedQueueTest {
    
    private RandomizedQueue<Integer> rq = new RandomizedQueue<>(); 
    private int size = 0; 
    
    public RandomizedQueue<Integer> getQueue() {
        return rq; 
    }
    

    public void sampleTest1() throws Exception {
        rq.enqueue(1); 
        assert rq.sample() == 1; 
    }

    public void sampleTest2() throws Exception {
        enqueueTest(); 
        getQueue().sample(); 
        assert getQueue().size() == 3; 
    }

    public void enqueueTest() throws Exception {
        for (int i = 0; i < 3; i++) {
            rq.enqueue(Integer.valueOf(i)); 
        }
        assert "0 1 2".equals(rq+""); 
    }

    public void dequeueTest() throws Exception {
        getQueue().dequeue(); 
        assert getQueue().size() == 2; 
        getQueue().dequeue(); 
        assert getQueue().size() == 1; 
        getQueue().dequeue(); 
        assert getQueue().size() == 0; 
        assert getQueue().isEmpty(); 
    }

    public void iteratorTest() {
        Iterator<Integer> it = getQueue().iterator(); 
        while (it.hasNext()) {
            System.out.println(it.next()+""); 
        }
        /*for (int e:getQueue()) {
            System.out.println(e); 
        }
        */
    }

    public void testException1() throws Exception {
        getQueue().clear(); 
        getQueue().dequeue(); 
    }

    public void testException2() throws Exception {
        getQueue().clear(); 
        getQueue().sample(); 
    }
    
    /*
        Calls to enqueue() 
        with probablity p1 
        and dequeue()
        wiht probability p2
        sample()
        with probability p3
    */
    public void testEDS(double p1, double p2, double p3) throws Exception {
       int index  =  StdRandom.discrete(new double[] {p1, p2, p3}); 
       if (index == 0) {
            getQueue().enqueue(Integer.valueOf(1)); 
            size++; 
       } else if (index == 1) {
            getQueue().dequeue(); 
            size--; 
       } else  {
            getQueue().sample(); 
       }
       assert size  ==  getQueue().size(); 

    }

    public void test2() throws Exception {
        for (int i = 0; i < 5;  i++) {
            testEDS(0.9, 0.1, 0.0); 
        }
            
        for (int i = 0; i < 50;  i++) {
            testEDS(0.9, 0.1, 0.0); 
        }

        for (int i = 0; i < 500;  i++) {
            testEDS(0.9, 0.1, 0.0); 
        }
    }


    public void test4() throws Exception  {
        for (int i = 0; i < 50;  i++) {
            testEDS(0.1, 0.1, 0.8); 
        }
        for (int i = 0; i < 500;  i++) {
            testEDS(0.1, 0.1, 0.8); 
        }
    }


    public static void main(String[] args) throws Exception {
      RandomizedQueueTest rt = new RandomizedQueueTest(); 
      
      /*rt.sampleTest1();  
      rt.getQueue().clear(); 
      rt.enqueueTest(); 
      rt.dequeueTest(); 
      rt.getQueue().clear(); 
      
      rt.enqueueTest(); 
      rt.dequeueTest(); 

      rt.getQueue().clear(); 
      rt.sampleTest2(); 

      rt.getQueue().clear(); 
      rt.enqueueTest(); 
      assert rt.getQueue().size() == 3; 
      rt.iteratorTest(); 

//      rt.testException1(); 
      rt.testException2(); 
      */

      rt.test2();
      rt.test4();
    }

}
