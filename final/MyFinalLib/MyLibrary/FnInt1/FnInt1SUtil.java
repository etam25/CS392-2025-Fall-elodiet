package MyLibrary.FnInt1;

import MyLibrary.FnGseq.*;
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnInt1SUtil {
    public static void foritm(FnInt1 xs, Consumer<? super Integer> work) {
        xs.foritm(work);
        return;
    }

    public static void rforitm(FnInt1 xs, Consumer<? super Integer> work) {
        xs.rforitm(work);
        return;
    }
}
