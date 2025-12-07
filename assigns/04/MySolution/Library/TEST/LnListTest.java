import Library.FnList.*;
import Library.LnList.*;
    
public class LnListTest {
//
    public static void main(String[] args) {
	FnList<Integer> xs =
	    FnListSUtil.int1$make(10);
	System.out.println
	    ("U0.length(xs) = " + xs.U0.length(xs));
	xs.System$out$print(); System.out.println();
	LnList<Integer> ys = new LnList<Integer>(xs);
	ys.System$out$print1(); System.out.println();
	ys.reverse1();
	ys.System$out$print1(); System.out.println();
	ys.append1(new LnList<Integer>(xs));
	ys.System$out$print1(); System.out.println();
    }
//
} // end of [public class LnListTest{...}]
