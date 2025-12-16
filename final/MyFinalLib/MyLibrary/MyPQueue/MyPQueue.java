package MyLibrary.MyPQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

interface MyPQueue<T> {
    int size();

    boolean isFull(); 
    boolean isEmpty(); 

    T top$raw(); 
    T top$opt(); 
    T top$exn() throws MyPQueueEmptyExn; 

    T deque$raw(); 
    T deque$opt(); 
    T deque$exn() throws MyPQueueEmptyExn; 

    void enque$raw(T itm); 
    void enque$exn(T itm) throws MyPQueueFullExn; 
    boolean enque$opt(T itm);
}
