package Library.FnStrn;

import Library.FnGseq.*;
import Library.FnList.*;
import Library.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnStrnSUtil {
//
    public static
	FnStrn
	list$make(FnList<Character> xs) {
	return new FnStrn(xs);
    }
//
    public static
	void foritm
	(FnStrn xs, Consumer<? super Character> work) {
	xs.foritm(work); return;
    }
    public static
	void rforitm
	(FnStrn xs, Consumer<? super Character> work) {
	xs.rforitm(work); return;
    }
    public static
	void iforitm
	(FnStrn xs,
	 BiConsumer<Integer, ? super Character> work) {
	xs.iforitm(work); return;
    }
    public static
	void irforitm
	(FnStrn xs,
	 BiConsumer<Integer, ? super Character> work) {
	xs.irforitm(work); return;
    }
//
    public static
	FnStrn fwork$make
	(Consumer<Consumer<? super Character>> fwork) {
	return list$make(FnListSUtil.fwork$make(fwork));
    }
//
    public static
	FnStrn append
	(FnStrn xs1, FnStrn xs2) { return xs1.append(xs2); }
    public static
	FnStrn append
	(FnStrn xs1, FnStrn xs2, FnStrn xs3) {
	return fwork$make
	  ((Consumer<? super Character> work) ->
	   { xs1.foritm(work); xs2.foritm(work); xs3.foritm(work); });
    }
    public static
	FnStrn append
	(FnStrn xs1, FnStrn xs2, FnStrn xs3, FnStrn xs4) {
	return fwork$make
	  ((Consumer<? super Character> work) ->
	   { xs1.foritm(work); xs2.foritm(work); xs3.foritm(work); xs4.foritm(work); });
    }
//
} // end of [public class FnStrnSUtil<X0>{...}]
