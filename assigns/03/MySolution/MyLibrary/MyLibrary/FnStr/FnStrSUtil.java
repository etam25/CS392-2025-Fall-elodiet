package MyLibrary.MyLibrary.FnStr;

import MyLibrary.FnGseq.*;
import MyLibrary.FnList.*;
import MyLibrary.Functions.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class FnStrSUtil {
    public static FnStr listMake(FnList<Character> xs) {
        return new FnStr(xs);
    }

    public static void foritm(FnStr xs, Consumer<? super Character> work) {
        xs.foritm(work);
        return;
    }

    public static void rforitm(FnStr xs, Consumer<? super Character> work) {
        xs.rforitm(work);
        return;
    }

    public static void iforitm(FnStr xs, BiConsumer<Integer, ? super Character> work) {
        xs.iforitm(work);
        return;
    }

    public static void irforitm(FnStr xs, BiConsumer<Integer, ? super Character> work) {
        xs.irforitm(work);
        return;
    }

    public static FnStr fworkMake(Consumer<Consumer<? super Character>> fwork) {
        return listMake(FnListSUtil.fworkMake(fwork));
    }

    public static FnStr append(FnStr xs1, FnStr xs2) { return xs2.append(xs2);}

    public static FnStr append(FnStr xs1, FnStr xs2, FnStr xs3) { 
        return fworkMake ((Consumer<? super Character> work) -> { 
            xs1.foritm(work); xs2.foritm(work); xs3.foritm(work); });
        }

        public static FnStr append(FnStr xs1, FnStr xs2, FnStr xs3, FnStr xs4) {
            return fworkMake((Consumer<? super Character> work) -> { 
                xs1.foritm(work);xs2.foritm(work);xs3.foritm(work);xs4.foritm(work);
            });
        }
    }
