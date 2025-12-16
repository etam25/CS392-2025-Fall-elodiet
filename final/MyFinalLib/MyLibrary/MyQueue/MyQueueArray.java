package MyLibrary.MyQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyQueueArray<T> extends MyQueueBase<T> {

    @Override
    public T deque$opt() {
        if (nitm == 0) {
            return null;
        }
        return deque$raw();
    }

    @Override
    public T deque$exn() {
        if (nitm == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        return deque$raw();
    }
    
    int nitm = -1;
    int frst = -1;
    int last = -1;
    T[] itms = null; 

    public MyQueueArray(int cap) {
        assert (cap >= 2);
        nitm = 0;
        frst = 0;
        last = 0;
        itms = (T[]) new Object[cap];
    }

    @Override 
    public int size() {
        return nitm;
    }

    @Override 
    public boolean isFull() {
        return (nitm >= itms.length);
    }

    @Override 
    public T top$raw() {
        return itms[frst];
    }

    @Override
    public T deque$raw() {
        T itm = itms[frst];
        nitm -= 1;

        frst = (frst + 1) % itms.length;
        return itm;
    }

    @Override
    public void enque$raw(T itm) {
        itms[last] = itm;
        nitm += 1;

        last = (last + 1) % itms.length;
        return;
    }

    @Override 
    public void foritm(Consumer<? super T> work) {
        int m = nitm - 1;
        int n = itms.length;
        for (int i = 0; i < nitm; i += 1) {
            work.accept(itms[(frst + i)%n]);
        }
        return;
    }

    @Override 
    public void iforitm(BiConsumer<Integer, ? super T> work) {
        int m = nitm - 1;
        int n = itms.length;
        for (int i = 0; i < nitm; i += 1) {
            work.accept(i, itms[(frst + i)%n]);
        }
        return;
    }

    @Override 
    public void rforitm(Consumer<? super T> work) {
        int m = nitm - 1;
        int n = itms.length;
        for (int i = 0; i < nitm; i += 1) {
            work.accept(itms[(last-1-i)%n]);
        }
        return;
    }

    @Override 
    public void irforitm(BiConsumer<Integer, ? super T> work) {
        int m = nitm - 1;
        int n = itms.length;
        for (int i = 0; i < nitm; i += 1) {
            work.accept(i, itms[(last-1-i)%n]);
        }
        return;
    }
}
