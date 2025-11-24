package Library.MyMap00;

import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnTuple.*;

import java.util.function.BiConsumer;

/*
HX-2025-11-15:
A map consists of DISTINCT keys, and each key is
map to a list of values, where the list obeys LIFO.
*/

public interface MyMap00<K,V> {
//
    int size(); // the number of keys
//
    boolean isFull(); // checks for fullness // no room
    boolean isEmpty(); // checks for emptiness // no keys
//
    LnStrm<FnTupl2<K,FnList<V>>> strmize();
//
    FnList<V> search$raw(K key); // HX: [key] is assumed to be in the map
    FnList<V> search$exn(K key); // HX: exception if [key] is not in the map
    FnList<V> search$opt(K key); // HX: return null if [key] is not in the map
//
    void insert$raw(K key, V val); // HX: insertion is assumed to work
    void insert$exn(K key, V val); // HX: exception if no insertion is done
    boolean insert$opt(K key, V val); // HX: return false if no insertion is done
//
    FnList<V> remove$raw(K key); // HX: [key] is assumed to be in the map
    FnList<V> remove$exn(K key); // HX: exception if [key] is not in the map
    FnList<V> remove$opt(K key); // HX: return null if [key] is not in the map
//
    void foritm(BiConsumer<? super K, ? super V> work);
    // If a key mapped to multiple values, each key-value pair needs to be processed
//
} // end of [interface MyMap00<T>{...}]
