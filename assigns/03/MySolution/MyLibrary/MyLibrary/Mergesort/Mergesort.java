package MyLibrary.MyLibrary.Mergesort;
import java.util.Comparator;

public final class Mergesort {
    //utility class: no instances
    private Mergesort() { }

    // natural-order comparator helper 
    private static <T extends Comparable<? super T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    /** 
     * public API: sorts array in-place using mergesort.
     * allocate 1 auxillary buffer once and alternate roles (A<->B) while recursing.
     */
    public static <T extends Comparable<? super T>> void sort(T[] A) {
        final int n = A.length;
        @SuppressWarnings("unchecked")
        T[] B = (T[]) new Comparable[n]; // auxillary buffer
        sortRec1(A, 0, n, B); // start with A as source, A as final target
    }
    /* 
     * sortRec1: source = A, final target = A (writes into A by merging from B)
     * sortRec2: source = A, final target = B (writes into B by merging frmo A)
     * By alternating, we avoid copying whole subarrays each level. 
     */

     /** 
      * Sorts A[l:r) and leaves the result in A[l:r)
      internally: sorts halves into B, merges from B back into A
      */
      private static <T extends Comparable<? super T>> void sortRec1(T[] A, int l, int r, T[] B) {
        if (r - l <= 1) return; // 0 or 1 element: already sorted
        int m = l + (r - l)/2;
        sortRec2(A, l, m, B); // left half result ends in B
        sortRec2(A, m, r, B); // right half result ends in B
        merge(B, l, m, r, A); // merge from B -> A
      }
      /** 
       * sorts A[l:r) and leaves the result in B[l:r).
       * internally: sorts halves into A, merges from A into B
       */
      private static <T extends Comparable<? super T>> void sortRec2(T[] A, int l, int r, T[] B) {
        int len = r - l;
        if (len <= 1) {
        if (len == 1) B[l] = A[l]; 
        return;
        }
        int m = l + len/2;
        sortRec1(A, l, m, B); // left half result ends in A
        sortRec1(A, m, r, B); // right half result ends in A
        merge(A, l, m, r, B); // merge from A -> B
      }

      /* 
       * Merge: merges two sorted runs 
       * Source array S: [l:m) and [m:r) are sorted;
       * write merged result into T[l:r).
       */
      private static <T extends Comparable<? super T>> void merge(T[] S, int l, int m, int r, T[] Tgt) {
        int i = l; // pointer into left run 
        int j = m; // pointer into right run 
        int k = l; // write cursor in target

        // while both runs have elements, take the smaller
        while (i < m && j < r) {
            if (less(S[j], S[i])) {
                Tgt[k++] = S[j++];
            } else {
                Tgt[k++] = S[i++];
            }
        }
        // copy any remainder from left run 
        while (i < m) {
            Tgt[k++] = S[i++];
        }
        // copy any remainder from right run 
        while (j < r) {
            Tgt[k++] = S[j++];
        }
      }

      public static void main(String[] args) {
        Integer[] A = {9, 8, 7, 6, 5,4 , 3, 2, 1, 0};
        System.out.println("before:");
        for (int i = 0; i < A.length; i++) System.out.println("A[" + i + "] = " + A[i]);
        sort(A); 

        System.out.println("\nAfter:");
        for (int i = 0; i < A.length; i++) System.out.println("A[" + i + "] = " + A[i]);
      }
}
