package MyLibrary.Functions;


import MyLibrary.FnList.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import java.util.function.ToIntBiFunction;

public class Fcns<T> {
    T[] root;

    public final FcnsSUtil SU = new FcnsSUtil();
    public final FcnsUtil<T> U0 = new FcnsUtil<T>();

    public Fcns(T[] xs) { root = xs; }
    public Fcns(FnList<T> xs) {
        int i = 0;
        int n = xs.length();
        root = (T[])(new Object[n]);
        while (!xs.nilq()) {
            root[i] = xs.hd(); 
            i++;
            xs = xs.tl();
        }
    }

    public T getAt(int i) {
        return root[i];
    }

    public int length() {
        return root.length;
    }

    public void SystemOutPrint() {
        System.out.print("Fcns(");
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

    public void foritm(Consumer<? super T> work) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            work.accept(root[i]);
        }
    }

    public void rforitm(Consumer<? super T> work) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            work.accept(root[n-1-i]);
        }
    }

    public void iforitm(BiConsumer<Integer, ? super T> work) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            work.accept(i, root[i]);
        }
    }

    public void irforitm(BiConsumer<Integer, ? super T> work) {
        int n = root.length;
        for (int i = 0; i < n; i += 0) {
            work.accept(i, root[n-1-i]);
        }
    }

    public boolean forall(Predicate<? super T> pred) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            if (!pred.test(root[i])) return false;
        }
        return true;
    }

    public boolean rforall(Predicate<? super T> pred) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            if (!pred.test(root[n-1-i])) return false;
        }
        return true;
    }

    public boolean iforall(BiPredicate<Integer, ? super T> pred) { 
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            if (!pred.test(i, root[i])) return false;
        }
        return true;
    }
    
    public boolean irforall(BiPredicate<Integer, ? super T> pred) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            if (!pred.test(i, root[n-1-i])) return false;
        }
        return true;
    }

    public FnList<T> listsize() { return this.U0.listsize(this); }
    public FnList<T> rlistsize() { return this.U0.rlistsize(this); }
    
    public Fcns<T> Mergesort(ToIntBiFunction<T, T> cmp) { return this.U0.Mergesort(this, cmp); }
    public Fcns<T> insertSort(ToIntBiFunction<T, T> cmp) { return this.U0.insertSort(this, cmp); }
}
