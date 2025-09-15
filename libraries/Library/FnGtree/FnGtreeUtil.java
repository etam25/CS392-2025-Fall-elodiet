import java.util.function.Consumer;

public class FnGtreeUtil {
//
    public static<T>
	void BFirstSearch
	(FnGtree<T> root, Consumer<? super T> action) {
	FnGtree<T> node;
	MyQueueList<FnGtree<T>> queue = new MyQueueList<FnGtree<T>>();
	queue.enque$exn(root);
	while (true) {
	    if (queue.isEmpty()) break;
	    node = queue.deque$raw();
	    action.accept(node.value());
	    node.children().foritm((tx) -> queue.enque$exn(tx));
	}
	return;
    }
    public static<T>
	void DFirstSearch
	(FnGtree<T> root, Consumer<? super T> action) {
	FnGtree<T> node;
	MyStackList<FnGtree<T>> stack = new MyStackList<FnGtree<T>>();
	stack.push$exn(root);
	while (true) {
	    if (stack.isEmpty()) break;
	    node = stack.pop$raw();
	    action.accept(node.value());
	    node.children().rforitm((tx) -> stack.push$exn(tx));
	}
	return;
    }
//
}
