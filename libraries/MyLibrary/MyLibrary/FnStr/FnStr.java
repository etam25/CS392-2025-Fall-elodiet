package FnStr;

import FnList.*;
import Functions.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import java.util.function.ToIntBiFunction;

public class FnStr {
    char[] root;

    public final FnStrUtil U0 = new FnStrUtil();
    public final FnStrSUtil SU = new FnStrSUtil();

    public FnStr(char[] xs) {
        root = xs;
    }

    public FnStr(String xs) {
        int n = xs.length();
        root = new char[n];
        for (int i = 0; i < n; i += 1) {
            root[i] = xs.charAt(i);
        }
    }

    public FnStr(char ch) {
        root = new char[1];
        root[0] = ch;
    }

    public FnStr(FnList<Character> xs) {
        int n = xs.length();
        root = new char[n];
        xs.iforitm((Integer i0, Character x0) -> root[i0] = x0);
    }

    public int length() {
        return root.length;
    }
    public char getAt(int i) {
        return root[i];
    }

    public void SystemOutPrint() {
        System.out.print("FnStr(");
        this.foritm (
            (itm) -> {
                System.out.print(itm.toString());
            }
        );
        System.out.print(")");
    }
    
    public FnStr append(FnStr xs) {
        int n0 = root.length;
        int n1 = xs.length();
        char[] rs = new char[n0+n1];
        for (int i = 0; i < n0; i += 1) {
            rs[i] = root[i];
        }

        for (int i = 0; i < n1; i += 1) {
            rs[n0+i] = xs.getAt(i);
        }
        return new FnStr(rs);
    }

    public FnStr reverse() {
        int n0 = root.length;
        char[] rs = new char[n0];
        for (int i = 0; i < n0; i += 1) {
            rs[i] = root[n0-1-i];
        }

        return new FnStr(rs);
    }

    public void foritm(Consumer<? super Character> work) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            work.accept(root[i]);
        }
    }

    public void rforitm(Consumer<? super Character> work) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            work.accept(root[n-1-i]);
        }
    }

    public void iforitm(BiConsumer<Integer, ? super Character> work) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            work.accept(i, root[i]);
        }
    }

    public void irforitm(BiConsumer<Integer, ? super Character> work) {
        int n = root.length;
        for (int i = 0; i < n; i += 1) {
            work.accept(i, root[n-1-i]);
        }
    }

    public FnStr Mergesort() {
        return Mergesort((c1, c2) -> c1.compareTo(c2));
    }

    public FnStr insertSort() { 
        return insertSort((c1, c2) -> c1.compareTo(c2));
    }
    
    public FnStr Mergesort(ToIntBiFunction<Character, Character> cmp) {
        return this.U0.Mergesort(this, cmp);
    }

    public FnStr insertSort(ToIntBiFunction<Character, Character> cmp) {
        return this.U0.insertSort(this, cmp);
    }


}
