import Library.FnInt1.*;
import Library.FnList.*;
    
public class FnInt1Test {
//
    public static
	void main(String[] args) {
	FnInt1 int10 = new FnInt1(10);
	System.out.print("int10 = ");
	int10.System$out$print(); System.out.println();
	FnList<Integer>
	    int10_sorted = int10.U0.mergeSort_list(int10, (i1, i2) -> i2 - i1);
	System.out.print("int10_sorted = ");
	int10_sorted.System$out$print(); System.out.println();
    }
//
} // end of [public class FnInt1Test{...}]
