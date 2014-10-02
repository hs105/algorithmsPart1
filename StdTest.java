public class StdTest{
    public static void main(String[] args){
        String[] s=Std.readStrings(args[0]);
        for(int i=0; i<s.length; i++){
            System.out.println(s[i]);
        }

        int[] a=Std.readInts(args[0]);
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }
}
