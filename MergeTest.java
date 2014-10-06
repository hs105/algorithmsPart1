import java.io.File;

public class MergeTest {

    public static void main(String[] args) {
        File dir = new File(args[0]); 
        for (File file:dir.listFiles()) {
                System.out.println(file.getName()); 
                String fileName = dir.getName()+"/"+file.getName();
                int[] atmp = Std.readInts(fileName); 
                Integer[] a = new Integer[atmp.length];
                for (int i = 0; i < a.length; i++)
                    a[i] = atmp[i];
                System.out.println("original array");
                Merge sort = new Merge(); 
                sort.show(a);
                sort.sort(a); 
                System.out.println("sorted array");
                sort.show(a);
                assert sort.isSorted(a); 
        }
    }
    

}
