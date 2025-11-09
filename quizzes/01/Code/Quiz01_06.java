//
// HX: 50 points
//
abstract public class Quiz01_06 {
    public static<T>
	FnList<T> someSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// This one is abstract, that is, not implemented
    }
    public static<T>
	FnList<T> someStableSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// Please implement a stable sorting method based on
	// someSort, which may not be stable
    }
}

////////////////////////////////////////////////////////////////////////.
//
// HX-2025-10-15:
// Please find a way to test someStableSort by
// implementing someSort as quickSort on FnList
// and then use someStableSort to parity-sort the following
// list of 1M integers:
// 0, 1, 2, 3, 4, ..., 999999
// That is, the result of parity-sorting should be:
// 0, 2, ..., 999998, 1, 3, ..., 999999
//
// Note that you should be able to call the quickSort method
// in Library/FnList/FnListSUtil.java; should not do another
// implementation of quickSort in your testing code.
////////////////////////////////////////////////////////////////////////.
