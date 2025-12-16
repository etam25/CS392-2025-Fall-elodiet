package MyLibrary.MyPQueue;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyPQueueArray<T> extends MyPQueueBase<T> {

	@Override
	public void enque$raw(T element) {
        return;
	}

	@Override
	public T deque$raw() {
		return null;
	}

	@Override
	public T top$raw() {
		return null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}
}
