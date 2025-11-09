import Library.FnList.*;
import java.util.function.Consumer;
//
// HX: 30 points
// This one tests your understanding of higher-order
// methods. Trying to construct a consumer of consumers
// (of the type Consumer<Consumer<Character>>) can help
// you understand the meaning of this one.
//
public class Quiz01_02 {
    public static
	FnList<Character>
	thirdOrderFun
	(Consumer<Consumer<Character>> ffcs) {
	// HX: Given a consumer of consumers of characters,
	// thirdOrderFun returns a string cs.
	// Given fcs = (ch) -> System.out.print(ch),
	// which is of the type Consumer<Character>,
	// ffcs.accept(fcs) and cs.foritm(fcs) should behave
	// the same.
    }
    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for thirdOrderFun.
    }
}
