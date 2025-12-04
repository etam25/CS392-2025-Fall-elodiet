package MyLibrary.MyLibrary.MyQueue;

import java.util.function.Consumer;

import org.w3c.dom.Node;

import java.util.function.BiConsumer;

public class MyQueueList<T> extends MyQueueBase<T> {
    int nitm = -1;
    Node first = null;
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
        nitm = 0;
        first = null;
        last = null;
    }

    @Override
    public int size() {
        return nitm;
    }

    @Override 
    public boolean isFull() {
        return false;
    }

    @Override 
    public T topraw() {
        return first.item;
    }

    @Override
    public  T dequeraw() {
        T itm = first.item;
        first = first.next;
        if (first == null) last = null;
        nitm -= 1; 
        return itm;
    }

    @Override 
    public void enqueraw(T itm) {
        if (last == null) {
            last = new Node(itm, null);
            first = last;
        }

        nitm += 1; 
        return;
    }
    @Override 
    public void foritm(Consumer<? super T> work) {
        Node xs = first;
        while (xs != null) {
            work.accept(xs.item);
            xs = xs.next;
        }
    }

    @Override 
    public void iforitm(BiConsumer<Integer, ? super T> work) {
        int i = 0;
        Node xs = first;
        while (xs != null) {
            work.accept(i, xs.item);
            i += 1;
            xs = xs.next;
        }
    }

}
