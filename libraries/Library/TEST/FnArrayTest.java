public class FnArrayTest {
//
    public static Integer tally(int n) {
	return FnArrayUtil.folditm
	    (FnArrayUtil.list_make_int1(n), 0, (r, i) -> r + (i+1));
    }
    public static Integer factorial(int n) {
	return FnArrayUtil.folditm
	    (FnArrayUtil.list_make_int1(n), 1, (r, i) -> r * (i+1));
    }
//
    public static void main(String[] args) {
	FnArray<Integer> xs =
	    FnArrayUtil.list_make_int1(10);
	xs.System$out$print(); System.out.println();
	xs = FnArrayUtil.map_array(xs, (x0) -> x0 * x0);
	xs.System$out$print(); System.out.println();
	xs = FnArrayUtil.imap_array(xs, (i0, x0) -> 10 * x0 + i0);
	xs.System$out$print(); System.out.println();
	System.out.println("tally(10) = " + tally(10).toString());
	System.out.println("factorial(10) = " + factorial(10).toString());
    }
//
} // end of [public class FnArrayTest{...}]
