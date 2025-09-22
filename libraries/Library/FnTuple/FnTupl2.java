package Library.FnTuple;

public class FnTupl2<T0,T1> {
    T0 sub0;
    T1 sub1;
    public
    FnTupl2(T0 x0, T1 x1) {
	sub0 = x0; sub1 = x1;
    }

    public void System$out$print() {
	FnTupl2Util.System$out$print(this);
    }
    public String toString() {
	return "FnTupl2(" + sub0.toString() + "," + sub1.toString() + ")";
    }
} // end of [public class FnTupl2<T0,T1>{...}]
