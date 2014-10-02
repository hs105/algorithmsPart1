public class Insertion extends Sort { 
    
    public void sort(Comparable[] a) { 
        for (int i = 1; i < a.length; i++) { 
            for (int j = i; j >= 1; j--) { 
                if (less(a[j], a[j-1])) exch(a, j, j-1); 
                else continue; //Insertion sort is non-stop. 
             } 
         } 
     } 

 } 
