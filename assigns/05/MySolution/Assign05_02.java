import MyLibrary.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_02 {
    
    public static <T extends Comparable<T>> FnList<T> insertSort(FnList<T> xs) {
        return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    
    public static<T> FnList<T> insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
        if (xs == null || xs.nilq() || xs.tl().nilq()) {
            return xs;
        }
        
        // Start with first element as sorted
        FnList<T> sorted = new FnList<>(xs.hd(), new FnList<>());
        FnList<T> cur = xs.tl();
        
        while (cur.consq()) {
            sorted = insertOptimized(cur.hd(), sorted, cmp);
            cur = cur.tl();
        }
        
        return sorted;
    }
    
    // Optimized insert that avoids rebuilding prefix when element goes at the end
    private static <T> FnList<T> insertOptimized(T x, FnList<T> sorted, ToIntBiFunction<T, T> cmp) {
        // Check if x should be first
        if (sorted.nilq() || cmp.applyAsInt(x, sorted.hd()) <= 0) {
            return new FnList<>(x, sorted);
        }
        
        // Fast path: check if x goes at the very end (common for nearly sorted lists)
        FnList<T> last = findLastAndCheck(sorted, x, cmp);
        if (last != null) {
            // x goes at the end, just append it
            return appendAtPosition(sorted, x, last);
        }
        
        // General case: need to insert in the middle
        return insertInMiddle(x, sorted, cmp);
    }
    
    // Check if element should go at the end, return last node if so, null otherwise
    private static <T> FnList<T> findLastAndCheck(FnList<T> list, T x, ToIntBiFunction<T, T> cmp) {
        FnList<T> cur = list;
        FnList<T> prev = null;
        
        while (cur.consq()) {
            if (cmp.applyAsInt(x, cur.hd()) <= 0) {
                // x should not go at the end
                return null;
            }
            prev = cur;
            cur = cur.tl();
        }
        
        // x should go at the end
        return prev;
    }
    
    // Append x after the given position by reconstructing only the necessary part
    private static <T> FnList<T> appendAtPosition(FnList<T> list, T x, FnList<T> position) {
        // Build path from start to position, then add x
        FnList<T> reversed = new FnList<>();
        FnList<T> cur = list;
        
        while (cur != position) {
            reversed = new FnList<>(cur.hd(), reversed);
            cur = cur.tl();
        }
        
        // Add position node and x
        FnList<T> result = new FnList<>(x, new FnList<>());
        result = new FnList<>(position.hd(), result);
        
        // Reconstruct in correct order
        while (reversed.consq()) {
            result = new FnList<>(reversed.hd(), result);
            reversed = reversed.tl();
        }
        
        return result;
    }
    
    // Insert in the middle using the standard approach
    private static <T> FnList<T> insertInMiddle(T x, FnList<T> sorted, ToIntBiFunction<T, T> cmp) {
        FnList<T> prefix = new FnList<>();
        FnList<T> cur = sorted;
        
        // Find insertion point
        while (cur.consq() && cmp.applyAsInt(cur.hd(), x) <= 0) {
            prefix = new FnList<>(cur.hd(), prefix);
            cur = cur.tl();
        }
        
        // Build result: reverse prefix, add x, add remaining
        FnList<T> result = new FnList<>(x, cur);
        while (prefix.consq()) {
            result = new FnList<>(prefix.hd(), result);
            prefix = prefix.tl();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int pairs = 500000;
        FnList<Integer> xs = new FnList<>();
        
        for (int i = pairs - 1; i >= 0; --i) {
            xs = new FnList<>(2*i, xs);
            xs = new FnList<>(2*i + 1, xs);
        }
        
        long t0 = System.nanoTime();
        FnList<Integer> sorted = insertSort(xs, Integer::compare);
        long t1 = System.nanoTime();
        
        System.out.println("Sorted (first 20):");
        int shown = 0;
        FnList<Integer> cur = sorted;
        while (cur.consq() && shown < 20) { 
            System.out.print(cur.hd());
            if (shown < 19) System.out.print(", ");
            cur = cur.tl();
            shown++;
        }
        System.out.println();
        
        System.out.printf("Elapsed: %.3f ms%n", (t1 - t0)/1e6);
        
        // Verify correctness
        boolean isOrdered = true;
        Integer prev = null;
        cur = sorted;
        while (cur.consq()) {
            if (prev != null && prev > cur.hd()) {
                isOrdered = false;
                break;
            }
            prev = cur.hd();
            cur = cur.tl();
        }
        System.out.println("Correctly sorted: " + isOrdered);
    }
} // end of [public class Assign05_02{...}]