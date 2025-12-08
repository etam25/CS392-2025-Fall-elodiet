import Library.FnA1sz.*;

public class Quiz01_01 {
    public static <T extends Comparable<T>> int BinarySearch(FnA1sz<T> A, T key) {
        int n = A.length();

        if (n == 0) {
            return -1;
        }
        if (key.compareTo(A.getAt(0)) < 0) {
            return -1;
        }

        int left = 0;
        int right = n - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midVal = A.getAt(mid);

            int cmp = key.compareTo(midVal);

            if (cmp >= 0) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        // Test 1
        Integer[] arr1 = {1, 3, 5, 7, 9};
        FnA1sz<Integer> A1 = new FnA1sz<>(arr1);
        System.out.println("Test 1: Array [1, 3, 5, 7, 9]");
        System.out.println(" Search for 5: " + BinarySearch(A1, 5) + " (expected: 2)");
        System.out.println("  Search for 6: " + BinarySearch(A1, 6) + " (expected: 2)");
        System.out.println("  Search for 0: " + BinarySearch(A1, 0) + " (expected: -1)");
        System.out.println("  Search for 10: " + BinarySearch(A1, 10) + " (expected: 4)");
        // Test 2
        Integer[] arr2 = {1, 3, 3, 3, 5, 7};
        FnA1sz<Integer> A2 = new FnA1sz<>(arr2);
        System.out.println("\nTest 2: Array [1, 3, 3, 3, 5, 7]");
        System.out.println(" Search for 3: " + BinarySearch(A2, 3) + " (expected: 3)");
        System.out.println(" Search for 4: " + BinarySearch(A2, 4) + " (expected: 3)");
        // Test 3
        Integer[] arr3 = {5};
        FnA1sz<Integer> A3 = new FnA1sz<>(arr3);
        System.out.println("\nTest 3: Array [5]");
        System.out.println(" Search for 5: " + BinarySearch(A3, 5) + " (expected: 0)");
        System.out.println(" Search for 3: " + BinarySearch(A3, 3) + " (expected: -1)");
        System.out.println(" Search for 7: " + BinarySearch(A3, 7) + " (expected: 0)");
        // Test 4
        String[] arr4 = {"apple", "banana", "cherry", "date"};
        FnA1sz<String> A4 = new FnA1sz<>(arr4);
        System.out.println("\nTest4: Array [apple, banana, cherry, date]");
        System.out.println(" Search for 'cherry': " + BinarySearch(A4, "cherry") + " (expected: 2)");
        System.out.println("  Search for 'coconut': " + BinarySearch(A4, "coconut") + " (expected: 2)");
        System.out.println("  Search for 'apple': " + BinarySearch(A4, "apple") + " (expected: 0)");
    
        return;
    }
}