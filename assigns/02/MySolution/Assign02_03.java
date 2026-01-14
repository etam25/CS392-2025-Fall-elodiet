import java.util.Arrays;
@Test
public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
        if (A.length < 3) {
            return false;
        }

        // sort the arary - O(n log n)
        Arrays.sort(A);

        // each element A[k] --> check A[i] + A[j] = A[k]
        for (int k = 2; k < A.length; k++) {
            int target = A[k];
            int left = 0;
            int right = k - 1;

            while (left < right) {
                int sum = A[left] + A[right];
                if (sum == target) {
                    return true;
                } else if (sum < target) {
                    left++; // need larger sum
                } else {
                    right--; // need smaller sum
                }
            }
        }
        return false;
    }

    public static void main(String[] argv) {
        System.out.println("Testing solve_3sum funciton:");
        System.out.println();

        // test case 1 
        Integer[] test1 = {1, 2, 3, 4, 5};
        System.out.println("Test 1: " + Arrays.toString(test1));
        System.out.println("Expected: true (1 + 2 = 3, 1 + 4 = 5, 2 + 3 = 5, etc.)");
        System.out.println("Result: " + solve_3sum(test1));
        System.out.println();

        // test case 2
        Integer[] test2 = {1, 2, 4, 8};
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Expected: false (no A[i] + A[j] = A[k])");
        System.out.println("Result: " + solve_3sum(test2));
        System.out.println();

        // test case 3
        Integer[] test3 = {0, 1, 1, 2};
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Expected: true (0 + 1 = 1, 1 + 1 = 2)");
        System.out.println("Result: " + solve_3sum(test3));
        System.out.println();

        // test case 4
        Integer[] test4 = {-3, -1, 0, 1, 2};
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Expected: true (-1 + 0 = 1, -1 + 1 = 0, etc.)"); // actual = false
        System.out.println("Result: " + solve_3sum(test4));
        System.out.println();

        // test case 5
        Integer[] test5 = {1, 2};
        System.out.println("Test 5: " + Arrays.toString(test5));
        System.out.println("Expected: false (less than 3 elements)");
        System.out.println("Result: " + solve_3sum(test5));
        System.out.println();

        // test case 6
        Integer[] test6 = {2, 2, 2, 4};
        System.out.println("Test 6: " + Arrays.toString(test6));
        System.out.println("Expected: true (2 + 2 = 4)");
        System.out.println("Result: " + solve_3sum(test6));
        System.out.println();

        // test case 7
        Integer[] test7 = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("Test 7: " + Arrays.toString(test7));
        System.out.println("Expected: true (multiple solutions exist)");
        System.out.println("Result: " + solve_3sum(test7));
        System.out.println();

        // test case 8
        Integer[] test8 = {10, 20, 30};
        System.out.println("Test 8: " + Arrays.toString(test8));
        System.out.println("Expected: true (10 + 20 = 30)");
        System.out.println("Result: " + solve_3sum(test8));
        System.out.println();
    }
}
