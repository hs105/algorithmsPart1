import java.util.Iterator;
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
