package MyLibrary.MyLibrary.FnInt;

import MyLibrary.FnList.*;
import MyLibrary.Functions.*;

import java.util.function.Consumer; 
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import java.util.function.ToIntBiFunction;

public class FnInt {
    int root;

    public final FnIntUtil U0 = new FnIntUtil();
    public final FnIntSUtil SU = new FnIntSUtil();

    public FnInt(int xs) {
        root = xs;
    }

    public int length() {
        return (root <= 0 ? 0 : root);
    }

    public int getAt(int i) { return i;}

    public void SystemOutPrint() {
        System.out.print("FnInt(");
        this.foritm (
            (itm) -> {
                System.out.print(itm.toString());
            }
        );
        System.out.print(")");
    }

    public void foritm(Consumer<? super Integer> work) {
        int n = root;
        for (int i = 0; i < n; i += 1) work.accept(i);
    }

    public void rforitm(Consumer<? super Integer> work) {
        int n = root;
        for (int i = 0; i < n; i += 1) work.accept(n-1-i);
    }
}
