import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class LnList<T> {
    Node root;
    
    private class Node {
	T head;
	LnList<T> tail;
	Node(T x0, LnList<T> xs) {
	    head = x0; tail = xs;
	}
    }

    public LnList() {
	root = null;
    }
    public LnList(T x0, LnList<T> xs) {
	root = new Node(x0, xs);
    }

    public boolean nilq() {
	return (root == null);
    }
    public boolean consq() {
	return (root != null);
    }

    public T hd() {
	return root.head;
    }
    public LnList<T> tl() {
	return root.tail;
    }
    public LnList<T> detl() {
	LnList<T> xs = root.tail;
	root.tail = null; return xs;
    }
//
    public int length() {
	int res = 0;
	LnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    res += 1; xs = xs.tl();
	}
	return res;
    }
//
    public void System$out$print() {
    	System.out.print("LnList(");
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
	LnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    action.accept(xs.hd());
	    xs = xs.tl();
	}
    }
    void iforitm(BiConsumer<Integer, ? super T> action) {
	int i0 = 0;
	LnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    action.accept(i0, xs.hd());
	    i0 += 1; xs = xs.tl();
	}
    }
//
    boolean forall(Predicate<? super T> pred) {
	LnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(xs.hd())) return false;
	    xs = xs.tl();
	}
	return true;
    }
    boolean iforall(BiPredicate<Integer, ? super T> pred) {
	int i0 = 0;
	LnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(i0, xs.hd())) return false;
	    i0 += 1; xs = xs.tl();
	}
	return true;
    }
} // end of [public class LnList<T>{...}]
