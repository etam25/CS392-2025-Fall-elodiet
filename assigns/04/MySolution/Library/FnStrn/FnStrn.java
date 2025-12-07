package Library.FnStrn;

import Library.FnList.*;
import Library.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import java.util.function.ToIntBiFunction;

public class FnStrn {
//
    char[] root;
//    
    public final
    FnStrnUtil U0 = new FnStrnUtil();
    public final
    FnStrnSUtil SU = new FnStrnSUtil();
//
    public FnStrn(char[] xs) {
	root = xs;
    }
    public FnStrn(String xs) {
	int n = xs.length();
	root = new char[n];
	for (int i = 0; i < n; i += 1) {
	    root[i] = xs.charAt(i);
	}
    }
    public FnStrn(char ch) {
	root = new char[1]; root[0] = ch;
    }
    public FnStrn(FnList<Character> xs) {
	int n = xs.length();
	root = new char[n];
	xs.iforitm((Integer i0, Character x0) -> root[i0] = x0);
    }
//
    public int length() {
	return root.length;
    }
//
    public char getAt(int i) {
	return root[i];
    }
//
    public void System$out$print() {
    	System.out.print("FnStrn(");
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
    public FnStrn
	append(FnStrn xs) {
	int n0 = root.length;
	int n1 = xs.length();
	char[] rs = new char[n0+n1];
	for (int i = 0; i < n0; i += 1) {
	    rs[i] = root[i];
	}
	for (int i = 0; i < n1; i += 1) {
	    rs[n0+i] = xs.getAt(i);
	}
	return new FnStrn(rs);
    }
//
    public FnStrn reverse() {
	int n0 = root.length;
	char[] rs = new char[n0];
	for (int i = 0; i < n0; i += 1) {
	    rs[i] = root[n0-1-i];
	}
	return new FnStrn(rs);
    }
//
    public void
	foritm(Consumer<? super Character> work) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    work.accept(root[i]);
	}
    }
    public void
	rforitm(Consumer<? super Character> work) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    work.accept(root[n-1-i]);
	}
    }
    public void
	iforitm(BiConsumer<Integer, ? super Character> work) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    work.accept(i, root[i]);
	}
    }
    public void
	irforitm(BiConsumer<Integer, ? super Character> work) {
	int n = root.length;
	for (int i = 0; i < n; i += 1) {
	    work.accept(i, root[n-1-i]);
	}
    }
//
    public FnStrn
	mergeSort() {
	return mergeSort((c1, c2) -> c1.compareTo(c2));
    }
    public FnStrn
	insertSort() {
	return insertSort((c1, c2) -> c1.compareTo(c2));
    }
    public FnStrn
	mergeSort
	(ToIntBiFunction<Character,Character> cmp) { return this.U0.mergeSort(this, cmp); }
    public FnStrn
	insertSort
	(ToIntBiFunction<Character,Character> cmp) { return this.U0.insertSort(this, cmp); }
//
} // end of [public class FnStrn{...}]
