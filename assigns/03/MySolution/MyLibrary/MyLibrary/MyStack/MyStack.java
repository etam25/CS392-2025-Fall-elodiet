package MyLibrary.MyLibrary.MyStack;

import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

interface MyStack<T> {
    int size();

    boolean isFull();
    boolean isEmpty();
    T topraw();
}
