import java.util.Arrays;

public class Assign02_02 {
    public static <T extends Comparable<T>> int indexOf(T[] a, T key) {
        return indexOf(A, key, 0, a.length - 1);
    }    
    private static <T extends Comparable<T>> int indexOf(T[] a, T key, int lo, int hi) {
        if (lo > hi) {
            return - 1;
        }
        final int mid = lo + (hi - lo)/2;
        final int sign = key.compareTo(a[mid]);

        if (sign < 0) {
            // key is smaller, search left half
            return indexOf(a, key, lo, mid - 1);
        } else if (sign > 0) {
            // key is larger, search right half
            return indexOf(a, key, mid + 1, hi);
        } else {
            // found the key
            return mid;
        }

    }

    public static void main(String[] argv) {
        // test with integer arrays
        Integer[] intArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("Testing with integer array: " + Arrays.toString(intArray));

        // test cases for integers
        System.out.println("indexOf(intArray, 7): " + indexOf(intArray, 7)); // should be 3
        System.out.println("indexOf(intArray, 1): " + indexOf(intArray, 1)); // should be 0
        System.out.println("indexOf(intArray, 19): " + indexOf(intArray, 19)); // should be 9
        System.out.println("indexOf(intArray, 6): " + indexOf(intArray, 6)); // should be -1
        System.out.println("indexOf(intArray, 20): " + indexOf(intArray, 20)); // should be -1
        System.out.println("indexOf(intArray, 0): " + indexOf(intArray, 0)); // should be -1

        System.out.println();

        String[] stringArray = {"apple", "banana", "cherry", "melon", "grapes"};
        System.out.println("Testing with string array: " + Arrays.toString(stringArray));

        // test cases for strings
        System.out.println("indexOf(StringArray, \"apple\"): " + indexOf(stringArray, "apple")); 
        System.out.println("indexOf(StringArray, \"banana\"): " + indexOf(stringArray, "banana")); 
        System.out.println("indexOf(StringArray, \"cherry\"): " + indexOf(stringArray, "cherry")); 
        System.out.println("indexOf(StringArray, \"melon\"): " + indexOf(stringArray, "melon")); 
        System.out.println("indexOf(StringArray, \"grapes\"): " + indexOf(stringArray, "grapes")); 

        System.out.println();

        // test with empty array
        Integerp[] emptyArray = {};
        System.out.println("Testing with empty array:");
        System.out.println("indexOf(emptyarray, 5): " + indexOf(emptyArray, 5)); // should be -1

        // test with single element array
        Integer[] singleArray = {42};
        System.out.println("Testing with single element array: " + Arrays.toString(singleArray));
        System.out.println("indexOf(singleArray, 42): " + indexOf(singleArray, 42));
        System.out.println("indexOf(singleArray, 10): " + indexOf(singleArray, 10)); // should be -1 
    }

}
