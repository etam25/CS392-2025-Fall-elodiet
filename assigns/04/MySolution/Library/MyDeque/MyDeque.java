package Library.MyDeque;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

interface MyDeque<T> {
//
    int size();
//
    boolean isFull(); // checks for fullness
    boolean isEmpty(); // checks for emptiness
//
    T fpeek$raw(); // defined if !isEmpty()
    T fpeek$opt(); // defined if !isEmpty() // T is optional
    T fpeek$exn() throws MyDequeEmptyExn; // defined if !isEmpty() 
    T rpeek$raw(); // defined if !isEmpty()
    T rpeek$opt(); // defined if !isEmpty() // T is optional
    T rpeek$exn() throws MyDequeEmptyExn; // defined if !isEmpty() 
//
    T fdeque$raw(); // defined if !isEmpty()
    T fdeque$opt(); // defined if !isEmpty() // T is optional
    T fdeque$exn() throws MyDequeEmptyExn; // defined if !isEmpty() 
    T rdeque$raw(); // defined if !isEmpty()
    T rdeque$opt(); // defined if !isEmpty() // T is optional
    T rdeque$exn() throws MyDequeEmptyExn; // defined if !isEmpty() 
//
    void fenque$raw(T itm); // defined if !isFull()
    void fenque$exn(T itm) throws MyDequeFullExn; // defined if !isFull()
    boolean fenque$opt(T itm); // defined if !isFull() // true/false: succ/fail
    void renque$raw(T itm); // defined if !isFull()
    void renque$exn(T itm) throws MyDequeFullExn; // defined if !isFull()
    boolean renque$opt(T itm); // defined if !isFull() // true/false: succ/fail
//
    void System$out$print();
//
    void foritm(Consumer<? super T> work);
    void iforitm(BiConsumer<Integer, ? super T> work);
//
    void rforitm(Consumer<? super T> work);
    void irforitm(BiConsumer<Integer, ? super T> work);
//
} // end of [interface MyDeque<T>{...}]
