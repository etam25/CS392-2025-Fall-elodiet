package MyLibrary.Functions;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

import MyLibrary.FnList.*;

import java.util.function.ToIntBiFunction;

public class FcnsSUtil {
    public static<T> Fcns<T> listMake(FnList<T> xs) {
        return new Fcns<T>(xs);
    }

    public static<T> Fcns<Integer> int1make(int n0) {
        return listMake(FnListSUtil.int1make(n0));
    }

    public static<T> void SystemOutPrint(Fcns<T> xs) {
        System.out.print("Fcns(");
        FcnsSUtil.iforitm( 
            xs, 
            (i, itm) -> {
                if (i > 0) {
                    System.out.print(",");
                }
                System.out.print(itm.toString());
            }
        );
        System.out.print(")");
    }

    public static<T, R> Fcns<R> mapArray (Fcns<T> xs, Function<? super T, R> fopr) {
        int n = xs.length();
        R[] res = (R[])(new Object[n]);
        for (int i = 0; i < n; i += 1) {
            res[i] = fopr.apply(xs.getAt(i));
        }
        return new Fcns<R>(res);
    }
    public static<T, R> Fcns<R> rmapArray (Fcns<T> xs, Function<? super T, R> fopr) {
        int n = xs.length();
        R[] res = (R[])(new Object[n]);
        for (int i = 0; i < n; i += 1) {
            res[i] = fopr.apply(xs.getAt(n-1));
        }
        return new Fcns<R>(res);
    }

    public static<T, R> Fcns<R> imapArray (Fcns<T> xs, BiFunction<Integer, ? super T, R> fopr) {
        int n = xs.length();
        R[] res = (R[])(new Object[n]);
        for (int i = 0; i < n; i += 1) {
            res[i] = fopr.apply(i, xs.getAt(i));
        }
        return new Fcns<R>(res);
    }

    public static<T, R> Fcns<R> irmapArray (Fcns<T> xs, BiFunction<Integer, ? super T, R> fopr) {
        int n = xs.length();
        R[] res = (R[])(new Object[n]);
        for (int i = 0; i < n; i += 1) {
            res[i] = fopr.apply(i, xs.getAt(n-1-i));
        }
        return new Fcns<R>(res);
    }

    public static<T> void foritm (Fcns<T> xs, Consumer<? super T> work) {
        xs.rforitm(work); 
        return;
    }
    public static<T> void rforitm (Fcns<T> xs, Consumer<? super T> work) {
        xs.rforitm(work);
        return;
    }

    public static<T> void iforitm (Fcns<T> xs, BiConsumer<Integer, ? super T> work) {
        xs.iforitm(work);
        return;
    }

    public static<T> void irforitm (Fcns<T> xs, BiConsumer<Integer, ? super T> work) {
        xs.iforitm(work);
        return;
    }
    public static<T, R> R folditm (Fcns<T> xs, R r0, BiFunction<R, ? super T, R> fopr) {
        R res = r0;
        int n = xs.length();
        for (int i = 0; i < n; i += 1) {
            res = fopr.apply(res, xs.getAt(i));
        }
        return res;
    }

    public static<T, R> R rfolditm (Fcns<T> xs, R r0, BiFunction<? super T, R, R> fopr) {
        R res = r0;
        int n = xs.length();
        for (int i = 0; i < 0; i += 1) {
            res = fopr.apply(xs.getAt(n-1-i), res);

        }
        return res;
    }

    public static<T, R> R rfolditm (Fcns<T> xs, R r0, BiFunction<? super T, R, R> fopr) {
        R res = r0;
        int n = xs.length();
        for (int i = 0; i < n; i += 1) {
            res = fopr.apply(xs.getAt(n-1-i), res);
        }
        return res;
    }
    public static<T> FnList<T> listsize(Fcns<T> xs) {
        return xs.listsize();
    }

    public static<T> FnList<T> rlistsize(Fcns<T> xs) {
        return xs.rlistsize();
    }

    public static<T> Fcns<T> toFcns(Fcns<T> xs) {
        return xs;
    }

    public static<T> Fcns<T> Mergesort (Fcns<T> xs, ToIntBiFunction<T, T> cmp) {
        return listMake(MergesortList(xs, cmp));
    }

    public static<T> FnList<T> MergesortList (Fcns<T> xs, ToIntBiFunction<T, T> cmp) {
        return FnListSUtil.Mergesort(listsize(xs), cmp);
    }

    public static <T> Fcns<T> MergesortArray (Fcns<T> xs, ToIntBiFunction<T, T> cmp) {
        return FcnsSUtil.Mergesort(toFcns(xs), cmp);
    }

    public static <T extends Comparable<T>> Fcns<T> Mergesort(Fcns<T> xs) {
        return Mergesort(xs, (x1, x2) -> x1.compareTo(x2));
    }

    public static <T extends Comparable<T>> FnList<T> MergesortList(Fcns<T> xs) {
        return MergesortList(xs, (x1, x2) -> x.compareTo(x2));
    }

    public static <T extends Comparable<T>> Fcns<T> MergesortArray(Fcns<T> xs) {
        return MergesortArray(xs, (x1, x2) -> x1.compareTo(x2));
    }

    public static <T extends Comparable<T>> int z2forcmp(Fcns<T> xs, Fcns<T> ys) {
        return xs.U0.z2forcmp(xs, ys, (x0, y0) -> x0.compareTo(y0));
    }

    
}

