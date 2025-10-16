package FnTuple;

public class FnTup3<T0, T1, T2> {
    T0 sub0;
    T1 sub1;
    T2 sub2;
    
    public FnTup3(T0 x0, T1 x1, T2 x2) {
        sub0 = x0; sub1 = x1; sub2 = x2;
    }

    public void SystemOutPrint() {
        FnTup3Util.SystemOutPrint(this);
    }
    public String toString() {
        return "FnTup3(" + sub0.toString() + "," + sub1.toString() + "," + sub2.toString() + ")";
    }
}
