public class Quiz02_02 {
    
    public static <T extends Comparable<? super T>>
    void sort1000WithNoRecursion(T[] A) {
        int n = A.length;
        if (n <= 1) return;
        
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[n];
        
        // Start bottom-up merge sort recursively
        bottomUpMerge(A, temp, n, 1);
    }
    
    // Recursive helper for bottom-up merge sort
    // size: current size of subarrays to merge
    private static <T extends Comparable<? super T>>
    void bottomUpMerge(T[] A, T[] temp, int n, int size) {
        if (size >= n) return; // Base case: done when size covers entire array
        
        // Merge all adjacent pairs of subarrays of current size
        mergeAllPairs(A, temp, n, size, 0);
        
        // Recursively process next size level (double the size)
        bottomUpMerge(A, temp, n, size * 2);
    }
    
    // Recursively merge all pairs at current size level
    private static <T extends Comparable<? super T>>
    void mergeAllPairs(T[] A, T[] temp, int n, int size, int left) {
        if (left >= n - size) return; // Base case: no more pairs to merge
        
        int mid = left + size - 1;
        int right = Math.min(left + 2 * size - 1, n - 1);
        merge(A, temp, left, mid, right);
        
        // Recursively merge next pair
        mergeAllPairs(A, temp, n, size, left + 2 * size);
    }
    
    // Merge two sorted subarrays A[left...mid] and A[mid+1...right]
    private static <T extends Comparable<? super T>>
    void merge(T[] A, T[] temp, int left, int mid, int right) {
        // Copy both halves to temp array
        copyToTemp(A, temp, left, right, left);
        
        // Merge back to A[]
        mergeBack(A, temp, left, mid, right, left, mid + 1, left);
    }
    
    // Recursively copy elements to temp array
    private static <T> void copyToTemp(T[] A, T[] temp, int i, int right, int current) {
        if (current > right) return; // Base case
        temp[current] = A[current];
        copyToTemp(A, temp, i, right, current + 1);
    }
    
    // Recursively merge back to A[]
    private static <T extends Comparable<? super T>>
    void mergeBack(T[] A, T[] temp, int left, int mid, int right, int i, int j, int k) {
        if (i <= mid && j <= right) {
            // Both halves have elements
            if (temp[i].compareTo(temp[j]) <= 0) {
                A[k] = temp[i];
                mergeBack(A, temp, left, mid, right, i + 1, j, k + 1);
            } else {
                A[k] = temp[j];
                mergeBack(A, temp, left, mid, right, i, j + 1, k + 1);
            }
        } else if (i <= mid) {
            // Only left half has elements
            A[k] = temp[i];
            mergeBack(A, temp, left, mid, right, i + 1, j, k + 1);
        } else if (j <= right) {
            // Only right half has elements
            A[k] = temp[j];
            mergeBack(A, temp, left, mid, right, i, j + 1, k + 1);
        }
        // Both exhausted: base case (do nothing)
    }
    
    public static void main(String[] args) {
        // Test 1 - small array
        Integer[] arr1 = {5, 2, 8, 1, 9, 3, 7, 4, 6, 0};
        System.out.print("Before: ");
        printArray(arr1);
        sort1000WithNoRecursion(arr1);
        System.out.print("After: ");
        printArray(arr1);
        System.out.println("Sorted: " + isSorted(arr1));
        System.out.println();
        
        // Test 2 - array with 1000 elements
        Integer[] arr2 = new Integer[1000];
        java.util.Random rand = new java.util.Random(42);
        for (int i = 0; i < 1000; i++) {
            arr2[i] = rand.nextInt(1000);
        }
        
        System.out.println("Testing array of 1000 elements");
        sort1000WithNoRecursion(arr2);
        System.out.println("Sorted: " + isSorted(arr2));
        System.out.print("First 10: ");
        for (int i = 0; i < 10; i++) System.out.print(arr2[i] + " ");
        System.out.println();
        System.out.print("Last 10: ");
        for (int i = 990; i < 1000; i++) System.out.print(arr2[i] + " ");
        System.out.println("\n");
        
        // Test 3 - already sorted
        Integer[] arr3 = {1, 2, 3, 4, 5};
        sort1000WithNoRecursion(arr3);
        System.out.println("Already sorted test: " + isSorted(arr3));
        
        // Test 4 - reverse sorted
        Integer[] arr4 = {5, 4, 3, 2, 1};
        sort1000WithNoRecursion(arr4);
        System.out.println("Reverse sorted test: " + isSorted(arr4));
        
        // Test 5 - empty and single element
        Integer[] arr5 = {};
        Integer[] arr6 = {1};
        sort1000WithNoRecursion(arr5);
        sort1000WithNoRecursion(arr6);
        System.out.println("Empty array test: " + isSorted(arr5));
        System.out.println("Single element array test: " + isSorted(arr6));
    }
    
    private static <T> void printArray(T[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    private static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) return false;
        }
        return true;
    }
}