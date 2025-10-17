package Library.FnA1sz;

import Library.FnGseq.*;
import Library.FnList.*;

import java.util.function.Consumer;

public class FnA1szUtil<X0>
    extends FnGseq<FnA1sz<X0>,X0> {
//
    @Override
    public int
	length(FnA1sz<X0> xs) {
	return xs.length(); // HX: O(1)
    }
//
    @Override
    public FnA1sz<X0>
	list$make(FnList<X0> xs) {
	return FnA1szSUtil.list$make(xs);
    }
//
    @Override
    public void
	foritm(FnA1sz<X0> xs, Consumer<? super X0> work) {
	xs.foritm(work); return;
    }
//
    @Override
    public void
	rforitm(FnA1sz<X0> xs, Consumer<? super X0> work) {
	xs.rforitm(work); return;
    }
//
} // end of [public class FnA1szUtil<X0>{...}]
