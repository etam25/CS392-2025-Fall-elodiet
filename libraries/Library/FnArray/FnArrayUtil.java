import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class FnArrayUtil {
//
    public static<T>
	void System$out$print(FnArray<T> xs) {
    	System.out.print("FnArray(");
	FnArrayUtil.iforitm
        ( xs,
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
    public static
	FnArray<Integer>
	list_make_int1(int n0) {
	Integer[] xs = new Integer[n0];
	for (int i0 = 0; i0 < n0; i0 += 1) {
	    xs[i0] = i0;
	}
	return new FnArray<Integer>(xs);
    }
//
    public static<T,R>
	FnArray<R> map_array
	(FnArray<T> xs, Function<? super T, R> fopr) {
	int n = xs.length();
	R[] res = (R[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    res[i] = fopr.apply(xs.sub(i));
	}
	return new FnArray<R>(res);
    }
    public static<T,R>
	FnArray<R> rmap_array
	(FnArray<T> xs, Function<? super T, R> fopr) {
	int n = xs.length();
	R[] res = (R[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    res[i] = fopr.apply(xs.sub(n-1-i));
	}
	return new FnArray<R>(res);
    }
    public static<T,R>
	FnArray<R> imap_array
	(FnArray<T> xs, BiFunction<Integer, ? super T, R> fopr) {
	int n = xs.length();
	R[] res = (R[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    res[i] = fopr.apply(i, xs.sub(i));
	}
	return new FnArray<R>(res);
    }
    public static<T,R>
	FnArray<R> irmap_array
	(FnArray<T> xs, BiFunction<Integer, ? super T, R> fopr) {
	int n = xs.length();
	R[] res = (R[])(new Object[n]);
	for (int i = 0; i < n; i += 1) {
	    res[i] = fopr.apply(i, xs.sub(n-1-i));
	}
	return new FnArray<R>(res);
    }
//
    public static<T>
	void foritm(FnArray<T> xs, Consumer<? super T> action) {
	xs.foritm(action); return;
    }
    public static<T>
	void rforitm(FnArray<T> xs, Consumer<? super T> action) {
	xs.foritm(action); return;
    }
    public static<T>
	void iforitm(FnArray<T> xs, BiConsumer<Integer, ? super T> action) {
	xs.iforitm(action); return;
    }
    public static<T>
	void irforitm(FnArray<T> xs, BiConsumer<Integer, ? super T> action) {
	xs.iforitm(action); return;
    }
//
    public static<T,R>
	R folditm
	(FnArray<T> xs, R r0, BiFunction<R, ? super T, R> fopr) {
	R res = r0;
	int n = xs.length();
	for (int i = 0; i < n; i += 1) {
	    res = fopr.apply(res, xs.sub(i));
	}
	return res;
    }
//
    public static<T,R>
	R rfolditm
	(FnArray<T> xs, R r0, BiFunction<? super T, R, R> fopr) {
	R res = r0;
	int n = xs.length();
	for (int i = 0; i < n; i += 1) {
	    res = fopr.apply(xs.sub(n-1-i), res);
	}
	return res;
    }
//
} // end of [public class FnArrayUtil{...}]
