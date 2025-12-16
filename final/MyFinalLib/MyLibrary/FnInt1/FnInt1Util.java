package MyLibrary.FnInt1;

import MyLibrary.FnGseq.*;
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnInt1Util extends FnGseq<FnInt1, Integer> {

    @Override
    public int length(FnInt1 xs) {
        return xs.length();
    }

    @Override 
    public void foritm(FnInt1 xs, Consumer<? super Integer> work) {
        xs.foritm(work);
        return;
    }

    @Override 
    public void rforitm(FnInt1 xs, Consumer<? super Integer> work) {
        xs.rforitm(work);
        return; 
    }
}
