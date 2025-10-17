package MySolution;

public class Quiz01_03 {
	private static class Pair<T> {
		T first;
		T second;
		Pair(T f, T s) { first = f; second = s;}
	}

	private static <T extends Comparable<T>> Pair<T> compareSwap(T a, T b) {
		if (a.compareTo(b) <= 0) {
			return new Pair<>(a, b);
		} else {
			return new Pair<>(b, a);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T[] sort10(T x0, T x1, T x2, T x3, T x4, T x5, T x6, T x7, T x8, T x9) {
	Pair<T> p;

	// Layer 1
	p = compareSwap(x0, x5); x0 = p.first; x5 = p.second;
	p = compareSwap(x1, x6); x1 = p.first; x6 = p.second;
	p = compareSwap(x2, x7); x2 = p.first; x7 = p.second;
	p = compareSwap(x3, x8); x3 = p.first; x8 = p.second;
	p = compareSwap(x4, x9); x4 = p.first; x9 = p.second;
	
	// Layer 2
	p = compareSwap(x0, x3); x0 = p.first; x3 = p.second;
	p = compareSwap(x1, x4); x1 = p.first; x4 = p.second;
	p = compareSwap(x5, x8); x5 = p.first; x8 = p.second;
	p = compareSwap(x6, x9); x6 = p.first; x9 = p.second;
	
	// Layer 3
	p = compareSwap(x0, x1); x0 = p.first; x1 = p.second;
	p = compareSwap(x2, x3); x2 = p.first; x3 = p.second;
	p = compareSwap(x4, x5); x4 = p.first; x5 = p.second;
	p = compareSwap(x7, x8); x7 = p.first; x8 = p.second;
	
	// Layer 4
	p = compareSwap(x1, x2); x1 = p.first; x2 = p.second;
	p = compareSwap(x3, x4); x3 = p.first; x4 = p.second;
	p = compareSwap(x5, x6); x5 = p.first; x6 = p.second;
	p = compareSwap(x8, x9); x8 = p.first; x9 = p.second;
	
	// Layer 5
	p = compareSwap(x0, x7); x0 = p.first; x7 = p.second;
	p = compareSwap(x2, x9); x2 = p.first; x9 = p.second;
	
	// Layer 6
	p = compareSwap(x0, x2); x0 = p.first; x2 = p.second;
	p = compareSwap(x7, x9); x7 = p.first; x9 = p.second;
	
	// Layer 7
	p = compareSwap(x1, x7); x1 = p.first; x7 = p.second;
	p = compareSwap(x3, x5); x3 = p.first; x5 = p.second;
	p = compareSwap(x4, x6); x4 = p.first; x6 = p.second;
	
	// Layer 8
	p = compareSwap(x1, x3); x1 = p.first; x3 = p.second;
	p = compareSwap(x2, x4); x2 = p.first; x4 = p.second;
	p = compareSwap(x5, x7); x5 = p.first; x7 = p.second;
	p = compareSwap(x6, x8); x6 = p.first; x8 = p.second;
	
	// Layer 9
	p = compareSwap(x2, x3); x2 = p.first; x3 = p.second;
	p = compareSwap(x4, x5); x4 = p.first; x5 = p.second;
	p = compareSwap(x6, x7); x6 = p.first; x7 = p.second;
	
	// Layer 10
	p = compareSwap(x3, x4); x3 = p.first; x4 = p.second;
	p = compareSwap(x5, x6); x5 = p.first; x6 = p.second;
	
	// Create and return the result array
	T[] result = (T[]) new Comparable[10];
	result[0] = x0;
	result[1] = x1;
	result[2] = x2;
	result[3] = x3;
	result[4] = x4;
	result[5] = x5;
	result[6] = x6;
	result[7] = x7;
	result[8] = x8;
	result[9] = x9;
	
	return result;
	}

	public static void main(String[] args) {
		// Test 1: Random integers
		System.out.println("Test 1: Random integers");
		Object[] result1 = sort10(5, 2, 8, 1, 9, 3, 7, 4, 6, 0);
		System.out.print(" Result: ");
		for (int i = 0; i < 10; i++) {
			System.out.print(result1[i] + (i < 9 ? ", " : "\n"));
		}

		// Test 2: Reverse sorted
		System.out.println("\nTest 2: Reverse sorted");
        Object[] result2 = sort10(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        System.out.print("  Result: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(result2[i] + (i < 9 ? ", " : "\n"));
        }

		// Test 3: Already sorted
		System.out.println("\nTest 3: Already sorted");
        Object[] result3 = sort10(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.print("  Result: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(result3[i] + (i < 9 ? ", " : "\n"));
        }

		// Test 4: Duplicates
		System.out.println("\nTest 4: With duplicates");
        Object[] result4 = sort10(5, 2, 5, 1, 8, 2, 8, 1, 5, 2);
        System.out.print("  Result: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(result4[i] + (i < 9 ? ", " : "\n"));
        }
        
		// Test 5: Strings
		System.out.println("\nTest 5: Strings");
        Object[] result5 = sort10("dog", "cat", "zebra", "ant", "fox", 
                                                   "bear", "lion", "elk", "wolf", "ape");
        System.out.print("  Result: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(result5[i] + (i < 9 ? ", " : "\n"));
        }
	}
}