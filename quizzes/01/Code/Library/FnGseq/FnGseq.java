package Library.FnGseq;
//
import Library.FnList.*;
import Library.FnA1sz.*;
import Library.MyRefer.*;
//
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;
//
public abstract class FnGseq<XS,X0> {
//
    static
    private class FnGseqExn
      extends RuntimeException {}
//
    static
    private class TrueExn
      extends RuntimeException {}
    static
    private class FalseExn
      extends RuntimeException {}
//
    static private class Count {
	int cnt = 0;
	Count() {
	    cnt = 0;
	}
	int getInc() {
	    int res = cnt; cnt = res+1; return res;
	}
    }
//
    public XS
    list$make(FnList<X0> xs) {
	throw new FnGseqExn();
    }
    public XS
    array$make(FnA1sz<X0> xs) {
	return list$make(xs.listize());
    }
//
    public void
	System$out$print(XS xs) {
    	System.out.print("FnGseq(");
	iforitm
        ( xs,
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }
//
    public int length(XS xs) {
	Integer r0 = 0;
	return folditm(xs, r0, (r1, x0) -> r1+1);
    }
//
    public FnList<X0> listize(XS xs) {
	FnList<X0> r0 = FnListSUtil.nil();
	return
	  rfolditm(xs, r0,
          (X0 x0, FnList<X0> r1) -> FnListSUtil.cons(x0, r1));
    }
    public FnList<X0> rlistize(XS xs) {
	FnList<X0> r0 = FnListSUtil.nil();
	return folditm(xs, r0,
          (FnList<X0> r1, X0 x0) -> FnListSUtil.cons(x0, r1));
    }
//
    public FnA1sz<X0> toArray(XS xs) {
	int n0 = length(xs);
	final X0[] result = (X0[]) (new Object[n0]);
	iforitm(xs, (Integer i0, X0 x0) -> result[i0] = x0);
	return new FnA1sz(result);
    }
    public FnA1sz<X0> toRArray(XS xs) {
	int n0 = length(xs);
	final X0[] result = (X0[]) (new Object[n0]);
	irforitm(xs, (Integer i0, X0 x0) -> result[i0] = x0);
	return new FnA1sz(result);
    }
//
    public void foritm
	(XS xs, Consumer<? super X0> work) {
	listize(xs).foritm(work); return /*void*/;
    }
    public void iforitm
	(XS xs, BiConsumer<Integer, ? super X0> work) {
	Count xcnt = new Count();
	foritm(
	  xs, (X0 x0) -> work.accept(xcnt.getInc(), x0)
	);
	return /*void*/;
    }
//
    public void rforitm
	(XS xs, Consumer<? super X0> work) {
	rlistize(xs).foritm(work); return /*void*/;
    }
    public void irforitm
	(XS xs, BiConsumer<Integer, ? super X0> work) {
	Count xcnt = new Count();
	rforitm(
	  xs, (X0 x0) -> work.accept(xcnt.getInc(), x0)
	);
	return /*void*/;
    }
//
    public boolean forall
	(XS xs, Predicate<? super X0> pred) {
	try {
	    foritm(
	      xs,
	      (X0 x0) -> {
		  if (!pred.test(x0)) throw new FalseExn();
	      }
	    );
	    return true;
	}
	catch (FalseExn e) {
	    return false;
	}
    }
    public boolean iforall
	(XS xs, BiPredicate<Integer, ? super X0> pred) {
	Count xcnt = new Count();
	return forall
	    (xs, (X0 x0) -> pred.test(xcnt.getInc(), x0));
    }
//
    public <R0> R0 folditm
	(XS xs, R0 r0, BiFunction<R0, ? super X0, R0> fopr) {
	final MyRefer<R0> rf = new MyRefer<R0>(r0);
	foritm(xs,
	  (X0 x0) -> rf.set$raw(fopr.apply(rf.get$raw(), x0)));
	return rf.get$raw();
    }
//
    public <R0> R0 rfolditm
	(XS xs, R0 r0, BiFunction<? super X0, R0, R0> fopr) {
	return FnListSUtil.folditm
	    (rlistize(xs), r0, (R0 r1, X0 x0) -> fopr.apply(x0, r1));
    }
//
    public XS
	mergeSort
	(XS xs, ToIntBiFunction<X0,X0> cmp) {
	return list$make(mergeSort_list(xs, cmp));
    }
    public FnList<X0>
	mergeSort_list
	(XS xs, ToIntBiFunction<X0,X0> cmp) {
	return FnListSUtil.mergeSort(listize(xs), cmp);
    }
    public FnA1sz<X0>
	mergeSort_array
	(XS xs, ToIntBiFunction<X0,X0> cmp) {
	return FnA1szSUtil.mergeSort(toArray(xs), cmp);
    }
//
    public XS
	quickSort
	(XS xs, ToIntBiFunction<X0,X0> cmp) {
	return list$make(quickSort_list(xs, cmp));
    }
    public FnList<X0>
	quickSort_list
	(XS xs, ToIntBiFunction<X0,X0> cmp) {
	return FnListSUtil.quickSort(listize(xs), cmp);
    }
//
    public XS
	insertSort
	(XS xs, ToIntBiFunction<X0,X0> cmp) {
	return list$make(insertSort_list(xs, cmp));
    }
    public FnList<X0>
	insertSort_list
	(XS xs, ToIntBiFunction<X0,X0> cmp) {
	return FnListSUtil.insertSort(listize(xs), cmp);
    }
//
    public boolean
	z2forall
	(XS xs, XS ys, BiPredicate<X0,X0> pred) {
	int sgn =
        z2forcmp(
	  xs, ys,
	  (X0 x0, X0 y0) -> (pred.test(x0, y0) ? 0 : 1)
	);
	return (0 == sgn);
    }
    public int
	z2forcmp
	(XS xs, XS ys, ToIntBiFunction<X0,X0> cmp) {
	FnA1sz<X0> us = toArray(xs);
	FnA1sz<X0> vs = toArray(ys);
	int n1 = us.length();
	int n2 = vs.length();
	int n0 = (n1 <= n2 ? n1 : n2);
	int sgn = 0;
	for (int i = 0; i < n0; i += 1) {
	    sgn = cmp.applyAsInt(us.getAt(i), vs.getAt(i));
	    if (sgn != 0) return sgn;
	}
	if (n1<n2) return -1; else return (n1 > n2 ? 1 : 0);
    }
//
} // end of [public abstract class FnGseq<XS,X0>{...}]
