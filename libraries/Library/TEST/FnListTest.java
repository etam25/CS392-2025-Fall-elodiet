public class FnListTest {
//
    public static Integer tally(int n) {
	return FnListUtil.folditm
	    (FnListUtil.list_make_int1(n), 0, (r, i) -> r + (i+1));
    }
    public static Integer factorial(int n) {
	return FnListUtil.folditm
	    (FnListUtil.list_make_int1(n), 1, (r, i) -> r * (i+1));
    }
//
    public static void main(String[] args) {
	FnList<Integer> xs =
	    FnListUtil.list_make_int1(10);
	xs.System$out$print(); System.out.println();
	xs = FnListUtil.map_list(xs, (x0) -> x0 * x0);
	xs.System$out$print(); System.out.println();
	xs = FnListUtil.imap_list(xs, (i0, x0) -> 10 * x0 + i0);
	xs.System$out$print(); System.out.println();
	System.out.println("tally(10) = " + tally(10).toString());
	System.out.println("factorial(10) = " + factorial(10).toString());
    }
//
} // end of [public class FnListTest{...}]
