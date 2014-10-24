import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item>{

    public RandomizedQueue()

    public boolean isEmpty() {
        return size()==0;
    }

    public int size()

    public void enqueue(Item item) throws Exception{
        if(item==null)
            throw new NullPointerException("not allowing null to be added")
    }

    /* delete and return a random item */
    public Item dequeue() throws Exception{
        if(size()==0)
            throw new NoSuchElementException("No element left any more");
    }

    /* return but do not delete a random item */
    public Item sample() throws Exception{
        if(size()==0)
            throw new NoSuchElementException("No element left any more");
    }

    /* return an independent iterator over items in random order */
    public Iterator<Item> iterator()

    /* testing unit */
    public static void main(String[] args)
}
