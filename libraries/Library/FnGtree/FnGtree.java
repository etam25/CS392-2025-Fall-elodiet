public interface FnGtree<T> {
    T value();
    FnList<FnGtree<T>> children();
}
