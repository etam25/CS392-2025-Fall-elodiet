package FnTuple;

public class FnTup2 <T0, T1> {
    T0 sub0;
    T1 sub1;

    public FnTup2(T0 x0, T1 x1) {
        sub0 = x0; 
        sub1 = x1;
    }
    public void SystemOutPrint() {
        FnTup2Util.SystemOutPrint(this);
    }

    public String toString() {
        return "FnTup2(" + sub0.toString() + "," + sub1.toString() + ")";
    }

}
