import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class FnList<T> {
    Node root;
    
    private class Node {
	T head;
	FnList<T> tail;
	Node(T x0, FnList<T> xs) {
	    head = x0; tail = xs;
	}
    }

    public FnList() {
	root = null;
    }
    public FnList(T x0, FnList<T> xs) {
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
    public FnList<T> tl() {
	return root.tail;
    }
//
    public int length() {
	int res = 0;
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    res += 1; xs = xs.tl();
	}
	return res;
    }
//
    public FnList<T> reverse() {
	return FnListUtil.reverse(this);
    }
    public FnList<T>
	rappend(FnList<T> ys) {
	return FnListUtil.rappend(this, ys);
    }
//
    public void System$out$print() {
    	System.out.print("FnList(");
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
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    action.accept(xs.hd());
	    xs = xs.tl();
	}
    }
    void rforitm(Consumer<? super T> action) {
	FnList<T> xs = this.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    action.accept(xs.hd());
	    xs = xs.tl();
	}
    }
    void iforitm(BiConsumer<Integer, ? super T> action) {
	int i0 = 0;
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    action.accept(i0, xs.hd());
	    i0 += 1; xs = xs.tl();
	}
    }
    void irforitm(BiConsumer<Integer, ? super T> action) {
	int i0 = 0;
	FnList<T> xs = this.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    action.accept(i0, xs.hd());
	    i0 += 1; xs = xs.tl();
	}
    }
//
    boolean forall(Predicate<? super T> pred) {
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(xs.hd())) return false;
	    xs = xs.tl();
	}
	return true;
    }
    boolean rforall(Predicate<? super T> pred) {
	FnList<T> xs = this.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(xs.hd())) return false;
	    xs = xs.tl();
	}
	return true;
    }
    boolean iforall(BiPredicate<Integer, ? super T> pred) {
	int i0 = 0;
	FnList<T> xs = this;
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(i0, xs.hd())) return false;
	    i0 += 1; xs = xs.tl();
	}
	return true;
    }
    boolean irforall(BiPredicate<Integer, ? super T> pred) {
	int i0 = 0;
	FnList<T> xs = this.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    if (!pred.test(i0, xs.hd())) return false;
	    i0 += 1; xs = xs.tl();
	}
	return true;
    }
} // end of [public class FnList<T>{...}]
