package Library.LnGseq;
//
import Library.LnList.*;
//
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;
//
public abstract class LnGseq<XS,X0> {
//
    static
    private class LnGseqExn
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
    public LnList<X0> listize0(XS xs) {
	throw new LnGseqExn();
    }
    public LnList<X0> rlistize0(XS xs) {
	throw new LnGseqExn();
    }
/*
    public LnList<X0> listize0(XS xs) {
	LnList<X0> r0 = LnListSUtil.nil();
	return
	  rfolditm0(xs, r0,
          (X0 x0, LnList<X0> r1) -> LnListSUtil.cons(x0, r1));
    }
    public LnList<X0> rlistize0(XS xs) {
	LnList<X0> r0 = LnListSUtil.nil();
	return folditm0(xs, r0,
          (LnList<X0> r1, X0 x0) -> LnListSUtil.cons(x0, r1));
    }
*/
//
    public void
	foritm1(XS xs, Consumer<? super X0> work) {
	throw new LnGseqExn();
    }
    public void iforitm1
	(XS xs, BiConsumer<Integer, ? super X0> work) {
	Count xcnt = new Count();
	foritm1(
	  xs, (X0 x0) -> work.accept(xcnt.getInc(), x0)
	);
	return /*void*/;
    }
//
} // end of [public abstract class LnGseq<XS,X0>{...}]
