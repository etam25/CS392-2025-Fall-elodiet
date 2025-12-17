/*
// HX: 50 points for Final_02
// HX: This one tests your quicksort and mergesort
// In Final_01, pg2701_word$strmize() is implemented
// that lists all the words in pg2701.txt. Here, you
// are asked to generate FnList of pairs; each pair consists
// of a word (FnList<Character>) and a count (Integer) such that
// the count is the number of occurrences of the word in pg2701.txt.
// Note that a lower case letter is considered the same as its
// corresponding upper case. For instance, "Whale" and "whale"
// are considered the same word.
*/

import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;
import java.util.function.ToIntBiFunction;
import java.util.ArrayList;

public class Final_02 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize2() {
	// HX-2025-12-15:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Turn this stream into an array A1 of words (FnList<Character>[])
	// 3. Call the quicksort (arrayQuickSort) done in Assign06_03 to sort A1
	// 4. Use sorted A1 to generate a list L2 of word-count pairs
	// 5. Use the mergesort (mergeSort) in Assign05_01 to sort L2 using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 6. The sorted L2 is the return value of pg2701_word$count$listize2()
	
	// Step 1: Get stream of words
	LnStrm<FnList<Character>> wordStrm = Final_01.pg2701_word$strmize();
	
	// Step 2: Convert stream to array
	ArrayList<FnList<Character>> wordList = new ArrayList<>();
	LnStcn<FnList<Character>> cons = wordStrm.eval0();
	while (cons.consq()) {
	    wordList.add(cons.hd());
	    cons = cons.tl().eval0();
	}
	
	@SuppressWarnings("unchecked")
	FnList<Character>[] A1 = wordList.toArray(new FnList[wordList.size()]);
	
	// Step 3: Sort array using quicksort from Assign06_03
	ToIntBiFunction<FnList<Character>, FnList<Character>> wordCmp = 
	    (w1, w2) -> compareWords(w1, w2);
	arrayQuickSort(A1, wordCmp);
	
	// Step 4: Generate list of word-count pairs from sorted array
	FnList<FnTupl2<FnList<Character>, Integer>> L2 = FnListSUtil.nil();
	
	if (A1.length > 0) {
	    FnList<Character> currentWord = A1[0];
	    int count = 1;
	    
	    for (int i = 1; i < A1.length; i++) {
		if (compareWords(currentWord, A1[i]) == 0) {
		    count++;
		} else {
		    // Add pair for previous word
		    FnTupl2<FnList<Character>, Integer> pair = 
			new FnTupl2<>(currentWord, count);
		    L2 = FnListSUtil.cons(pair, L2);
		    
		    // Start counting new word
		    currentWord = A1[i];
		    count = 1;
		}
	    }
	    
	    // Add the last word
	    FnTupl2<FnList<Character>, Integer> pair = 
		new FnTupl2<>(currentWord, count);
	    L2 = FnListSUtil.cons(pair, L2);
	}
	
	// Step 5: Sort L2 using mergesort from Assign05_01
	ToIntBiFunction<FnTupl2<FnList<Character>, Integer>, 
			FnTupl2<FnList<Character>, Integer>> pairCmp = 
	    (p1, p2) -> comparePairs(p1, p2);
	
	FnList<FnTupl2<FnList<Character>, Integer>> sortedL2 = 
	    mergeSort(L2, pairCmp);
	
	// Step 6: Return sorted list
	return sortedL2;
    }
    
    // Helper function to compare two words (FnList<Character>)
    private static int compareWords(FnList<Character> w1, FnList<Character> w2) {
	FnList<Character> w1Copy = w1;
	FnList<Character> w2Copy = w2;
	
	while (!w1Copy.nilq() && !w2Copy.nilq()) {
	    char c1 = w1Copy.hd();
	    char c2 = w2Copy.hd();
	    if (c1 < c2) return -1;
	    if (c1 > c2) return 1;
	    w1Copy = w1Copy.tl();
	    w2Copy = w2Copy.tl();
	}
	
	if (w1Copy.nilq() && w2Copy.nilq()) return 0;
	if (w1Copy.nilq()) return -1;
	return 1;
    }
    
    // Helper function to compare pairs: (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
    private static int comparePairs(
	FnTupl2<FnList<Character>, Integer> p1,
	FnTupl2<FnList<Character>, Integer> p2) {
	
	int n1 = p1.sub1;
	int n2 = p2.sub1;
	
	// First compare by count (descending order: higher count comes first)
	if (n1 > n2) return -1;
	if (n1 < n2) return 1;
	
	// If counts are equal, compare by word (ascending order)
	return compareWords(p1.sub0, p2.sub0);
    }
    
    // Helper to convert word to string for printing
    private static String wordToString(FnList<Character> word) {
	StringBuilder sb = new StringBuilder();
	word.foritm(ch -> sb.append(ch));
	return sb.toString();
    }
    
    // Quicksort implementation from Assign06_03
    private static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T, T> cmp) {
	if (A == null || A.length <= 1) {
	    return;
	}
	quickSortHelper(A, 0, A.length - 1, cmp);
    }

    private static <T> void quickSortHelper(T[] A, int lo, int hi, ToIntBiFunction<T, T> cmp) {
	if (lo >= hi) {
	    return;
	}

	int[] bounds = partition3Way(A, lo, hi, cmp);
	int lt = bounds[0];
	int gt = bounds[1];

	quickSortHelper(A, lo, lt - 1, cmp);
	quickSortHelper(A, gt + 1, hi, cmp);
    }

    private static <T> int[] partition3Way(T[] A, int lo, int hi, ToIntBiFunction<T, T> cmp) {
	int mid = lo + (hi - lo) / 2;
	medianOfThree(A, lo, mid, hi, cmp);
	T pivot = A[lo];

	int lt = lo; 
	int i = lo + 1;
	int gt = hi;

	while (i <= gt) {
	    int cmpResult = cmp.applyAsInt(A[i], pivot);

	    if (cmpResult < 0) {
		swap(A, lt, i);
		lt++;
		i++;
	    } else if (cmpResult > 0) {
		swap(A, i, gt);
		gt--;
	    } else {
		i++;
	    }
	}

	return new int[]{lt, gt};
    }

    private static <T> void medianOfThree(T[] A, int lo, int mid, int hi, ToIntBiFunction<T, T> cmp) {
	if (cmp.applyAsInt(A[mid], A[lo]) < 0) {
	    swap(A, lo, mid);
	}
	if (cmp.applyAsInt(A[hi], A[lo]) < 0) {
	    swap(A, lo, hi);
	}
	if (cmp.applyAsInt(A[hi], A[mid]) < 0) {
	    swap(A, mid, hi);
	}
	swap(A, lo, mid);
    }

    private static <T> void swap(T[] A, int i, int j) {
	T temp = A[i];
	A[i] = A[j];
	A[j] = temp;
    }
    
    // Mergesort implementation from Assign05_01
    public static<T> FnList<T> mergeSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// Base case: empty or single element
	if (xs == null || xs.nilq() || xs.tl().nilq()) {
	    return xs;
	}

	// Split list into two halves
	Split<T> halves = splitAlternate(xs);
	
	// Recursively sort both halves
	FnList<T> left = mergeSort(halves.a, cmp);
	FnList<T> right = mergeSort(halves.b, cmp);
	
	// Merge the sorted halves
	return merge(left, right, cmp);
    }

    // Helper class to hold two lists
    private static final class Split<T> {
	final FnList<T> a, b;
	Split(FnList<T> a, FnList<T> b) { 
	    this.a = a; 
	    this.b = b; 
	}
    }

    // Helper to cons
    private static <T> FnList<T> cons(T x, FnList<T> xs) {
	return new FnList<>(x, xs);
    }

    // Helper to reverse a list
    private static <T> FnList<T> reverse(FnList<T> xs) {
	FnList<T> acc = new FnList<>();
	while (!xs.nilq()) {
	    acc = cons(xs.hd(), acc);
	    xs = xs.tl();
	}
	return acc;
    }

    // Split list by alternating elements
    private static <T> Split<T> splitAlternate(FnList<T> xs) {
	FnList<T> aRev = new FnList<>();
	FnList<T> bRev = new FnList<>();
	boolean toA = true;
	
	while (!xs.nilq()) {
	    if (toA) {
		aRev = cons(xs.hd(), aRev);
	    } else {
		bRev = cons(xs.hd(), bRev);
	    }
	    toA = !toA;
	    xs = xs.tl();
	}
	
	return new Split<>(reverse(aRev), reverse(bRev));
    }

    // Merge two sorted lists
    private static <T> FnList<T> merge(FnList<T> xs, FnList<T> ys, ToIntBiFunction<T, T> cmp) { 
	FnList<T> outRev = new FnList<>();
	FnList<T> a = xs;
	FnList<T> b = ys;

	// Merge while both lists have elements
	while (!a.nilq() && !b.nilq()) {
	    T ah = a.hd();
	    T bh = b.hd();
	    
	    if (cmp.applyAsInt(ah, bh) <= 0) {
		outRev = cons(ah, outRev);
		a = a.tl();
	    } else {
		outRev = cons(bh, outRev);
		b = b.tl();
	    }
	}
	
	// Append remaining elements
	while (!a.nilq()) { 
	    outRev = cons(a.hd(), outRev); 
	    a = a.tl(); 
	}
	while (!b.nilq()) { 
	    outRev = cons(b.hd(), outRev); 
	    b = b.tl(); 
	}
	
	return reverse(outRev);
    }
    
    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize2()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
	
	System.out.println("Computing word counts using quicksort and mergesort...");
	System.out.println();
	
	FnList<FnTupl2<FnList<Character>, Integer>> wordCounts = 
	    pg2701_word$count$listize2();
	
	System.out.println("First 100 word-count pairs:");
	System.out.println("============================");
	
	int i = 0;
	FnList<FnTupl2<FnList<Character>, Integer>> xs = wordCounts;
	
	while (i < 100 && !xs.nilq()) {
	    FnTupl2<FnList<Character>, Integer> pair = xs.hd();
	    String word = wordToString(pair.sub0);
	    int count = pair.sub1;
	    
	    System.out.println(word + " : " + count);
	    
	    xs = xs.tl();
	    i++;
	}
	
	return /*void*/;
    }
}
