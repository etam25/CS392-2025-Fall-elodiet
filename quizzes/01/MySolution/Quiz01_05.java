package MySolution;

import Library.LnList.*;

public class Quiz01_05 {
	public static <T extends Comparable<T>> LnList<T> QuickSort(LnList<T> xs) {
		if (xs.nilq1()) {
			return xs.SU.nil();
		}

		if (xs.tl1().nilq1()) {
			return xs;
		}

		T pivot = xs.hd1();
		LnList<T> rest = xs.tl1();

		PartitionResult<T> partitioned = partition(rest, pivot);

		LnList<T> sortedLeft = QuickSort(partitioned.less);
		LnList<T> sortedRight = QuickSort(partitioned.greater);

		LnList<T> result = sortedLeft;
		result.append1(buildListFromElements(xs, pivot, partitioned.equal));
		result.append1(sortedRight);

		return result;
	}

	private static class PartitionResult<T> {
		LnList<T> less;
		LnList<T> equal;
		LnList<T> greater;

		PartitionResult(LnList<T> less, LnList<T> equal, LnList<T> greater) {
			this.less = less;
			this.equal = equal;
			this.greater = greater;
		}
	}

	private static <T extends Comparable<T>> PartitionResult<T> partition(LnList<T> xs, T pivot) {
		LnList<T> less = xs.SU.nil();
		LnList<T> equal = xs.SU.nil();
		LnList<T> greater = xs.SU.nil();

		xs.foritm1((elem) -> {
			int cmp = elem.compareTo(pivot);
			if (cmp < 0) {
				appendToEnd(less, elem);
			} else if (cmp == 0) {
				appendToEnd(equal, elem);
			} else {
				appendToEnd(greater, elem);
			}
		});

		return new PartitionResult<T>(less, equal, greater);
	}

	private static <T> void appendToEnd(LnList<T> list, T elem) {
		LnList<T> singleElem = list.SU.cons(elem, list.SU.nil());
		list.append1(singleElem);
	}

	private static <T> LnList<T> buildListFromElements(LnList<T> xs, T first, LnList<T> rest) {
		return xs.SU.cons(first, rest);
	}

	public static void main(String[] args) {
		System.out.println("QuickSort Testing\n");
		
		// Create a helper instance to access SU
		LnList<Integer> helper = LnListSUtil.nil();
		
		// Test 1: Empty list
		System.out.println("Test 1: Empty list");
		LnList<Integer> empty = helper.SU.nil();
		LnList<Integer> result1 = QuickSort(empty);
		System.out.print("Input:  ");
		empty.System$out$print1();
		System.out.print("\nOutput: ");
		result1.System$out$print1();
		System.out.println("\n");
		
		// Test 2: Single element
		System.out.println("Test 2: Single element");
		LnList<Integer> single = helper.SU.cons(42, helper.SU.nil());
		LnList<Integer> result2 = QuickSort(single);
		System.out.print("Input:  ");
		single.System$out$print1();
		System.out.print("\nOutput: ");
		result2.System$out$print1();
		System.out.println("\n");
		
		// Test 3: Already sorted
		System.out.println("Test 3: Already sorted");
		LnList<Integer> sorted = helper.SU.cons(1, 
								 helper.SU.cons(2, 
								 helper.SU.cons(3, 
								 helper.SU.cons(4, helper.SU.nil()))));
		LnList<Integer> result3 = QuickSort(sorted);
		System.out.print("Input:  ");
		sorted.System$out$print1();
		System.out.print("\nOutput: ");
		result3.System$out$print1();
		System.out.println("\n");
		
		// Test 4: Reverse sorted
		System.out.println("Test 4: Reverse sorted");
		LnList<Integer> reverse = helper.SU.cons(5, 
								  helper.SU.cons(4, 
								  helper.SU.cons(3, 
								  helper.SU.cons(2, 
								  helper.SU.cons(1, helper.SU.nil())))));
		LnList<Integer> result4 = QuickSort(reverse);
		System.out.print("Input:  ");
		reverse.System$out$print1();
		System.out.print("\nOutput: ");
		result4.System$out$print1();
		System.out.println("\n");
		
		// Test 5: Random order with duplicates
		System.out.println("Test 5: Random order with duplicates");
		LnList<Integer> random = helper.SU.cons(3, 
								 helper.SU.cons(1, 
								 helper.SU.cons(4, 
								 helper.SU.cons(1, 
								 helper.SU.cons(5, 
								 helper.SU.cons(9, 
								 helper.SU.cons(2, helper.SU.nil())))))));
		LnList<Integer> result5 = QuickSort(random);
		System.out.print("Input:  ");
		random.System$out$print1();
		System.out.print("\nOutput: ");
		result5.System$out$print1();
		System.out.println("\n");
		
		System.out.println("All tests complete");
		return;
	}
}