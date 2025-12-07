package Library.LnList;

import Library.LnGseq.*;

import java.util.function.Consumer;

public class LnListUtil<X0>
    extends LnGseq<LnList<X0>,X0> {
//
    @Override
    public LnList<X0>
	listize0(LnList<X0> xs) { return xs; }
    @Override
    public LnList<X0>
	rlistize0(LnList<X0> xs) { return xs.reverse0(); }
//
    @Override
    public void
	foritm1(LnList<X0> xs, Consumer<? super X0> work) {
	xs.foritm1(work); return /*void*/;
    }
//
} // end of [public class LnListUtil<X0>{...}]
