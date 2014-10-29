import java.util.NoSuchElementException; 
import java.util.Iterator; 

/*
    A randomized queue implemented with resizing array
*/
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N = 0;  //number of queue elements
    private Item[] a; //excessive storage array

    /* construct an empty queue */
    public RandomizedQueue() {
    
    }

    public boolean isEmpty()  {
        return size()  ==  0; 
    }

    public int size() {
        return N; 
    }

    private void resize(int sizeNew) {
        Item[] b = (Item[]) new Object[sizeNew]; 
        if (a != null) {
                assert N <=  Math.min(a.length, b.length); 
                for (int i = 0; i < N; i++) {
                    b[i] = a[i]; 
                }
        }
        a = b; 
    }

    public void enqueue(Item item) throws Exception {
        if (item  ==  null)
            throw new NullPointerException("not allowing null to be added"); 
        N++; 
        if (a  ==  null || N >=  1/2*a.length)
            resize(2*N); 
        a[N-1] = item; 
    }

    /* delete and return a random item */
    public Item dequeue() throws Exception {
        if (size()  ==  0)
            throw new NoSuchElementException("No element left any more"); 
        Item s = a[N-1]; 
        a[N-1] = null;  //loitering
        N--; 
        if (N <=  1/4*a.length) {
            resize(1/2*a.length); 
        }
        return s; 
    }

    /* return but do not delete a random item */
    public Item sample() throws Exception {
        if (size()  ==  0)
            throw new NoSuchElementException("No element left any more"); 
        return a[StdRandom.uniform(N)]; 
    }

    /* return an independent iterator over items in random order */
    public Iterator<Item> iterator() {
        return new RandomIterator(); 
    }

    private void shuffle() {
        for (int i = 0; i < N; i++) {
            Item tmp = a[i]; 
            int r = StdRandom.uniform(N); 
            a[i] = a[r]; 
            a[r] = tmp; 
        }
    }

    private class RandomIterator implements Iterator<Item> {
         {
            int lengthOld = a.length; 
            shuffle(); 
            assert a.length  ==  lengthOld; 
        }

        private int current = 0; 

        public boolean hasNext() {
            return current <=  N-1; 
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no more to remove()"); 
            }
            current++; 
            return a[current-1]; 
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported for remove()");  
        }
    }

   public void clear() {
   //private void clear() {
        a = null; 
        N = 0; 
   }

/*
   @Override
   public String toString() {
        String s = ""; 
        for (int i = 0; i<N; i++) {
            s+ = a[i]+" "; 
        }
        return s.trim(); 
   }
*/

    /* testing unit */
    public static void main(String[] args) {
    
    }
}
