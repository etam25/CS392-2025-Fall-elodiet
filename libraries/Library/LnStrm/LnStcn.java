//
package Library.LnStrm;
//
//
// HX-2025-10-23:
// typedef LStrm<T> = Supplier<LStcn<T>>
//
public class LnStcn<T> {
    public final T head;
    public final LnStrm<T> tail;

    public LnStcn() {
	head = null; tail = null;
    }
    public LnStcn
	(T hd, LnStrm<T> tl) {
	this.head = hd; this.tail = tl;
    }
    public boolean nilq() {
	return (head == null);
    }
    public boolean consq() {
	return (head != null);
    }
} // end of [class LnStcn<T>{...}]
