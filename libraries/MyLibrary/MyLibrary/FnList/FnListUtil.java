package FnList;

import java.util.function.Consumer;

import FnGseq.*;

public class FnListUtil<X0 extends Comparable<X0>>
    extends FnGseq<FnList<X0>,X0> {
    @Override
    public FnList<X0> listMake(FnList<X0> xs) { return xs; }

    public FnList<X0> listSize(FnList<X0> xs) { return xs; }
    public FnList<X0> rlistSize(FnList<X0> xs) { return xs.reverse(); }
    @Override
    public void foritm(FnList<X0> xs, Consumer<? super X0> work) {
	    xs.foritm(work); 
        return;
    }
}