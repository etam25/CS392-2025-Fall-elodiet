import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnGtree.*;

import java.util.function.Consumer;

public class Assign07_01 {

    public static<T> LnStrm<T>
	BFirstEnumerate(FnGtree<T> root) {
	MyDequeList<FnGtree<T>> deque = new MyDequeList<FnGtree<T>>();
    deque.renque(root);
    return BFirstEnumerate(deque);
    }

    private static<T> LnStrm<T> BFirstEnumerate_helper(MyDequeList<FnGtree<T>> deque) {
        return new LnStrm<T>(
            () -> {
                if (deque.isEmpty()) {
                    return new LnStcn<T>();
                } else {
                    FnGtree<T> node = deque.fdeque();

                    node.children().foritm((tx) -> deque.renque(tx));
                    return new LnStcn<T>(node.value(), BFirstEnumerate_helper(deque));
                }
            }
        );
    }



    public static<T> LnStrm<T>
	DFirstEnumerate(FnGtree<T> root) {
	MyDequeList<FnGtree<T>> deque = new MyDequeList<FnGtree<T>>();
    deque.fenque(root);
    return DFirstEnumerate_helper(deque);
    }

    private static<T> LnStrm<T> DFirstEnumerate_helper(MyDequeLIst<FnGtree<T>> deque) {
        return new LnStrm<T>( 
            () -> {
                if (deque.isEmpty()) {
                    return new LnStcn<T>();
                } else {
                    FnGtree<T> node = deque.fdeque();
                    node.children().rforitm((tx) -> deque.fenque(tx));
                    return new LnStcn<T>(node.value(), DFirstEnumerate_helper(deque));
                }
            }
        );
    }
} // end of [public class Assign07_01{...}]
