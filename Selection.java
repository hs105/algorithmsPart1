public class Selection extends Sort {

    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < a.length; j++) {
                if (less(a[j], a[minIndex])) minIndex = j;
            }
            exch(a, minIndex, i);
        }
    }
}


