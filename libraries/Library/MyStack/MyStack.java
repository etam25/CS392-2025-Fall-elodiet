import java.util.function.Consumer;
import java.util.function.BiConsumer;

interface MyStack<T> {
//
    int size();
//
    boolean isFull(); // checks for fullness
    boolean isEmpty(); // checks for emptiness
//
    T top$raw(); // defined if !isEmpty()
    T top$opt(); // defined if !isEmpty() // T is optional
    T top$exn() throws MyStackEmptyExn; // defined if !isEmpty() 
//
    T pop$raw(); // defined if !isEmpty()
    T pop$opt(); // defined if !isEmpty() // T is optional
    T pop$exn() throws MyStackEmptyExn; // defined if !isEmpty() 
//
    void push$raw(T itm); // defined if !isFull()
    void push$exn(T itm) throws MyStackFullExn; // defined if !isFull()
    boolean push$opt(T itm); // defined if !isFull() // true/false: succ/fail
//
    void foritm(Consumer<? super T> action);
    void iforitm(BiConsumer<Integer, ? super T> action);
//
    void System$out$print();
}
