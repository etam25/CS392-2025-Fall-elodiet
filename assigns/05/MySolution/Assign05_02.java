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
        FnList<T> acc = new FnList<>();
        FnList<T> cur = xs;
        while (cur.consq()) {
            acc = insert(cur.hd(), acc, cmp);
            cur = cur.tl();
        }
        return acc;
    }

    private static <T> FnList<T> insert(T x, FnList<T> ys, ToIntBiFunction<T, T> cmp) {
        FnList<T> prefix = new FnList<>();
        FnList<T> cur = ys;

        while (cur.consq() && cmp.applyAsInt(cur.hd(), x) <= 0) {
            prefix = new FnList<>(cur.hd(), prefix);
            cur = cur.tl();
        }

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
    }

} // end of [public class Assign05_02{...}]
