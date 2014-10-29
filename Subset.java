import java.util.Iterator; 
/*
 given a commanline input integer k; 
 and std input sequnce of strings (separated by
 white space)
 output k random, non-repeating words 

execute: echo A B C D E F G H I | java Subset 3 
*/

public class Subset {
    public static void main(String[] args) throws Exception {
        int k = Integer.valueOf(args[0]); 
        String[] strings = StdIn.readAllStrings(); 
        assert k >= 0 && k <= strings.length; 
        
        RandomizedQueue<String> q = new RandomizedQueue<>(); 
        for (int i = 0; i < strings.length; i++) {
            q.enqueue(strings[i]); 
        }
        int count = 0; 
        Iterator<String> it = q.iterator(); 
        assert k <= q.size(); 
        while (++count <= k && it.hasNext()) {
            System.out.println(it.next()+""); 
        }
    }
}
