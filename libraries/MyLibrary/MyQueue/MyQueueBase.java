package MyLibrary.MyQueue;

import MyLibrary.FnList.*;
import MyLibrary.MyRefer.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public abstract class MyQueueBase<T> implements MyQueue<T> {

    public boolean isEmpty() {
        return (size() <= 0);
    }

    public T top$opt() {
        return (isEmpty() ? null : top$raw());
    }

    public T top$exn() throws MyQueueEmptyExn {
        T top = top$opt();
        if (top != null) return top;
        else throw new MyQueueEmptyExn();
    }

    public boolean enque$opt(T itm) {
        if (isFull()) {
            return false;
        } else {
            enque$raw(itm);
            return true;
        }
    }

    public void enque$exn(T itm) throws MyQueueFullExn {
        boolean res = enque$opt(itm);
        if (!res) throw new MyQueueFullExn();
        else return;
    }

    public void System$out$print() {
        System.out.print("MyQueue(");
        this.iforitm ( 
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
            itm -> itms.set$raw(new FnList<T>(itm, itms.get$raw()))
        );
        (itms.get$raw()).foritm(work);
    }

    public void irforitm(BiConsumer<Integer, ? super T> work) {
        final MyRefer<FnList<T>> itms = new MyRefer<FnList<T>>(new FnList<T>());
        foritm( 
            itm -> itms.set$raw(new FnList<T>(itm, itms.get$raw()))
        );
        (itms.get$raw()).iforitm(work);
    } 
}
