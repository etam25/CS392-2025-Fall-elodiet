package MyLibrary.FnStr;

import MyLibrary.FnGseq.*;
import MyLibrary.FnList.*;
import MyLibrary.Functions.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnStrUtil
    extends FnGseq<FnStr, Character> {
        @Override 
        public int length(FnStr xs) {
            return xs.length();
        }

        @Override public FnStr listMake(FnList<Character> xs) {
            return FnStrSUtil.listMake(xs);
        }
        @Override 
        public void foritm(FnStr xs, Consumer<? super Character> work) {
            xs.foritm(work);
            return;
        }

        @Override
        public void rforitm(FnStr xs, Consumer<? super Character> work) {
            xs.rforitm(work);
            return;
        }

        @Override
        public void iforitm(FnStr xs, BiConsumer<Integer, ? super Character> work) {
            xs.iforitm(work);
            return;
        }

        @Override 
        public void irforitm(FnStr xs, BiConsumer<Integer, ? super Character> work) {
            xs.irforitm(work);
            return;
        }
    }