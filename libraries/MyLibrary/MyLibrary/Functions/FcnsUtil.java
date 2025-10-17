package MyLibrary.Functions;

import MyLibrary.FnList.*;

import java.util.function.Consumer;

import MyLibrary.FnGseq.*;

public class FcnsUtil<X0>
    extends FnGseq<Fcns<X0>, X0> {
        @Override 
        public int length(Fcns<X0> xs) {
            return xs.length();
        }

        @Override 
        public int length(Fcns<X0> xs) {
            return xs.length();
        }

        public void foritm(Fcns<X0> xs, Consumer<? super X0> work) {
            xs.foritm(work);
            return;
        }

        public void rforitm(Fcns<X0> xs, Consumer<? super X0> work) {
            xs.rforitm(work);
            return;
        }
    }
