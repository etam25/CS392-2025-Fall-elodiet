package FnInt;

import FnGseq.*;
import FnList.*;
import Functions.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnIntSUtil {

    public static void foritm (FnInt xs, Consumer<? super Integer> work) {
        xs.foritm(work);
        return;
    }

    public static void rforitm(FnInt xs, Consumer<? super Integer> work) {
        xs.rforitm(work);
        return;
    }
    
}
