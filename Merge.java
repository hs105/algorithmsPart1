public class Merge extends Sort { 
    public void sort(Comparable[] a) { 
        Comparable[] aux = new Comparable[a.length]; 
        mergesort(a, aux,  0, a.length); 
    }

    /*
        sort left and right half;  then merge
    */
    public void mergesort(Comparable[] a,  Comparable[] aux,  int low,  int high) { 
        if (high  <= low+1) return; 
        int mid = low+(high-low)/2; 
        mergesort(a, aux, low, mid); 
        mergesort(a, aux, mid, high); 
        merge(a,  aux,  low,  mid,  high); 
    }

    public void merge(Comparable[] a,  Comparable[] aux,  int low,  int mid,  int high) { 
        assert isSorted(a,  low,  mid); 
        assert isSorted(a,  mid,  high); 
        
        //copy the range to the auxiliary
        for (int i = low;  i < high;  i++) { 
            aux[i] = a[i]; 
        }
        
        /*
        copy from the auxiliary array using three indices: 
        one for  left half,  
        one for  right half
        one for  the auxliary array
        */
        int left = low; 
        int right = mid; 
        for (int i = low; i < high; i++) { 
            if (left >= mid) a[i] = aux[right++]; 
            else if (right >= high) a[i] = aux[left++]; 
            else if (less(aux[right], aux[left])) a[i] = aux[right++]; 
            else a[i] = aux[left++]; 
        }

        assert isSorted(a,  low,  high); 
    }
}
