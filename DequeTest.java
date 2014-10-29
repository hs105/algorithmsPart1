import java.util.Iterator;

/*
    future work: 
    Tests  make random calls to addFirst(), addLast(), removeFirst(), and
    removeLast(). The probabilities of each operation are p1, p2, p3, and p4,
    respectively. check size()
    Test 1: Calls to addFirst() and addLast() only
    Test 2: Calls to addFirst() and removeFirst()
    Test 3: Calls to addFirst() and removeLast()
    Test 4: Calls to addLast() and removeLast()
    Test 5: Calls to addLast() and removeFirst()
    Test 6: Calls to addFirst(), addLast(), removeFirst(), and removeLast()
    Test 9: Check iterator() after calls only to addFirst()
    Test 10: Check iterator() after intermixed calls to addFirst(), addLast(),
             removeFirst(), and removeLast()
    Test 11: Create two independent iterators to same deque
    Test 12: Create Deque objects of different parameterized types
    Test 13: Check that addFirst() and addLast() each throw a NullPointerException
             when inserting null items

    Test 15: Check iterator() when Deque is empty
    passed all these tests for coursera. should write these tests yourself here.
*/

public class DequeTest {
    private Deque deque = new Deque<Integer>();
    

    public void testExceptions() throws Exception {
        deque.clear();
        //deque.removeFirst();
        //deque.removeLast();
        Iterator<Integer> it = deque.iterator();
        //it.remove();
        it.next();
    }

    public void test1() throws Exception {
        deque.clear();
        deque.addFirst(1);
        assert "1".equals(deque+"");
        deque.addFirst(2);
        assert "2 1".equals(deque+"");
        deque.addFirst(3);
        assert "3 2 1".equals(deque+"");
//        for(Integer element:deque)
//            System.out.println(element);
    }

    public void test2() throws Exception {
        deque.clear();
        deque.addLast(1);
        assert "1".equals(deque+"");
        deque.addLast(2);
        assert "1 2".equals(deque.toString());
        deque.addLast(3);
        assert "1 2 3".equals(deque+"");
    }

    public void test3() throws Exception {
        deque.clear();
        deque.addFirst(1);
        deque.addLast(2);
        assert "1 2".equals(deque.toString());
        deque.addFirst(3);
        assert "3 1 2".equals(deque.toString());
        deque.addLast(4);
        assert "3 1 2 4".equals(deque.toString());
    
    }

    public void test4() throws Exception {
        deque.clear();
        test3();
        deque.removeLast();
        assert "3 1 2".equals(deque.toString());
        deque.removeFirst();
        assert "1 2".equals(deque.toString());
        deque.removeLast();
        assert "1".equals(deque.toString());
        deque.removeFirst();
        assert "".equals(deque.toString());
    }

    public static void main(String[] args) throws Exception {
        DequeTest test = new DequeTest();
//        test.testExceptions();
        test.test1();
        test.test2();
        test.test3();
        test.test4();
    }

}
