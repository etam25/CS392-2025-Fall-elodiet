import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyStackList<T> extends MyStackBase<T> {

    int nitm = -1;
    Node itms = null;

    private class Node {
        private T item;
        private Node next;
        
        private Node(T itm, Node nxt) {
            item = itm;
            next = nxt;
        }
    }

    public MyStackList() {
	nitm = 0; itms = null;
    }

    public int size() {
	return nitm;
    }

    public boolean isFull() {
	return false;
    }

    public T top$raw() {
	return itms.item;
    }

    public T pop$raw() {
	T itm = itms.item;
	itms = itms.next;
	nitm -= 1; return itm;
    }

    public void push$raw(T itm) {
	itms = new Node(itm, itms);
	nitm += 1; return;
    }

    public void foritm(Consumer<? super T> action) {
	Node xs = itms;
	while (xs != null) {
	    action.accept(xs.item); xs = xs.next;
	}
    }

    public void
	iforitm(BiConsumer<Integer, ? super T> action) {
	int i = 0;
	Node xs = itms;
	while (xs != null) {
	    action.accept(i, xs.item); i += 1; xs = xs.next;
	}
    }
}
