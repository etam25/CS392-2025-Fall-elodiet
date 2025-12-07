package Library.FnGtree;

import Library.FnList.*;

public interface FnGtree<T> {
    T value();
    int priority();
    FnList<FnGtree<T>> children();
}
