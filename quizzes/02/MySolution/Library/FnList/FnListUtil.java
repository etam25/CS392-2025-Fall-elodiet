package Library.FnList;

import Library.FnGseq.*;

import java.util.function.Consumer;

public class FnListUtil<X0>
    extends FnGseq<FnList<X0>,X0> {
//
    @Override
    public FnList<X0>
    list$make(FnList<X0> xs) { return xs; }

    @Override
    public FnList<X0>
	listize(FnList<X0> xs) { return xs; }
    @Override
    public FnList<X0>
	rlistize(FnList<X0> xs) { return xs.reverse(); }
//
    @Override
    public void
	foritm(FnList<X0> xs, Consumer<? super X0> work) {
	xs.foritm(work); return /*void*/;
    }
//
} // end of [public class FnListUtil<X0>{...}]
