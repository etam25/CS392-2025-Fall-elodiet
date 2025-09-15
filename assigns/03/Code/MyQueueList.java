import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyQueueFnList2<T> extends MyQueueBase<T> {

    int nitm = -1;
    Node frst = null;
    Node last = null;

    private class Node {
        private T item;
        private Node next;
        
        private Node(T itm, Node nxt) {
            item = itm;
            next = nxt;
        }
    }

    public MyQueueList() {
	nitm = 0; frst = null; last = null;
    }

    public int size() {
	return nitm;
    }

    public boolean isFull() {
	return false;
    }

    public T top$raw() {
	return frst.item;
    }

    public T deque$raw() {
    }

    public void enque$raw(T itm) {
    }
}
