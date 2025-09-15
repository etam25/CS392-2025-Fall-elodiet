public abstract class MyQueueBase<T> implements MyQueue<T> {
//
    public
    boolean isEmpty()
    {
	return (size() <= 0);
    }
//
    public T top$opt() {
	return (isEmpty() ? null : top$raw());
    }
    public T top$exn() throws MyQueueEmptyExn {
	T top = top$opt();
	if (top != null) return top; else throw new MyQueueEmptyExn();
    }
//
    public T deque$opt() {
	return (isEmpty() ? null : deque$raw());
    }
    public T deque$exn() throws MyQueueEmptyExn {
	T deque = deque$opt();
	if (deque != null) return deque; else throw new MyQueueEmptyExn();
    }
//
    public boolean enque$opt(T itm) {
	if (isFull()) {
	    return false;
	} else {
	    enque$raw(itm); return true;
	}
    }

    public void enque$exn(T itm) throws MyQueueFullExn {
        boolean res = enque$opt(itm);
	if (!res) throw new MyQueueFullExn(); else return;
    }
//
}
