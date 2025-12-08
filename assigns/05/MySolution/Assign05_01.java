import MyLibrary.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_01 {

    public static <T extends Comparable<T>> FnList<T> mergeSort(FnList<T> xs) {
	    return mergeSort(xs, (x1, x2) -> x1.compareTo(x2));
    }

    public static<T> FnList<T> mergeSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	    if (xs == null || xs.nilq() || xs.tl().nilq()) return xs;

        Split<T> halves = splitAlternate(xs);
        FnList<T> left = mergeSort(halves.a, cmp);
        FnList<T> right = mergeSort(halves.b, cmp);
        return merge(left, right, cmp);
    }

    private static final class Split<T> {
        final FnList<T> a, b;
        Split(FnList<T> a, FnList<T> b) { this.a = a; this.b = b; }
    }

    private static <T> FnList<T> cons(T x, FnList<T> xs) {
        return new FnList<>(x, xs);
    }

    private static <T> FnList<T> reverse(FnList<T> xs) {
        FnList<T> acc = new FnList<>();
        while (!xs.nilq()) {
            acc = cons(xs.hd(), acc);
            xs = xs.tl();
        }
        return acc;
    }

    private static <T> Split<T> splitAlternate(FnList<T> xs) {
        FnList<T> aRev = new FnList<>();
        FnList<T> bRev = new FnList<>();
        boolean toA = true;;
        while (!xs.nilq()) {
            if (toA) aRev = cons(xs.hd(), aRev);
            else bRev = cons(xs.hd(), bRev);
            toA = !toA;
            xs = xs.tl();
        }
        return new Split<>(reverse(aRev), reverse(bRev));
    }

    private static <T> FnList<T> merge(FnList<T> xs, FnList<T> ys, ToIntBiFunction<T, T> cmp) { 
        FnList<T> outRev = new FnList<>();
        FnList<T> a = xs, b = ys;

        while (!a.nilq() && !b.nilq()) {
            T ah = a.hd(), bh = b.hd();
            if (cmp.applyAsInt(ah, bh) <= 0) {
                outRev = cons(ah, outRev);
                a = a.tl();
            } else {
                outRev = cons(bh, outRev);
                b = b.tl();
            }
        }
        while (!a.nilq()) { outRev = cons(a.hd(), outRev); a = a.tl(); }
        while (!b.nilq()) { outRev = cons(b.hd(), outRev); b = b.tl(); }
        return reverse(outRev);
    }

    public static void main(String[] args) {
        final int N = 1000000;
        Random rng = new Random(42);
        FnList<Integer> xs = new FnList<>();
        for (int i = 0; i < N; i++) { 
            xs = cons(rng.nextInt(), xs);
        }

        long t0 = System.currentTimeMillis();
        FnList<Integer> ys = mergeSort(xs);
        long t1 = System.currentTimeMillis();
        System.out.println("Sorted " + N + " integers in " + (t1 - t0) + " ms");

        boolean ok = true;
        Integer prev = null;
        FnList<Integer> it = ys;
        int shown = 0;
        System.out.print("First 10: ");
        while (!it.nilq()) {
            Integer cur = it.hd();
            if (prev != null && prev > cur) { ok = false; break;}
            if (shown < 10) {
                System.out.print(cur + (shown < 9 ? ", " : "\n"));
                shown++;
            }
            prev = cur;
            it = it.tl();
        }
        System.out.println("Non-decreasing check: " + (ok ? "OK" : "FAILED"));
    }

} // end of [public class Assign05_01{...}]

