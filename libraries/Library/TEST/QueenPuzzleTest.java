import Library.FnList.*;
import Library.FnGtree.*;

public class QueenPuzzleTest {
//
    static int nsol = 0;
    static final int N = QueenBoard.N;
//
    public static void
	row$print(int c0) {
	for (int c1 = 0; c1 < N; c1 += 1)
	{
	    System.out.print(c0 != c1 ? ". " : "Q ");
	}
	System.out.println();
    }
    public static void
	board$print(FnList<Integer> cs) {
	FnListSUtil.rforitm(cs, (col) -> row$print(col));
	System.out.println();
    }
//
    public static void main(String[] args) {
	QueenBoard bd0 =
	    new QueenBoard(new FnList<Integer>());
	FnGtreeSUtil.DFirstSearch(
	     bd0,
	     (cs) -> {
		 if (cs.length() == N) {
		     nsol += 1;
		     System.out.println("Solution(" + nsol + "):");
		     board$print(cs);
		 }
	     }
        );
    }
//
} // end of [public class QueenPuzzleTest{...}]
