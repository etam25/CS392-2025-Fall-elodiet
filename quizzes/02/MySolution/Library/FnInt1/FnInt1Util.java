package Library.FnInt1;

import Library.FnGseq.*;
import Library.FnList.*;
import Library.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnInt1Util
    extends FnGseq<FnInt1,Integer> {
//
    @Override
    public int
	length(FnInt1 xs) {
	return xs.length(); // HX: O(1)
    }
//
    @Override
    public void
	foritm(FnInt1 xs, Consumer<? super Integer> work) {
	xs.foritm(work); return;
    }
    @Override
    public void
	rforitm(FnInt1 xs, Consumer<? super Integer> work) {
	xs.rforitm(work); return;
    }
//
} // end of [public class FnInt1Util<X0>{...}]
