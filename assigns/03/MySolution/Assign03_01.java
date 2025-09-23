public class Assign03_01 {
    static int f91(int n) {
        return f91_helper(n, 1);
    }

    private static int f91_helper(int n, int count) {
        if (count == 0) {
            return n;
        }

        if (n > 100) {
            return f91_helper(n - 10, count - 1);
        } else {
            return f91_helper(n + 11, count + 1);
        }
        }
        public void main (String[] argv) {
            // testing code
            System.out.println("Testing McCarthy's 91 function (tail-recursion version):");
            System.out.println();

            // test cases for n <= 100 (should all return 91)
            int[] testCases1 = {1, 5, 10, 25, 50, 75, 90, 95, 99, 100};
            System.out.println("Test cases for n <= 100 (should all return 91):");
            for (int n : testCases1) {
                int result = f91(n);
                System.out.printf("f91(%d) = %d%n", n, result);
            }
            System.out.println();

            // test cases for n > 100 (should return n - 10)
            int[] testCases2 = {101, 102, 105, 110, 120, 150, 200};
            System.out.println("Test cases for n > 100 (should return n - 10):");
            for (int n : testCases2) {
                int result = f91(n);
                System.out.printf("f91(%d) = %d (expected: %d)%n", n, result, n-10);
            }
            System.out.println();

            System.out.println("Edge cases:");
            System.out.printf("f91(100) = %d%n", f91(100));
            System.out.printf("f91(101) = %d%n", f91(101));

            // verify the propertuy of McCarthy's 91 function
            System.out.println();
            System.out.println("Verification: all values <= 100 should return 91");
            boolean allCorrect = true;
            for (int i = 50; i <= 100; i++) {
                if (f91(i) != 91) {
                    System.out.printf("ERROR: f91(%d) (expected 91)%n", i, f91(i));
                    allCorrect = false;
                }
            }
            if (allCorrect) {
                System.out.println("All test cases for n <= 100 passed");
            }
        }
}