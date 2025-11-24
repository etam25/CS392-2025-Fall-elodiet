package Library.FnGtree;

import Library.FnList.*;

public interface FnGtree<T> {
    T value();
    FnList<FnGtree<T>> children();
}
