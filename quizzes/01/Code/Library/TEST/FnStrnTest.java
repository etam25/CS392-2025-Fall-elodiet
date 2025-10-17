import Library.FnStrn.*;
    
public class FnStrnTest {
//
    public static
	void main(String[] args) {
	FnStrn strn1 = new FnStrn("Hello");
	FnStrn strn2 = new FnStrn(", world!");
	FnStrn strn3 = strn1.append(strn2);
	System.out.print("strn1 = ");
	strn1.System$out$print(); System.out.println();
	System.out.print("strn2 = ");
	strn2.System$out$print(); System.out.println();
	System.out.print("strn3 = ");
	strn3.System$out$print(); System.out.println();
	System.out.print("strn3(sorted) = ");
	strn3.mergeSort().System$out$print(); System.out.println();
    }
//
} // end of [public class FnStrnTest{...}]
