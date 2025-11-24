package Library.FnInt1;

import Library.FnGseq.*;
import Library.FnList.*;
import Library.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnInt1SUtil {
//
    public static
	void foritm
	(FnInt1 xs, Consumer<? super Integer> work) {
	xs.foritm(work); return;
    }
    public static
	void rforitm
	(FnInt1 xs, Consumer<? super Integer> work) {
	xs.rforitm(work); return;
    }
//
} // end of [public class FnInt1SUtil<X0>{...}]
