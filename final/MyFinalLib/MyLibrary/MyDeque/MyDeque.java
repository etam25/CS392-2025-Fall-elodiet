package MyLibrary.MyDeque;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

interface MyDeque<T> {
    int size();

    boolean isFull(); 
    boolean isEmpty();

    T fpeek$raw(); 
    T fpeek$opt(); 
    T fpeek$exn() throws MyDequeEmptyExn;
    T rpeek$raw(); 
    T rpeek$opt(); 
    T rpeek$exn() throws MyDequeEmptyExn; 

    T fdeque$raw(); 
    T fdeque$opt(); 
    T fdeque$exn() throws MyDequeEmptyExn; 
    T rdeque$raw(); 
    T rdeque$opt(); 
    T rdeque$exn() throws MyDequeEmptyExn;

    void fenque$raw(T itm);
    void fenque$exn(T itm) throws MyDequeFullExn; 
    boolean fenque$opt(T itm); 
    void renque$raw(T itm); 
    void renque$exn(T itm) throws MyDequeFullExn; 
    boolean renque$opt(T itm);

    void System$out$print();

    void foritm(Consumer<? super T> work);
    void iforitm(BiConsumer<Integer, ? super T> work);

    void rforitm(Consumer<? super T> work);
    void irforitm(BiConsumer<Integer, ? super T> work);
}
