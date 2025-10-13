package MyLibrary;

import java.util.Comparator;

public class Quicksort {
    // private constructor prevents instantiation of this utility class
    private Quicksort() { }

    // swap two elements in the array
    private static <T> void swap(T[] A, int i, int j) {
        T temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    // compare two elements using their natural ordering 
    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }
    // public entry point for quicksort 
    public static <T extends Comparable<T>> void sort(T[] A) {
        sortRec(A, 0, A.length); // sort entire array
    }
    // choose pivot index (currently just leftmost element)
    private static int choosePivot(int left, int right) {
        return left; 
    }
    // partition array around pivot value and return pivots final position
    private static <T extends Comparable<T>> int partition(T[] A, int left, int right) {
        // right is exclusive, so pivot is at right - 1
        return partitionRec(A, left, right - 2, A[right - 1]);
    }
    // recursive partition helper 
    private static <T extends Comparable<T>> int partitionRec(T[] A, int leftPtr, int rightPtr, T pivot) {
        // move pointers inward until they cross
        if (leftPtr < rightPtr) {
            if (less(A[leftPtr], pivot)) {
                // left value is smallrt than pivot --> keep it, move right 
                return partitionRec(A, leftPtr + 1, rightPtr, pivot);
            } else if (!less(A[rightPtr], pivot)) {
                // right value is bigger than pivot --> move left pointer inward
                return partitionRec(A, leftPtr, rightPtr - 1, pivot);
            } else {
                // swap values that are on the wrong side 
                swap(A, rightPtr, rightPtr);
                return partitionRec(A, leftPtr + 1, rightPtr - 1, pivot);
            }
        } else {
            // when pointers cross, return the final split index
            return (!less(A[leftPtr], pivot)) ? leftPtr : leftPtr + 1;
        }
    }
    // recursive quicksort function
    private static <T extends Comparable<T>> void sortRec(T[] A, int left, int right) {
        // base case: 0 or 1 element --> already sorted
        if (right <= left + 1) return;
        
        // pick pivot and move it to end
        int pivotIndex = choosePivot(left, right);
        swap(A, pivotIndex, right - 1);

        // partition array around pivot
        int mid = partition(A, left, right);

        // place pivot in its final position
        swap(A, mid, right - 1);

        // recurse on left and right halves
        sortRec(A, left, mid);
        sortRec(A, mid + 1, right);
    }

    // simple test in main()
    public static void main(String[] args) {
        Integer[] A = {9, 8, 7, 6, 5, 4, 4, 3, 2, 1, 0};

        System.out.println("before sorting:");
        for (int i = 0; i < A.length; i++) {
            System.out.println("A[" + i + "] = " + A[i]);
        }
        
        sort(A);
        System.out.println("\nAfter sorting:");
        for (int i = 0; i < A.length; i++) {
            System.out.println("A[" + i + "] = " + A[i]);
        }
    }


}