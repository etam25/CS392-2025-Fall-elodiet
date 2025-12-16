package MyLibrary.FnStrn;

import MyLibrary.FnGseq.*;
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnStrnUtil extends FnGseq<FnStrn, Character> {
    @Override
    public int length(FnStrn xs) {
        return xs.length();
    }

    @Override 
    public FnStrn list$make(FnList<Character> xs) {
        return FnStrnSUtil.list$make(xs);
    }

    @Override
    public void foritm(FnStrn xs, Consumer<? super Character> work) {
        xs.foritm(work);
        return;
    }

    @Override
    public void rforitm(FnStrn xs, Consumer<? super Character> work) {
        xs.rforitm(work);
        return;
    }

    @Override
    public void iforitm(FnStrn xs, BiConsumer<Integer, ? super Character> work) {
        xs.iforitm(work);
        return;
    }

    @Override
    public void irforitm(FnStrn xs, BiConsumer<Integer, ? super Character> work) {
	xs.irforitm(work); 
    return;
    }
}
