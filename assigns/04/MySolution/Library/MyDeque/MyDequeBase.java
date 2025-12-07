package Library.MyDeque;

import Library.FnList.*;
import Library.MyRefer.*;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public abstract class MyDequeBase<T> implements MyDeque<T> {
//
    public
    boolean isEmpty()
    {
	return (size() <= 0);
    }
//
    public T fpeek$opt() {
	return (isEmpty() ? null : fpeek$raw());
    }
    public T fpeek$exn() throws MyDequeEmptyExn {
	T itm = fpeek$opt();
	if (itm != null)
	    return itm; else throw new MyDequeEmptyExn();
    }
    public T rpeek$opt() {
	return (isEmpty() ? null : rpeek$raw());
    }
    public T rpeek$exn() throws MyDequeEmptyExn {
	T itm = rpeek$opt();
	if (itm != null)
	    return itm; else throw new MyDequeEmptyExn();
    }
//
    public T fdeque$opt() {
	return (isEmpty() ? null : fdeque$raw());
    }
    public T fdeque$exn() throws MyDequeEmptyExn {
	T itm = fdeque$opt();
	if (itm != null)
	    return itm; else throw new MyDequeEmptyExn();
    }
    public T rdeque$opt() {
	return (isEmpty() ? null : rdeque$raw());
    }
    public T rdeque$exn() throws MyDequeEmptyExn {
	T itm = rdeque$opt();
	if (itm != null)
	    return itm; else throw new MyDequeEmptyExn();
    }
//
    public boolean fenque$opt(T itm) {
	if (isFull()) {
	    return false;
	} else {
	    fenque$raw(itm); return true;
	}
    }
    public void fenque$exn(T itm) throws MyDequeFullExn {
        boolean res = fenque$opt(itm);
	if (!res) throw new MyDequeFullExn(); else return;
    }
    public boolean renque$opt(T itm) {
	if (isFull()) {
	    return false;
	} else {
	    renque$raw(itm); return true;
	}
    }
    public void renque$exn(T itm) throws MyDequeFullExn {
        boolean res = renque$opt(itm);
	if (!res) throw new MyDequeFullExn(); else return;
    }
//
    public void System$out$print() {
    	System.out.print("MyDeque(");
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
    public void
    rforitm(Consumer<? super T> work) {
/*
        HX: this does NOT work!!!
	FnList itms;
	itms = new FnList<T>();
	foritm(itm -> itms = new FnList(itm, itms));
	itms.foritm(work);
*/
        final
	MyRefer<FnList<T>> itms =
	    new MyRefer<FnList<T>>(new FnList<T>());
	foritm(
	  itm ->
	  itms.set$raw(new FnList<T>(itm, itms.get$raw()))
        ); // end of [foritm]
	(itms.get$raw()).foritm(work);
    }
//
    public void
    irforitm(BiConsumer<Integer, ? super T> work) {
        final
	MyRefer<FnList<T>> itms =
	    new MyRefer<FnList<T>>(new FnList<T>());
	foritm(
	  itm ->
	  itms.set$raw(new FnList<T>(itm, itms.get$raw()))
        ); // end of [foritm]
	(itms.get$raw()).iforitm(work);
    }
//
} // end of [public abstract class MyDequeBase<T>{...}]
