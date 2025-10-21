package MyLibrary.FnList;

import MyLibrary.Functions.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

import MyLibrary.FnGseq.*;

public class FnList<T> {
    private Node root;

    public final FnListSUtil SU = new FnListSUtil();
    public final FnListUtil U0 = new FnListUtil();

    private class Node {
        T head;
        FnList<T> tail;
        Node(T x0, FnList<T> xs) {
            head = x0;
            tail = xs;
        }
    }

    public FnList() {
        root = null;
    }

    public FnList(T x0, FnList<T> xs) {
        root = new Node(x0, xs);
    }

    public boolean nilq() {
        return (root == null);
    }

    public boolean consq() {
        return (root != null);
    }

    public FnList<T> reverse() {
        return FnListSUtil.reverse(this);
    }

    public T head() {
        return root.head;
    }
    
    public FnList<T> tail() {
        return root.tail;
    }
    
    public void setTail(FnList<T> newTail) {
        if (root != null) {
            root.tail = newTail;
        }
    }

    public int length() {
        int res = 0;
        FnList<T> xs = this;
        while (true) {
            if (xs.nilq()) break;
            res += 1;
            xs = xs.tail();
        }
        return res;
    }

    public Fcns<T> toArray() {
        return new Fcns(this);
    }

    public Fcns<T> toReversedArray() {
        return new Fcns<>(FnListSUtil.reverse(this));
    }
    
    public FnList<T> rappend(FnList<T> ys) {
        return FnListSUtil.rappend(this, ys);
    }
    
    public void SystemOutPrint() {
        System.out.print("FnList(");
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
        FnList<T> xs = this;
        while (true) {
            if (xs.nilq()) break;
            work.accept(xs.head()); 
            xs = xs.tail(); 
        }
    }

    public void rforitm(Consumer<? super T> work) {
        FnList<T> xs = this.reverse();
        while (true) {
            if (xs.nilq()) break;
            work.accept(xs.head()); 
            xs = xs.tail(); 
        }
    }

    public void iforitm(BiConsumer<Integer, ? super T> work) {
        int i0 = 0;
        FnList<T> xs = this;
        while (true) {
            if (xs.nilq()) break;
            work.accept(i0, xs.head()); 
            i0 += 1;
            xs = xs.tail(); 
        }
    }

    public void irforitm(BiConsumer<Integer, ? super T> work) {
        int i0 = 0; 
        FnList<T> xs = this.reverse();
        while (true) {
            if (xs.nilq()) break;
            work.accept(i0, xs.head()); 
            i0 += 1; 
            xs = xs.tail(); 
        }
    }

    public boolean forall(Predicate<? super T> pred) {
        FnList<T> xs = this;
        while (true) {
            if (xs.nilq()) break;
            if (!pred.test(xs.head())) return false; 
            xs = xs.tail(); 
        }
        return true;
    }

    public boolean rforall(Predicate<? super T> pred) {
        FnList<T> xs = this.reverse();
        while (true) {
            if (xs.nilq()) break;
            if (!pred.test(xs.head())) return false; 
            xs = xs.tail(); 
        }
        return true;
    }
    
    public boolean iforall(BiPredicate<Integer, ? super T> pred) {
        int i0 = 0;
        FnList<T> xs = this;
        while (true) {
            if (xs.nilq()) break;
            if (!pred.test(i0, xs.head())) return false; 
            i0 += 1;
            xs = xs.tail();
        }
        return true;
    }
    
    public boolean irforall(BiPredicate<Integer, ? super T> pred) {
        int i0 = 0;
        FnList<T> xs = this.reverse();
        while (true) {
            if (xs.nilq()) break;
            if (!pred.test(i0, xs.head())) return false;
            i0 += 1;
            xs = xs.tail(); 
        }
        return true;
    }

    /*
    public FnList<T> Mergesort(ToIntBiFunction<T, T> cmp) { 
        return FnListUtil.Mergesort(this, cmp);
    }

    public FnList<T> Quicksort(ToIntBiFunction<T, T> cmp) { 
        return FnListUtil.Quicksort(this, cmp);
    }

    public FnList<T> insertSort(ToIntBiFunction<T, T> cmp) { 
        return FnListUtil.insertSort(this, cmp);
    }
    */
}