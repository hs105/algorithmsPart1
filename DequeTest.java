public class DequeTest {
    private Deque deque = new Deque<Integer>();
    
    public void test1() throws Exception {
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
        deque.addLast(1);
        assert "1".equals(deque+"");
        deque.addLast(2);
        assert "1 2".equals(deque.toString());
        deque.addLast(3);
        assert "1 2 3".equals(deque+"");
    }

    public static void main(String[] args) throws Exception {
        DequeTest test = new DequeTest();
        //test.test1();
        test.test2();
    }

}
