package MyLibrary.MyLibrary.LnGseq;

import MyLibrary.LnList.*;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public abstract class LnGseq<XS, X0> {
    static private class LnGseqExn extends RuntimeException {}

    static private class TrueExn extends RuntimeException {}
    
    static private class FalseExn extends RuntimeException {}

    static private class Count {
        int cnt = 0;
        Count() {
            cnt = 0;
        }
        int getInc() {
            int res = cnt;
            cnt = res + 1;
            return res;
        }
    }

    public LnList<X0> listsize0(XS xs) {
        throw new LnGseqExn();
    }

    public LnList<X0> rlistsize0(XS xs) {
        throw new LnGseqExn();
    }

    public void foritm1(XS xs, Consumer<? super X0> work) {
        throw new LnGseqExn();
    }

    public void iforitm(XS xs, BiConsumer<Integer, ? super X0> work) {
        Count xcnt = new Count();
        foritm1( 
            xs, (X0, x0) -> work.accept(xcnt.getInc(), x0)
        );
        return;
    }
}
