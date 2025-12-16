package MyLibrary.MyQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

interface MyQueue<T> {
    int size();

    boolean isFull();
    boolean isEmpty();

    T top$raw();
    T top$opt();
    T top$exn() throws MyQueueEmptyExn;

    T deque$raw();
    T deque$opt();
    T deque$exn() throws MyQueueEmptyExn;

    void enque$raw(T itm);
    void enque$exn(T itm) throws MyQueueFullExn;
    boolean enque$opt(T itm);

    void System$out$print();

    void foritm(Consumer<? super T> work);
    void iforitm(BiConsumer<Integer, ? super T> work);

    void rforitm(Consumer<? super T> work);
    void irforitm(BiConsumer<Integer, ? super T> work);
}
