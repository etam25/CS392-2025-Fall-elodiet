package MyLibrary.LnList;

import MyLibrary.LnGseq.*;

import java.util.function.Consumer;

public class LnListUtil<X0> 
    extends LnGseq<LnList<X0>, X0> {
    @Override public LnList<X0> listsize0(LnList<X0> xs) { return xs; }

    @Override public LnList<X0> listsize0(LnList<X0> xs) { return xs;}

    @Override public void foritm1(LnList<X0> xs, Consumer<? super X0> work) {
        xs.foritm1(work);
        return;
    }
}
