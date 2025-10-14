import Library.FnList.*;
    
public class FnListTest {
//
    static int abs(int x) {
	return (x >= 0 ? x : -x);
    }
//
    public static Integer tally(int n) {
	return FnListSUtil.folditm
	    (FnListSUtil.int1$make(n), 0, (r, i) -> r + (i+1));
    }
    public static Integer factorial(int n) {
	return FnListSUtil.folditm
	    (FnListSUtil.int1$make(n), 1, (r, i) -> r * (i+1));
    }
//
    public static void main(String[] args) {
	FnList<Integer> xs =
	    FnListSUtil.int1$make(10);
	System.out.println
	    ("U0.length(xs) = " + xs.U0.length(xs));
	xs.System$out$print(); System.out.println();
	xs = FnListSUtil.map_list(xs, (x0) -> 10-x0);
	xs.System$out$print(); System.out.println();
	xs = FnListSUtil.map_list(xs, (x0) -> x0 * x0);
	xs.System$out$print(); System.out.println();
	xs = FnListSUtil.imap_list(xs, (i0, x0) -> 10 * x0 + i0);
	xs.System$out$print(); System.out.println();
//
	xs = FnListSUtil.insertSort(xs);
	xs.System$out$print(); System.out.println();
	xs = xs.insertSort((x1, x2) -> x2.compareTo(x1));
	xs.System$out$print(); System.out.println();
//
	xs = FnListSUtil.rand$int$make(10);
	xs = FnListSUtil.map_list(xs, (x0) -> x0 % 100);
	xs.System$out$print(); System.out.println();
	xs = FnListSUtil.insertSort(xs);
	xs.System$out$print(); System.out.println();
	xs = xs.mergeSort((x1, x2) -> x2.compareTo(x1));
	System.out.print("By mergeSort: "); xs.System$out$print(); System.out.println();
//
	xs = FnListSUtil.rand$int$make(10);
	xs = FnListSUtil.map_list(xs, (x0) -> x0 % 100);
	xs.System$out$print(); System.out.println();
	xs = xs.quickSort((x1, x2) -> x2.compareTo(x1));
	System.out.print("By quickSort: "); xs.System$out$print(); System.out.println();
//
	xs = FnListSUtil.int1$make(10);
	xs.System$out$print(); System.out.println();
	xs = xs.mergeSort((x1, x2) -> x1 % 2 - x2 % 2);
	System.out.print("By mergeSort(stable): "); xs.System$out$print(); System.out.println();
	xs = FnListSUtil.int1$make(10);
	xs.System$out$print(); System.out.println();
	xs = xs.quickSort((x1, x2) -> x1 % 2 - x2 % 2);
	System.out.print("By quickSort(unstable): "); xs.System$out$print(); System.out.println();
//
/*
    xs = FnListSUtil.rand$int$make(1000000);
	System.out.println("U0.length(xs) = " + xs.U0.length(xs));
	xs = xs.mergeSort((x1, x2) -> x1.compareTo(x2));
	System.out.println("U0.length(xs) = " + xs.U0.length(xs));
	System.out.println("By mergeSort: xs.U0.orderedq(xs) = " + xs.SU.orderedq(xs));
*/
//
	xs = FnListSUtil.rand$int$make(1000000);
	System.out.println("U0.length(xs) = " + xs.U0.length(xs));
	xs = xs.quickSort((x1, x2) -> x1.compareTo(x2));
	System.out.println("U0.length(xs) = " + xs.U0.length(xs));
	System.out.println("By quickSort: xs.U0.orderedq(xs) = " + xs.SU.orderedq(xs));
//
	System.out.println("tally(10) = " + tally(10).toString());
	System.out.println("factorial(10) = " + factorial(10).toString());
//
    }
//
} // end of [public class FnListTest{...}]
