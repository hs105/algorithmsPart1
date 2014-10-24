import java.util.NoSuchElementException;
import java.util.Iterator;

/*
    use linked list to
    support each deque operation in constant worst-case time

    requirement: http://coursera.cs.princeton.edu/algs4/assignments/queues.html
*/
public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Node next;
        private Node previous;
        private Item item;
    }
    private Node first, last;
    private int N = 0;

    public Deque() {
        
    }

    public void clear() {
        first = null;
        last = null;
        N = 0;
    }

    /* is the deque empty? */
    public boolean isEmpty() {
        if (N ==  0) {
                assert first ==  last;
                assert first ==  null;
        }
        return N ==  0;
    }
    
    /*number of items in the deque */
    public int size() {
        return N;
    }

    /* add the item at the front */
    public void addFirst(Item item) throws Exception {
        if (item ==  null) 
            throw new NullPointerException("null pointer not allowed ");
        if (isEmpty()) {
                first = new Node();
                first.item = item;
                last = first;
        } else {
                Node node = new Node();
                node.item = item;
                first.previous = node;
                node.next = first;
                first = node;
        }
        N++;
    }

    /* remove the item at the front */
    public void removeFirst() throws Exception {
        if (size() ==  0)
            throw new NoSuchElementException("no removing for empty deque");
        if (size() == 1) {
            clear();
        } else {
            Node second = first.next;
            assert second != null;
            second.previous = null;
            first = second;
        }
        N--;
    }

    /* add item at the last */
    public void addLast(Item item) throws Exception {
        if (item ==  null)
            throw new NullPointerException("no null allowed to be added");
        if (isEmpty()) {
            addFirst(item);
        } else {
                Node node = new Node();
                node.item = item;
                last.next = node;
                node.previous = last;
                last = node;
        }
        N++;
    }

    /* remove item at the end */
    public void removeLast() throws Exception {
        if (size() ==  0)
            throw new NoSuchElementException("no removing for empty deque");
        if (size() == 1) {
            removeFirst();
        } else {
            Node secondLast = last.previous;
            assert secondLast != null;
            secondLast.next =  null;
            last = secondLast;
        }
        N--;
    }

    /* return an iterator in the order from front to end */
    public Iterator<Item> iterator() {
          return new FrontToEndIterator();     
    }

    private class FrontToEndIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Item next()  {
            if (!hasNext())
                throw new NoSuchElementException("No such element.");
            Item result = current.item;
            current = current.next;
            return result;
        }
        public void remove()  {
            throw new UnsupportedOperationException("Unsupported Operation.");
        }
    }

    @Override
    public String toString() {
                Iterator<Item> it = iterator();
                String result = "";
                while (it.hasNext()) {
                    result += it.next()+" ";
                }
                return result.trim();
        }


    //unit testing
    public static void main(String[] args) {
    
    }
}
