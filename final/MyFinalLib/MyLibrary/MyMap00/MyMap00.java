package MyLibrary.MyMap00;

import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.FnTuple.*;

import java.util.function.BiConsumer;

public interface MyMap00<K, V> {
    int size();

    boolean isFull();
    boolean isEmpty();

    LnStrm<FnTupl2<K, FnList<V>>> strmize();
    
    FnList<V> search$raw(K key); 
    FnList<V> search$exn(K key); 
    FnList<V> search$opt(K key); 

    void insert$raw(K key, V val); 
    void insert$exn(K key, V val); 
    boolean insert$opt(K key, V val);

    FnList<V> remove$raw(K key); 
    FnList<V> remove$exn(K key); 
    FnList<V> remove$opt(K key); 

    void foritm(BiConsumer<? super K, ? super V> work);
}

