//
// HX: 50 points
//
import Library.LnList.*;
// Please see Library/LnList for LnList.java
public class Quiz01_05 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListQuickSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Please implement quicksort on a linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class
	
	if (xs.nilq1() || xs.tl1().nilq1()) {
	    return xs;
	}
	
	// Use first element as pivot
	T pivot = xs.hd1();
	LnList<T> pivotNode = xs;
	LnList<T> rest = pivotNode.unlink1(); // Detach pivot from rest
	
	// Partition the rest into less, equal, and greater
	// Start with empty lists - we'll build them as we process
	LnList<T> less = null;
	LnList<T> equal = null;
	LnList<T> greater = null;
	
	LnList<T> lastLess = null;
	LnList<T> lastEqual = null;
	LnList<T> lastGreater = null;
	
	LnList<T> current = rest;
	while (current.consq1()) {
	    LnList<T> node = current;
	    // Isolate node by unlinking its tail - this returns the rest of the list
	    current = node.unlink1(); // Detach node and get the rest
	    
	    int cmp = node.hd1().compareTo(pivot);
	    
	    if (cmp < 0) {
		if (less == null) {
		    less = node;
		    lastLess = node;
		} else {
		    lastLess.link1(node);
		    lastLess = node;
		}
	    } else if (cmp == 0) {
		if (equal == null) {
		    equal = node;
		    lastEqual = node;
		} else {
		    lastEqual.link1(node);
		    lastEqual = node;
		}
	    } else {
		if (greater == null) {
		    greater = node;
		    lastGreater = node;
		} else {
		    lastGreater.link1(node);
		    lastGreater = node;
		}
	    }
	}
	
	// Recursively sort partitions
	LnList<T> sortedLess;
	if (less == null) {
	    sortedLess = new LnList<T>(); // Empty list
	} else {
	    sortedLess = LnListQuickSort(less);
	}
	
	LnList<T> sortedGreater;
	if (greater == null) {
	    sortedGreater = new LnList<T>(); // Empty list
	} else {
	    sortedGreater = LnListQuickSort(greater);
	}
	
	// Reconnect: sortedLess -> pivot -> equal -> sortedGreater
	LnList<T> result;
	
	// Build the chain: pivot -> equal
	if (equal != null) {
	    pivotNode.link1(equal);
	}
	
	// Connect less partition to pivot (if less exists)
	if (sortedLess == null || sortedLess.nilq1()) {
	    result = pivotNode;
	} else {
	    result = sortedLess;
	    LnList<T> lastOfLess = sortedLess;
	    LnList<T> next = lastOfLess.tl1();
	    while (next.consq1()) {
		lastOfLess = next;
		next = next.tl1();
	    }
	    lastOfLess.link1(pivotNode);
	}
	
	// Connect greater partition to the end (if greater exists)
	if (sortedGreater != null && !sortedGreater.nilq1()) {
	    LnList<T> lastOfChain = result;
	    LnList<T> next = lastOfChain.tl1();
	    while (next.consq1()) {
		lastOfChain = next;
		next = next.tl1();
	    }
	    lastOfChain.link1(sortedGreater);
	}
	
	return result;
    }
    public static int main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for LnListQuickSort
	
	// Test with a simple list [3, 1, 2]
	LnList<Integer> empty = new LnList<Integer>();
	LnList<Integer> list2 = new LnList<Integer>(2, empty);
	LnList<Integer> list1 = new LnList<Integer>(1, list2);
	LnList<Integer> testList = new LnList<Integer>(3, list1);
	
	LnList<Integer> result = LnListQuickSort(testList);
	result.System$out$print1();
	System.out.println();
	
	return 0;
    }
}
