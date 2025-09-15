import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class MyStackArray<T> extends MyStackBase<T> {

    int nitm = -1;
    T[] itms = null;

    public
    MyStackArray(int cap)
    {
	assert (cap >= 1);
	nitm = 0;
	itms = (T[]) new Object[cap];
    }
    
    public int size() {
	return nitm;
    }

    public boolean isFull() {
	return (nitm >= itms.length);
    }

    public T top$raw() {
	return itms[0];
    }

    public T pop$raw() {
	nitm -= 1; return itms[nitm];
    }

    public void push$raw(T itm) {
	itms[nitm] = itm; nitm += 1; return;
    }

    public void foritm(Consumer<? super T> action) {
	int m = nitm - 1;
	for (int i = 0; i < nitm; i += 1) action.accept(itms[m-i]);
    }

    public void
	iforitm(BiConsumer<Integer, ? super T> action) {
	int m = nitm - 1;
	for (int i = 0; i < nitm; i += 1) action.accept(i, itms[m-i]);
    }
}
