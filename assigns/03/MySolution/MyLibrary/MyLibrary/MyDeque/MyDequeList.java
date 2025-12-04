package MyLibrary.MyLibrary.MyDeque;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

class NotImplementedExn extends RuntimeException{}

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
            prev=prv;
            next = nxt;
        }
    }

    public MyDequeList() {
        nitm = 0;
        frnt = null;
        rear = null;
    }

    public int size() { return nitm; }
    public boolean isFull() { return false; }

    public T fpeekraw() { return frnt.item; }
    public T rpeekraw() { return rear.item; }

    public T fdequeraw() { 
        throw new NotImplementedExn();
    }

    public T rdequeraw() {
        throw new NotImplementedExn();
    }

    public void fenqueraw(T itm) {
        throw new NotImplementedExn();
    }

    public void renqueraw(T itm) {
        throw new NotImplementedExn();
    }

    public void foritm(Consumer<? super T> work) {
        Node xs = frnt;
        while (xs != null) {
            work.accept(xs.item);
            xs = xs.next;
        }
    }

    public void rforitm(Consumer<? super T> work) {
        Node xs = rear;
        while (xs != null) {
            work.accept(xs.item);
            xs = xs.prev;
        }
    }

    public void iforitm(BiConsumer<Integer, ? super T> work) {
        int i = 0;
        Node xs = frnt;
        while (xs != null) {
            work.accept(i, xs.item);
            i += 1;
            xs = xs.next;
        }
    }

    public void irforitm(BiConsumer<Integer, ? super T> work) {
        int i = 0;
        Node xs = rear;
        while (xs != null) {
            work.accept(i, xs.item);
            i += 1;
            xs = xs.prev;
        }
    }
}
