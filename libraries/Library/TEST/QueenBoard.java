public class QueenBoard implements FnGtree<FnList<Integer>> {
    static final int N = 8;
    FnList<Integer> bd0;
    public
	QueenBoard(FnList<Integer> bd1) {
	bd0 = bd1;
    }
    private int abs(int i0) {
	return (i0 >= 0 ? i0 : -i0);
    }
    public
	FnList<Integer> value() {
	return bd0;
    }
    public
	FnList<FnGtree<FnList<Integer>>>
	children() {
	boolean ans;
	FnList<Integer> bd1;
	FnList<FnGtree<FnList<Integer>>>
	    res =
	    new FnList<FnGtree<FnList<Integer>>>();
	if (bd0.length() >= N) return res;
	for (int c0 = 0; c0 < N; c0 += 1) {
	    final int fc0 = c0;
	    ans = bd0.iforall((i1, c1) -> (fc0 != c1) && (i1+1 != abs(fc0 - c1)));
	    if (ans) {
		bd1 = new FnList<Integer>(fc0, bd0);
		res = new FnList<FnGtree<FnList<Integer>>>(new QueenBoard(bd1), res);
	    }
	}
	return res.reverse();
    }
}
