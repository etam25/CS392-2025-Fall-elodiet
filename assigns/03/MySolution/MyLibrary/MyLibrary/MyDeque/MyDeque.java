package MyLibrary.MyLibrary.MyDeque;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyDeque {
    int size();

    boolean isFull();
    boolean isEmpty();

    T fpeekraw();
    T fpeekopt();
    T fpeekexn() throws MyDequeEmptyExn;
    T rpeekraw();
    T rpeekopt();
    T rpeekexn() throws MyDequeEmptyExn;

    T fdequeraw();
    T fdequeopt();
    T fdequeexn() throws MyDequeEmptyExn;
    T rdequeraw();
    T rdequeopt();
    T rdequeexn() throws MyDequeEmptyExn;

    void fenqueraw(T itm);
    void fenqueexn(T itm) throws MyDequeFullExn;
    boolean fenqueopt(T itm);
    void renqueraw(T itm);
    void renqueexn(T itm) throws MyDequeFullExn;
    boolean renqueopt(T itm);

    void SystemOutPrint();

    void foritm(Consumer<? super T> work);
    void iforitm(BiConsumer<Integer, ? super T> work);

    void rforitm(Consumer<? super T> work);
    void irforitm(BiConsumer<Integer, ? super T> work);
}
