import java.io.File;

public class InsertionTest {

    public static void main(String[] args) {
        try {
                File dir = new File(args[0]); 
                for (File file:dir.listFiles()) {
                        System.out.println(file.getName()); 
                        String fileName = dir.getName()+"/"+file.getName();
                        String[] a = Std.readStrings(fileName); 
                        for (int i = 0; i < a.length; i++) {
                            System.out.println(a[i]); 
                        }
                        Insertion insertion = new Insertion(); 
                        insertion.sort(a); 
                        for (int i = 0; i < a.length; i++) {
                            System.out.println(a[i]); 
                        }
                        assert insertion.isSorted(a); 
                }
        }
        catch (IOException e) {
            System.err.println(e.getMessage()); 
        }
    }
    

}
