package MyLibrary.MyLibrary.FnGseq;

import MyLibrary.Functions.*;
import MyLibrary.MyRefer.*;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

import MyLibrary.FnList.FnList;
import MyLibrary.FnList.FnListSUtil;

public class FnGseq<XS, X0 extends Comparable<X0>> {
    static private class FnGseqExn extends RuntimeException {}

    static private class TrueExn extends RuntimeException {}
    static private class FalseExn extends RuntimeException {}

    static private class Count {
        int cnt = 0;
        Count() {
            cnt = 0;
        }
        int getInc() {
            int res = cnt; cnt = res + 1; 
            return res;
        }
    }

    public XS listMake(FnList<X0> xs) {
        throw new FnGseqExn();
    }
    public XS arrayMake(Fcns<X0> xs) {
        return listMake(xs.listsize());
    }

    public void SystemOutPrint(XS xs) {
        System.out.print("FnGseq(");
        iforitm( 
            xs, 
            (i, itm) ->  {
                if (i > 0) {
                    System.out.print(",");

                }
                System.out.print(itm.toString());
            }
        );
        System.out.print(")");
    }

    public int length(XS xs) {
        Integer r0 = 0;
        return folditm(xs, r0, (r1, x0) -> r1+1);
    }

    public FnList<X0> listsize(XS xs) {
        FnList<X0> r0 = FnListSUtil.nil();
        return rfolditm(xs, r0, (X0 x0, FnList<X0> r1) -> FnListSUtil.cons(x0, r1));
    }

    public FnList<X0> rlistsize(XS xs) {
        FnList<X0> r0 = FnListSUtil.nil();
        return folditm(xs, r0, (FnList<X0> r1, X0 x0) -> FnListSUtil.cons(x0, r1));
    }

    public Fcns<X0> toArray(XS xs) {
        int n0 = length(xs);
        final X0[] result = (X0[]) (new Object[n0]);
        iforitm(xs, (Integer i0, X0 x0) -> result[i0] = x0);
        return new Fcns(result);
    }

    public Fcns<X0> toRArray(XS xs) {
        int n0 = length(xs);
        final X0[] result = (X0[]) (new Object[n0]);
        irforitm(xs, (Integer i0, X0 x0) -> result[i0] = x0);
        return new Fcns(result);
    }
    public void foritm (XS xs, Consumer<? super X0> work) {
        listsize(xs).foritm(work); 
        return;
    }

    public void iforitm (XS xs, BiConsumer<Integer, ? super X0> work) {
        Count xcnt = new Count();
        foritm(
            xs, (X0 x0) -> work.accept(xcnt.getInc(), x0)
        );
        return;
    }

    public void rforitm (XS xs, Consumer<? super X0> work) {
        rlistsize(xs).foritm(work);
        return;
    }

    public void irforitm (XS xs, BiConsumer<Integer, ? super X0> work) {
        Count  xcnt = new Count();
        rforitm(xs, (X0 x0) -> work.accept(xcnt.getInc(), x0));
        return;
    }

    public boolean forall (XS xs, Predicate<? super X0> pred) {
        try {
            foritm (
                xs, (X0 x0) -> {
                    if (!pred.test(x0)) throw new FalseExn();
                }
            );
            return true;
        } catch (FalseExn e) {
            return false;
        }
    }

    public boolean iforall (XS xs, BiPredicate<Integer, ? super X0> pred) {
        Count xcnt = new Count();
        return forall (xs, (X0 x0) -> pred.test(xcnt.getInc(), x0));
    }

    public <R0> R0 folditm (XS xs, R0 r0, BiFunction<R0, ? super X0, R0> fopr) {
        final MyRefer<R0> rf = new MyRefer<R0>(r0);
        foritm(xs, (X0 x0) -> rf.setRaw(fopr.apply(rf.getRaw(), x0)));
        return rf.getRaw();
    }

    public <R0> R0 rfolditm (XS xs, R0 r0, BiFunction<? super X0, R0, R0> fopr) {
        return FnListSUtil.folditm (rlistsize(xs), r0, (R0 r1, X0 x0) -> fopr.apply(x0, r1));
    }
    public XS Mergesort (XS xs, ToIntBiFunction<X0, X0> cmp) {
        return listMake(MergesortList(xs, cmp));
    }
    public FnList<X0> MergesortList (XS xs, ToIntBiFunction<X0, X0> cmp) {
        return FnListSUtil.Mergesort(listsize(xs), cmp);
    }

    public Fcns<X0> MergesortArray (XS xs, ToIntBiFunction<X0, X0> cmp) {
        return FcnsSUtil.Mergesort(toArray(xs), cmp);
    }

    public XS Quicksort (XS xs, ToIntBiFunction<X0, X0> cmp) {
        return listMake(QuicksortList(xs, cmp));
    }

    public FnList<X0> QuicksortList (XS xs, ToIntBiFunction<X0, X0> cmp) {
        return FnListSUtil.Quicksort(listsize(xs), cmp);
    }

    public XS insertSort (XS xs, ToIntBiFunction<X0, X0> cmp) {
        return listMake(insertSortList(xs, cmp));
    }

    public FnList<X0> insertSortList (XS xs, ToIntBiFunction<X0, X0> cmp) {
        return FnListSUtil.insertSort(listsize(xs));
    }

    public boolean z2forall (XS xs, XS ys, BiPredicate<X0, X0> pred) {
        int sgn = z2forcmp( 
            xs, ys, 
            (X0 x0, X0 y0) -> (pred.test(x0, y0) ? 0 : 1)
        );
        return (0 == sgn);
    }

    public int z2forcmp (XS xs, XS ys, ToIntBiFunction<X0, X0> cmp) {
        Fcns<X0> us = toArray(xs);
        Fcns<X0> vs = toArray(ys);
        int n1 = us.length();
        int n2 = vs.length();
        int n0 = (n1 <= n2 ? n1 : n2);
        int sgn = 0;
        for (int i = 0; i < n0; i += 1) {
            sgn = cmp.applyAsInt(us.getAt(i), vs.getAt(i));
            if (sgn != 0) return sgn;
        }
        if (n1 < n2) return -1; else return (n1 > n2 ? 1 : 2);
    }
}
