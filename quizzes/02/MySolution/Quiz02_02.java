public class Quiz02_02 {
    public static <T extends Comparable<? super T>>
    void sort1000WithNoRecursion(T[] A) {
        int n = A.length;
        if (n <= 1) return;

        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[n];

        // bottom-up mergesort: start with size 1, double each time
        for (int size = 1; size < n; size *= 2) {
            // merge adjacent runs of length 'size'
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(A, temp, left, mid, right);
            }
        }
    }

    // merge two sorted subarrays A[left...mid] and A[mid+1...right]
    private static <T extends Comparable<? super T>>
    void merge(T[] A, T[] temp, int left, int mid, int right) {
        // copy both halves to temp array
        for (int i = left; i <= right; i++) {
            temp[i] = A[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        // merge back to A[]
        while (i <= mid && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                A[k++] = temp[i++];
            } else {
                A[k++] = temp[j++];
            }
        }

        // copy remaining elements from left half (if any)
        while (i <= mid) {
            A[k++] = temp[i++];
        }

        // copy remaining elements from right half (if any)
        while (j <= right) {
            A[k++] = temp[j++];
        }
    }

    public static void main(String[] args) {
        // test 1 - small array
        Integer[] arr1 = {5, 2, 8, 1, 9, 3, 7, 4, 6, 0};
        System.out.print("Before: ");
        printArray(arr1);
        sort1000WithNoRecursion(arr1);
        System.out.print("After: ");
        printArray(arr1);
        System.out.println("Sorted: " + isSorted(arr1));
        System.out.println();

        // test 2 - array w/ 1000 elements
        Integer[] arr2 = new Integer[1000];
        java.util.Random rand = new java.util.Random(42);
        for (int i = 0; i < 1000; i++) {
            arr2[i] = rand.nextInt(1000);
        }
        System.out.print("Testing array of 1000 elements");
        sort1000WithNoRecursion(arr2);
        System.out.print("\nAfter: ");
        printArray(arr2); // FIX: print arr2, not arr1
        System.out.println("Sorted: " + isSorted(arr2));
        System.out.println("First 10: ");
        for (int i = 0; i < 10; i++) System.out.print(arr2[i] + " ");
        System.out.println();
        System.out.println("Last 10: ");
        for (int i = 990; i < 1000; i++) System.out.print(arr2[i] + " ");
        System.out.println();

        // test 3 - already sorted
        Integer[] arr3 = {1, 2, 3, 4, 5};
        sort1000WithNoRecursion(arr3);
        System.out.println("\nAlready sorted test: " + isSorted(arr3));

        // test 4 - reverse sorted
        Integer[] arr4 = {5, 4, 3, 2, 1};
        sort1000WithNoRecursion(arr4);
        System.out.println("Reverse sorted test: " + isSorted(arr4));

        // test 5 - empty and single element
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
