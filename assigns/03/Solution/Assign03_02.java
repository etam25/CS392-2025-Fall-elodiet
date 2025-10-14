import Library.FnStrn.*;
import Library.MyStack.*;

public class Assign03_02 {
    public static
	boolean balancedq(String txt0) {
	FnStrn txt1 = new FnStrn(txt0);
	MyStackArray<Character>
	    stack = new MyStackArray<Character>(txt1.length());
	boolean result =
	    txt1.U0.forall
	    (txt1, (Character ch) -> balancedq_test(ch, stack));
	return (result && stack.isEmpty());
    }
    private static boolean
	balancedq_test(char ch, MyStackArray<Character> stack) {
	Character opt;
	// System.out.print("stack = ");
	// stack.System$out$print();System.out.println();
	if (ch == '(') {
	    stack.push$exn(ch); return true;
	}
	if (ch == '[') {
	    stack.push$exn(ch); return true;
	}
	if (ch == '{') {
	    stack.push$exn(ch); return true;
	}
	if (ch == ')') {
	    opt = stack.pop$opt();
	    return (opt != null && opt.equals('('));
	}
	if (ch == ']') {
	    opt = stack.pop$opt();
	    return (opt != null && opt.equals('['));
	}
	if (ch == '}') {
	    opt = stack.pop$opt();
	    return (opt != null && opt.equals('{'));
	}
	return false;
    }
	    
    public static void main (String[] args) {
	String txt0 = "{}";
	System.out.println("balancedq("+txt0+") = "+balancedq(txt0));
	String txt1 = "[()]{}()";
	System.out.println("balancedq("+txt1+") = "+balancedq(txt1));
	String txt2 = "[()]{[}]()";
	System.out.println("balancedq("+txt2+") = "+balancedq(txt2));
    }
}
