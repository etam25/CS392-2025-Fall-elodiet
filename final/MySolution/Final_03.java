/*
// HX: 50 points for Final_03
// HX: This one tests your hash map implementation
// In Final_02, pg2701_word$count$listize2() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_03, you are asked to implement the same functionality
// with a different approach.
*/
import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;
import java.util.function.ToIntBiFunction;

public class Final_03 {
    
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize3() {
	// HX-2025-12-15:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Then use the hash map implemented in Assign08_02 (open addressing)
	//    to count the number of occurrences of each word in the stream of words
	// 3. Then figure out a way to turn the hash map into a list WNS (FnList) of
	//    word-count pairs
	// 4. Use the mergesort (mergeSort) in Assign05_01 to sort WNS using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 5. The sorted WNS is the return value of pg2701_word$count$listize3()
	
	// Step 1: Call pg2701_word$strmize() to get a stream of words
        LnStrm<FnList<Character>> wordStrm = Final_01.pg2701_word$strmize();
        
        // Step 2: Use the hash map implemented in Assign08_02 to count occurrences
        // Strategy: Insert 1 for each occurrence of a word. The hash map will
        // prepend to the list if the key exists. Then we use length() to count.
        // This avoids remove/re-insert operations that can cause capacity issues.
        Assign08_02<Integer> wordCountMap = new Assign08_02<>(10000);
        
        LnStcn<FnList<Character>> cons = wordStrm.eval0();
        while (cons.consq()) {
            FnList<Character> word = cons.hd();
            String wordStr = wordToString(word);
            
            // Simply insert 1 each time we see the word
            // The hash map will handle existing keys by prepending to the list
            wordCountMap.insert$exn(wordStr, 1);
            
            cons = cons.tl().eval0();
        }
        
        // Step 3: Turn the hash map into a list WNS of word-count pairs
        FnList<FnTupl2<FnList<Character>, Integer>> WNS = FnListSUtil.nil();
        
        LnStrm<FnTupl2<String, FnList<Integer>>> mapStrm = wordCountMap.strmize();
        LnStcn<FnTupl2<String, FnList<Integer>>> mapCons = mapStrm.eval0();
        
        while (mapCons.consq()) {
            FnTupl2<String, FnList<Integer>> entry = mapCons.hd();
            String wordStr = entry.sub0;
            FnList<Integer> counts = entry.sub1;
            // Count is the length of the list (each occurrence added a 1)
            int count = counts.length(); 
            
            FnList<Character> word = stringToWord(wordStr);
            FnTupl2<FnList<Character>, Integer> pair = 
                new FnTupl2<>(word, count);
            WNS = FnListSUtil.cons(pair, WNS);
            
            mapCons = mapCons.tl().eval0();
        }
        
        // Step 4: Use the mergesort (mergeSort) in Assign05_01 to sort WNS
        FnList<FnTupl2<FnList<Character>, Integer>> sortedWNS = 
            mergeSort(WNS, (p1, p2) -> comparePairs(p1, p2));
        
        // Step 5: Return the sorted WNS
        return sortedWNS;
    }
    
    private static String wordToString(FnList<Character> word) {
        StringBuilder sb = new StringBuilder();
        word.foritm(ch -> sb.append(ch));
        return sb.toString();
    }
    
    private static FnList<Character> stringToWord(String str) {
        FnList<Character> result = FnListSUtil.nil();
        for (int i = str.length() - 1; i >= 0; i--) {
            result = FnListSUtil.cons(str.charAt(i), result);
        }
        return result;
    }
    
    private static int compareWords(FnList<Character> w1, FnList<Character> w2) {
        while (!w1.nilq() && !w2.nilq()) {
            char c1 = w1.hd();
            char c2 = w2.hd();
            if (c1 < c2) return -1;
            if (c1 > c2) return 1;
            w1 = w1.tl();
            w2 = w2.tl();
        }
        if (w1.nilq() && w2.nilq()) return 0;
        if (w1.nilq()) return -1; 
        return 1;
    }
    
    private static int comparePairs(
        FnTupl2<FnList<Character>, Integer> p1,
        FnTupl2<FnList<Character>, Integer> p2) {
        
        int n1 = p1.sub1;
        int n2 = p2.sub1;
        
        if (n1 > n2) return -1;
        if (n1 < n2) return 1;
        
        return compareWords(p1.sub0, p2.sub0);
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
    
    public static void main(String[] args) {
        System.out.println("Computing word counts using hash map...");
        System.out.println();
        
        FnList<FnTupl2<FnList<Character>, Integer>> wordCounts = 
            pg2701_word$count$listize3();
        
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
        
        System.out.println();
        System.out.println("Total unique words: " + wordCounts.length());
        
        return;
    }
}