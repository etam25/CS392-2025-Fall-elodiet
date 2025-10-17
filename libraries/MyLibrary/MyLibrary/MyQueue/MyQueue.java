package MyLibrary.MyQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

interface MyQueue<T> {
    int size();

    boolean isFull();
    boolean isEmpty();

    T topraw();
    T topopt();
    T topexn() throws MyQueueEmptyExn;

    void enqueraw(T itm);
    void enqueexn(T itm) throws MyQueueFullExn;
    boolean enqueopt(T itm);

    void SystemOutPrint();

    void foritm(Consumer<? super T> work);
    void iforitm(BiConsumer<Integer, ? super T> work);

    void rforitm(Consumer<? super T> work);
    void irforitm(BiConsumer<Integer, ? super T> work);
}
