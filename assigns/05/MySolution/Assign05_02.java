import MyLibrary.FnList.*;
    
import java.util.function.ToIntBiFunction;

public class Assign05_02 {
    
    public static <T extends Comparable<T>> FnList<T> insertSort(FnList<T> xs) {
        return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    
    public static<T> FnList<T> insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
        if (xs == null || xs.nilq()) {
            return xs;
        }
        
        // Start with first element as sorted, maintain last element for optimization
        FnList<T> sorted = new FnList<>(xs.hd(), new FnList<>());
        T lastElement = xs.hd();
        FnList<T> cur = xs.tl();
        
        while (cur.consq()) {
            T x = cur.hd();
            
            // Fast path for nearly sorted lists: if x >= lastElement, append it
            // This avoids traversing the list to find insertion point
            if (cmp.applyAsInt(lastElement, x) <= 0) {
                sorted = appendFast(sorted, x);
                lastElement = x;
            } else {
                // Need to insert in the middle - do it in one pass
                InsertResult<T> result = insertWithLast(x, sorted, lastElement, cmp);
                sorted = result.list;
                lastElement = result.lastElement;
            }
            cur = cur.tl();
        }
        
        return sorted;
    }
    
    // Helper class to return both the new list and the last element
    private static final class InsertResult<T> {
        final FnList<T> list;
        final T lastElement;
        InsertResult(FnList<T> list, T lastElement) {
            this.list = list;
            this.lastElement = lastElement;
        }
    }
    
    // Efficient insert that returns the new last element to avoid recomputing it
    private static <T> InsertResult<T> insertWithLast(T x, FnList<T> sorted, T currentLast, ToIntBiFunction<T, T> cmp) {
        // Check if x should be first
        if (sorted.nilq() || cmp.applyAsInt(x, sorted.hd()) <= 0) {
            return new InsertResult<>(new FnList<>(x, sorted), currentLast);
        }
        
        // Build prefix in reverse as we find insertion point
        FnList<T> prefix = new FnList<>();
        FnList<T> cur = sorted;
        
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
        
        // Since x < currentLast (we checked before calling this), x cannot be the new last.
        // The last element remains currentLast.
        return new InsertResult<>(result, currentLast);
    }
    
    // Fast append when we know element goes at the end
    // Rebuilds list once without checking insertion point
    private static <T> FnList<T> appendFast(FnList<T> list, T x) {
        // Rebuild list with x appended
        FnList<T> reversed = new FnList<>();
        FnList<T> cur = list;
        while (cur.consq()) {
            reversed = new FnList<>(cur.hd(), reversed);
            cur = cur.tl();
        }
        // Add x at the end
        reversed = new FnList<>(x, reversed);
        // Reverse back
        FnList<T> result = new FnList<>();
        while (reversed.consq()) {
            result = new FnList<>(reversed.hd(), result);
            reversed = reversed.tl();
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