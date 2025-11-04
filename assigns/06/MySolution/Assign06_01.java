import Library.LnStrm.*;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_01 {

    public static<T>
LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
    return new LnStrm<T>(
        () -> {
            LnStcn<LnStrm<T>> outer = fxss.eval0();
            
            // Base case: no more streams
            if (outer.nilq()) {
                return new LnStcn<T>();
            }
            
            // Evaluate the first stream
            LnStrm<T> firstStream = outer.head;
            LnStcn<T> firstCons = firstStream.eval0();
            
            // If first stream is empty, skip to next streams
            if (firstCons.nilq()) {
                return mergeLnStrm(outer.tail, cmpr).eval0();
            }
            
            // Find the minimum element among all stream heads
            T minHead = firstCons.head;
            LnStrm<T> minTail = firstCons.tail;
            LnStrm<LnStrm<T>> remainingStreams = outer.tail;
            boolean minFromFirst = true;
            
            // Check remaining streams for a smaller head
            LnStcn<LnStrm<T>> curr = outer.tail.eval0();
            LnStrm<LnStrm<T>> rebuiltTail = new LnStrm<>(() -> new LnStcn<>());
            
            // Collect non-empty streams
            java.util.List<LnStrm<T>> nonEmptyStreams = new java.util.ArrayList<>();
            nonEmptyStreams.add(firstStream);
            
            while (curr.consq()) {
                LnStcn<T> currCons = curr.head.eval0();
                if (currCons.consq()) {
                    nonEmptyStreams.add(curr.head);
                }
                curr = curr.tail.eval0();
            }
            
            // Find minimum
            int minIdx = 0;
            minHead = null;
            for (int i = 0; i < nonEmptyStreams.size(); i++) {
                LnStcn<T> cons = nonEmptyStreams.get(i).eval0();
                if (minHead == null || cmpr.applyAsInt(cons.head, minHead) < 0) {
                    minHead = cons.head;
                    minIdx = i;
                }
            }
            
            // Rebuild stream list with the min stream's tail
            final T finalMinHead = minHead;
            final int finalMinIdx = minIdx;
            
            LnStrm<LnStrm<T>> newStreams = new LnStrm<>(
                () -> {
                    LnStcn<LnStrm<T>> result = new LnStcn<>();
                    for (int i = 0; i < nonEmptyStreams.size(); i++) {
                        if (i == finalMinIdx) {
                            // Use tail of min stream
                            LnStcn<T> minCons = nonEmptyStreams.get(i).eval0();
                            result = new LnStcn<>(minCons.tail, 
                                new LnStrm<>(() -> result));
                        } else {
                            // Keep original stream
                            final LnStcn<LnStrm<T>> finalResult = result;
                            result = new LnStcn<>(nonEmptyStreams.get(i), 
                                new LnStrm<>(() -> finalResult));
                        }
                    }
                    return result;
                }
            );
            
            return new LnStcn<T>(finalMinHead, mergeLnStrm(newStreams, cmpr));
        }
    );
}
} 

