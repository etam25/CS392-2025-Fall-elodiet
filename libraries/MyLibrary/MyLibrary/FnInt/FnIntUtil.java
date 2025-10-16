package FnInt;

import FnGseq.*;
import FnList.*;
import Functions.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnIntUtil
    extends FnGseq<FnInt, Integer> {
        @Override 
        public int length(FnInt xs) {
            return xs.length();
        }

        @Override 
        public void foritm(FnInt xs, Consumer<? super Integer> work) {
            xs.foritm(work);
            return;
        }

        @Override
        public void rforitm(FnInt xs, Consumer<? super Integer> work) {
            xs.rforitm(work);
            return;
        }

    }
