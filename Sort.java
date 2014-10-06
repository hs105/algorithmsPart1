public abstract class Sort {
    
    public abstract void sort(Comparable[] a); 

    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i]; 
        a[i] = a[j]; 
        a[j] = swap; 
    }

    public static boolean less(Comparable x, Comparable y) {
        return x.compareTo(y) < 0; 
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length);
    }

    public static boolean isSorted(Comparable[] a, int low, int high){
        for(int i = low; i < high-1; i++){
            if(less(a[i+1], a[i])) return false;
        }
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]+" "); 
        }
        System.out.println(); 
    }
}
