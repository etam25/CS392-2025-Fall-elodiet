package Library.MyDeque;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyDequeList<T> extends MyDequeBase<T> {

    int nitm = -1;
    Node frnt = null;
    Node rear = null;

    private class Node {
        private T item;
	private Node prev;
        private Node next;
        
        private Node(T itm, Node prv, Node nxt) {
            item = itm;
	    prev = prv;
            next = nxt;
        }
    }

    public MyDequeList() {
	nitm = 0; itms = null;
    }

    public int size() {
	return nitm;
    }

    public boolean isFull() {
	return false;
    }

    public T fpeek$raw() {
	return frnt.item;
    }
    public T rpeek$raw() {
	return rear.item;
    }

    public void foritm(Consumer<? super T> action) {
	Node xs = frnt;
	while (xs != null) {
	    action.accept(xs.item); xs = xs.next;
	}
    }

    public void rforitm(Consumer<? super T> action) {
	Node xs = rear;
	while (xs != null) {
	    action.accept(xs.item); xs = xs.prev;
	}
    }

    public void
	iforitm(BiConsumer<Integer, ? super T> action) {
	int i = 0;
	Node xs = frnt;
	while (xs != null) {
	    action.accept(i, xs.item); i += 1; xs = xs.next;
	}
    }

    public void
	irforitm(BiConsumer<Integer, ? super T> action) {
	int i = 0;
	Node xs = rear;
	while (xs != null) {
	    action.accept(i, xs.item); i += 1; xs = xs.prev;
	}
    }
}
