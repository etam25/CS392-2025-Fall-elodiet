package MyLibrary.MyLibrary.MyQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyQueueArray<T> extends MyQueueBase<T> {
    int nitm = -1;
    int first = -1;
    int last = -1;
    T[] itms = null;

    public MyQueueArray(int cap) {
        assert (cap>=2);
        nitm = 0;
        first = 0;
        last = 0;
        itms = (T[]) new Object[cap];
    }

    @Override public int size() { 
        return nitm;
    }

    @Override public boolean isFull() { 
        return (nitm >= itms.length);
    }

    @Override public T topraw() {
        return itms[first];
    }

    @Override public T dequeraw() {
        T itm = itms[first];
        nitm -= 1;

        first = (first + 1) % itms.length;
        return itm;
    }

    @Override public void enqueraw(T itm) {
        itms[last] = itm;
        nitm += 1;
        last = (last + 1) % itms.length;
        return;
    }

    @Override public void foritm(Consumer<? super T> work) {
        int m = nitm - 1;
        int n = itms.length;
        for (int i = 0; i < nitm; i += 1) {
            work.accept(itms[(first+i)%n]);
        }
        return;
    }

    @Override public void iforitm(BiConsumer<Integer, ? super T> work) {
        int m = nitm - 1;
        int n = itms.length;
        for (int i = 0; i < nitm; i += 1) {
            work.accept(i, itms[(first+i)%n]);
        }
        return;
    }

    @Override public void rforitm(Consumer<? super T> work) {
        int m = nitm - 1;
        int n = itms.length;
        for (int i = 0; i < nitm; i += 1) {
            work.accept(itms[(last-1-i)%n]);
        }
        return;
    }

    @Override public void irforitm(BiConsumer<Integer, ? super T> work) {
        int m = nitm - 1;
        int n = itms.length;
        for (int i = 0; i < nitm; i += 1) {
            work.accept(i, itms[(last-1-i)%n]);
        }
        return;
    }


}

