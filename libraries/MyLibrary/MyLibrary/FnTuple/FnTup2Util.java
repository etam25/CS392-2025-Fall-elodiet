package MyLibrary.FnTuple;

public class FnTup2Util {
    public static <T0, T1>
        void SystemOutPrint(FnTup2<T0, T1> tup) {
            System.out.print("FnTup1(");
            System.out.print(tup.sub0.toString());
            System.out.print(",");
            System.out.print(tup.sub1.toString());
            System.out.print(")");
    }

    public static <T0 extends Comparable<T0>, T1 extends Comparable<T1>>
    int compare (FnTup2<T0, T1> tup1, FnTup2<T0, T1> tup2) {
        int sgn;
        sgn = tup1.sub0.compareTo(tup2.sub0);
        if (sgn != 0) {
            return sgn;
        } else {
            return tup1.sub1.compareTo(tup2.sub1);
        }
    }
}
