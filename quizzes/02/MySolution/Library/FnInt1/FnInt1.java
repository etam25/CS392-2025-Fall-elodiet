package Library.FnInt1;

import Library.FnList.*;
import Library.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import java.util.function.ToIntBiFunction;

public class FnInt1 {
//
    int root;
//    
    public final
    FnInt1Util U0 = new FnInt1Util();
    public final
    FnInt1SUtil SU = new FnInt1SUtil();
//
    public FnInt1(int xs) {
	root = xs;
    }
    public int length() {
	return (root <= 0 ? 0 : root);
    }
//
    public int getAt(int i) { return i; }
//
    public void System$out$print() {
    	System.out.print("FnInt1(");
	this.foritm
	(
          (itm) ->
	  {
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }
//
    public void
	foritm(Consumer<? super Integer> work) {
	int n = root;
	for (int i = 0; i < n; i += 1) work.accept(i);
    }
    public void
	rforitm(Consumer<? super Integer> work) {
	int n = root;
	for (int i = 0; i < n; i += 1) work.accept(n-1-i);
    }
//
} // end of [public class FnInt1{...}]
