import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/*
 a class that reads strings and numbers
*/
public class Std {

    public static int[] readInts(String file) {
        String[] s = readStrings(file);
        int[] a = new int[s.length];
        for (int i = 0; i < s.length && s[i].trim().length()>0; i++) {
            a[i] = Integer.valueOf(s[i].trim());
        }
        return a;
    }

    public static String[] readStrings(String file){
        List<String> list  =  new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                list.add(reader.readLine());
            }
            reader.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return list.toArray(new String[0]);
    }
}
