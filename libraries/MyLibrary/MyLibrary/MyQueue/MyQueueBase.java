package MyLibrary.MyQueue;

import MyLibrary.FnList.*;
import MyLibrary.MyRefer.*;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public abstract class MyQueueBase<T> implements MyQueue<T> {
    public boolean isEmpty() {
        return (size() <= 0);
    }

    public T topopt() {
        return (isEmpty() ? null : topraw());
    }
    public T topexn() throws MyQueueEmptyExn {
        T top = topopt();
        if (top != null) return top;
        else throw new MyQueueEmptyExn();
    }
    public T dequeopt() {
        return (isEmpty() ? null : dequeraw());
    }
    public T dequeexn() throws MyQueueEmptyExn {
        T deque = dequeopt();
        if (deque != null) return deque;
        else throw new MyQueueEmptyExn();
    }
    public boolean enqueopt(T itm) {
        if (isFull()) {
            return false;
        } else {
            enqueraw(itm);
            return true;
        }
    }

    public void enqueexn(T itm) throws MyQueueFullExn {
        boolean res = enqueopt(itm);
        if (!res) throw new MyQueueFullExn();
        else return;
    }
    public void SystemOutPrint() {
        System.out.print("MyQueue(");
        this.iforitm( 
            (i, itm) -> {
                if (i > 0) { 
                    System.out.print(",");
                }
                System.out.print(itm.toString());
            }
        );
        System.out.print(")");
    }

    public void rforitm(Consumer<? super T> work) {
        final MyRefer<FnList<T>> itms = new MyRefer<FnList<T>>(new FnList<T>());
        foritm( 
            itm -> itms.setRaw(new FnList<T>(itm, itms.getRaw()))
        );
        (itms.getRaw()).iforitm(work);
    }
}