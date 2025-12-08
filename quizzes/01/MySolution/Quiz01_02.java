import Library.FnList.*;
import java.util.function.Consumer;

public class Quiz01_02 {
	public static FnList<Character> thirdOrderFn(Consumer<Consumer<Character>> ffcs) {
		final FnList<Character>[] resultHolder = new FnList[]{new FnList<Character>()};
		Consumer<Character> collector = (ch) -> { 
			resultHolder[0] = new FnList<Character>(ch, resultHolder[0]);
		};

		ffcs.accept(collector);
		return resultHolder[0].reverse();
	}

	public static void main(String[] args) {
		System.out.println("Test 1: ");
		Consumer<Consumer<Character>> test1 = (fcs) -> {
            fcs.accept('H');
            fcs.accept('e');
            fcs.accept('l');
            fcs.accept('l');
            fcs.accept('o');
        };
		FnList<Character> result1 = thirdOrderFn(test1);
		System.out.print(" Result: ");
		result1.System$out$print();
		System.out.print(" -> ");
		result1.foritm((ch) -> System.out.print(ch));
		System.out.println();

		System.out.println("\nTest2: Verify that ffcs.accept(fcs) and cs.foritm(fcs) behave the same");
		Consumer<Character> printChar = (ch) -> System.out.print(ch);
		System.out.print(" Using ffcs.accept: ");
		test1.accept(printChar);
		System.out.println();
		System.out.print(" Using cs.foritm: ");
		result1.foritm(printChar);
		System.out.println();

		System.out.println("\nTest 3: Empty sequence");
		Consumer<Consumer<Character>> test3 = (fcs) -> {
			// do nothing
		};
		FnList<Character> result3 = thirdOrderFn(test3);
		System.out.print(" Result: ");
		result3.System$out$print();
		System.out.println(" (should be empty)");

		System.out.println("\nTest4: Single character");
		Consumer<Consumer<Character>> test4 = (fcs) -> {
            fcs.accept('X');
        };
		FnList<Character> result4 = thirdOrderFn(test4);
		System.out.print(" Result: ");
		result4.System$out$print();
		System.out.print(" -> ");
		result4.foritm((ch) -> System.out.print(ch));
		System.out.println();

		System.out.println("\nTest 5: Loop-based character generation");
		Consumer<Consumer<Character>> test5 = (fcs) -> {
			for (char c = 'A'; c <= 'E'; c++) {
				fcs.accept(c);
			}
		};
		FnList<Character> result5 = thirdOrderFn(test5);
		System.out.print(" Result: ");
		result5.System$out$print();
		System.out.print(" -> ");
		result5.foritm((ch) -> System.out.print(ch));
		System.out.println();

		return;
	}
}