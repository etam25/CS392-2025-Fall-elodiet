import Library.LnStrm.*;
import Library.FnTuple.*;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_03 {
    public static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T, T> cmp) {
        if (A == null || A.length <= 1) {
            return;
        }
        quickSortHelper(A, 0, A.length - 1, cmp);
    }

    private static <T> void quickSortHelper(T[] A, int lo, int hi, ToIntBiFunction<T, T> cmp) {
        if (lo >= hi) {
            return;
        }

        int[] bounds = partition3Way(A, lo, hi, cmp);
        int lt = bounds[0];
        int gt = bounds[1];

        quickSortHelper(A, lo, lt - 1, cmp);
        quickSortHelper(A, gt + 1, hi, cmp);
    }

    private static <T> int[] partition3Way(T[] A, int lo, int hi, ToIntBiFunction<T, T> cmp) {
        int mid = lo + (hi - lo) / 2;
        medianOfThree(A, lo, mid, hi, cmp);
        T pivot = A[lo];

        int lt = lo; 
        int i = lo + 1;
        int gt = hi;

        while (i <= gt) {
            int cmpResult = cmp.applyAsInt(A[i], pivot);

            if (cmpResult < 0) {
                swap(A, lt, i);
                lt++;
                i++;
            } else if (cmpResult > 0) {
                swap(A, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        return new int[]{lt, gt};
    }

    private static <T> void medianOfThree(T[] A, int lo, int mid, int hi, ToIntBiFunction<T, T> cmp) {
        if (cmp.applyAsInt(A[mid], A[lo]) < 0) {
            swap(A, lo, mid);
        }
        if (cmp.applyAsInt(A[hi], A[lo]) < 0) {
            swap(A, lo, hi);
        }
        if (cmp.applyAsInt(A[hi], A[mid]) < 0) {
            swap(A, mid, hi);
        }
        swap(A, lo, mid);
    }

    private static <T> void swap(T[] A, int i, int j) {
        T temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static <T> boolean isSorted(T[] A, ToIntBiFunction<T, T> cmp) {
        for (int i = 0; i < A.length - 1; i++) {
            if (cmp.applyAsInt(A[i], A[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ToIntBiFunction<Integer, Integer> intCmp = (a, b) -> Integer.compare(a, b);

        System.out.println("Test 1: Small array, mixed values");
        Integer[] test1 = {5, 2, 8, 9, 1, 5, 5, 3};
        System.out.print("Before: ");
        printArray(test1);
        arrayQuickSort(test1, intCmp);
        System.out.print("After: ");
        printArray(test1);
        System.out.println("Sorted: " + isSorted(test1, intCmp));
        System.out.println();

        System.out.println("Test 2: Array with 1M zeros");
        Integer[] test2 = new Integer[1_000_000];
        for (int i = 0; i < test2.length; i++) {
            test2[i] = 0;
        }

        long startTime = System.currentTimeMillis();
        arrayQuickSort(test2, intCmp);
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("Sorted: " + isSorted(test2, intCmp));
        System.out.println("All zeros: " + allEqual(test2, 0));
        System.out.println();

        System.out.println("Test 3: Already sorted");
        Integer[] test3 = new Integer[10000];
        for (int i = 0; i < test3.length; i++) {
            test3[i] = i;
        }

        startTime = System.currentTimeMillis();
        arrayQuickSort(test3, intCmp);
        endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("Sorted: " + isSorted(test3, intCmp));
        System.out.println();

        System.out.println("Test 4: Reverse sorted");
        Integer[] test4 = new Integer[10000];
        for (int i = 0; i < test4.length; i++) {
            test4[i] = 10000 - i;
        }

        startTime = System.currentTimeMillis();
        arrayQuickSort(test4, intCmp);
        endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("Sorted: " + isSorted(test4, intCmp));
        System.out.println();

        System.out.println("Test 5: Random array w/ duplicates");
        Integer[] test5 = new Integer[100000];
        Random rand = new Random(42);
        for (int i = 0; i < test5.length; i++) {
            test5[i] = rand.nextInt(100);
        }

        startTime = System.currentTimeMillis();
        arrayQuickSort(test5, intCmp);
        endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("Sorted: " + isSorted(test5, intCmp));
        System.out.println();

        System.out.println("Test 6: Array with all 5s and one exception");
        Integer[] test6 = new Integer[100000];
        for (int i = 0; i < test6.length; i++) {
            test6[i] = 5;
        }

        test6[50000] = 7;
        startTime = System.currentTimeMillis();
        arrayQuickSort(test6, intCmp);
        endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        System.out.println("Sorted: " + isSorted(test6, intCmp));
        System.out.println("Last element is 7: " + (test6[test6.length - 1] == 7));
        System.out.println();

        System.out.println("Test 7: Strings");
        String[] test7 = {"dog", "cat", "bird", "cat", "ant", "dog", "elephant"};
        ToIntBiFunction<String, String> strCmp = (a, b) -> a.compareTo(b);
        System.out.print("Before: ");
        printArray(test7);
        arrayQuickSort(test7, strCmp);
        System.out.print("After: ");
        printArray(test7);
        System.out.println("Sorted: " + isSorted(test7, strCmp));

        return;
    }

    private static <T> void printArray(T[] A) {
        if (A.length > 20) {
            System.out.print("[" + A.length + " elements]");
        } else {
            System.out.print("[");
            for (int i = 0; i < A.length; i++) {
                System.out.print(A[i]);
                if (i < A.length - 1) System.out.print(", ");
            }
            System.out.print("]");
        }
        System.out.println();
    }

    private static <T> boolean allEqual(T[] A, T val) {
        for (T elem : A) {
            if (!elem.equals(val)) {
                return false;
            }
        }
        return true;
    }

}