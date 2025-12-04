package MyLibrary.MyLibrary.LnStrm;

public class LnStcn<T> {
    public final T head;
    public final LnStrm<T> tail;

    public LnStcn() {
        head = null;
        tail = null;
    }

    public LnStcn (T hd, LnStrm<T> tl) {
        this.head = hd;
        this.tail = tl;
    }

    public boolean nilq() {
        return (head == null);
    }

    public boolean consq() {
        return (head != null);
    }
}
