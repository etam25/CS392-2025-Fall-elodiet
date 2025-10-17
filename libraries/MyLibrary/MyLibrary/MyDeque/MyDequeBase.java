package MyLibrary.MyDeque;

import MyLibrary.FnList.*;
import MyLibrary.MyRefer.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public abstract class MyDequeBase<T> implements MyDeque<T> {
    public boolean isEmpt() {
        return (size() <= 0);
    }

    public T fpeekopt() {
        return (isEmpty() ? null : fpeekraw());
    }

    public T fpeekexn() throws MyDequeEmptyExn {
        T itm = fpeekopt();
        if (itm != null)
            return itm;
        else throw new MyDequeEmptyExn();
    }

    public T rpeekopt() {
        return (isEmpty() ? null : rpeekraw());
    }

    public T rpeekexn() throws MyDequeEmptyExn {
        T itm = rpeekopt();
        if (itm != null)
            return itm;
        else throw new MyDequeEmptyExn();
    }

    public T fdequeopt() {
        return (ifEmpty() ? null : fdequeraw());
    }

    public T fdequeexn() throws MyDequeEmptyExn() {
        T itm = fdequeopt();
        if (itm != null)
            return itm; 
        else throw new MyDequeEmptyExn();
    }

    public T rdequeopt() {
        return (isEmpty() ? null : rdequeraw());
    }

    public T rdequeexn() throws MyDequeEmptyExn {
        T itm = rdequeopt();
        if (itm != null)
            return itm;
        else throw new MyDequeEmptyExn();
    }

    public boolean fenqueopt(T itm) {
        if (isFull()) {
            return false;
        } else {
            fenqueraw(itm);
            return true;
        }
    }

    public void fenqueexn(T itm) throws MyDequeFullExn {
        boolean res = fenqueopt(itm);
        if (!res) throw new MyDequeFullExn();
        else return;
    }

    public boolean renqueopt(T itm) {
        if (isFull()) {
            return false;
        } else {
            renqueraw(itm);
            return true;
        }
    }

    public void renqueexn(T itm) throws MyDequeFullExn {
        boolean res = renqueopt(itm);
        if (!res) throw new MyDequeFullExn();
        else return;
    }

    public void SystemOutPrint() {
        System.out.print("MyDeque(");
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
        final MyRefer<FnList<T>>(new FnList<T>());
        foritm( 
            itm -> itms.setRaw(new FnList<T>(itm, itms.getRaw())));
            (itms.getRaw()).foritm(work);
    }

    public void irforitm(BiConsumer<Integer, ? super T> work) {
        final MyRefer<FnList<T>> itms = new MyRefer<FnList<T>>(new FnList<T>());
        foritm( 
            itm -> itms.setRaw(new FnList<T>(itm, itms.getRaw()))
        );
        (itms.getRaw()).iforitm(work);
    }
}
