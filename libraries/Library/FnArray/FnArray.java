import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class FnArray<T> {
    T[] root;
//    
    public FnArray(T[] xs) {
	int n = xs.length;
	root = (T[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    root[i] = xs[i];
	}
    }
//
    public T sub(int i) {
	return root[i];
    }
//
    public int length() {
	return root.length;
    }
//
    public void System$out$print() {
    	System.out.print("FnArray(");
	this.iforitm
	(
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }
//
    void foritm(Consumer<? super T> action) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    action.accept(root[i]);
	}
    }
    void rforitm(Consumer<? super T> action) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    action.accept(root[n-1-i]);
	}
    }
    void iforitm(BiConsumer<Integer, ? super T> action) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    action.accept(i, root[i]);
	}
    }
    void irforitm(BiConsumer<Integer, ? super T> action) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    action.accept(i, root[n-1-i]);
	}
    }
//
    boolean forall(Predicate<? super T> pred) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    if (!pred.test(root[i])) return false;
	}
	return true;
    }
    boolean rforall(Predicate<? super T> pred) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    if (!pred.test(root[n-1-i])) return false;
	}
	return true;
    }
    boolean iforall(BiPredicate<Integer, ? super T> pred) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    if (!pred.test(i, root[i])) return false;
	}
	return true;
    }
    boolean irforall(BiPredicate<Integer, ? super T> pred) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    if (!pred.test(i, root[n-1-i])) return false;
	}
	return true;
    }
} // end of [public class FnArray<T>{...}]
