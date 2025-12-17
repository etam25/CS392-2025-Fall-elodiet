/*
// HX: 50 points for Final_04
// HX: This one tests your RBST implementation done in Quiz02_06.
// In Final_02, pg2701_word$count$listize1() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_04, you are asked to implement the same functionality
// with a different approach.
*/

import MyLibrary.FnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnStrm.*;
import java.util.Random;
import java.util.function.ToIntBiFunction;

public class Final_04 {
    
    // Generic RBST-based map for counting word occurrences
    private static class RBSTMap {
        Node root = null;
        private Random rand = new Random();
        
        private class Node {
            FnList<Character> key;
            Integer value;
            int size;
            Node parent;
            Node lchild;
            Node rchild;
        }
        
        private int getSize(Node x) {
            return (x == null) ? 0 : x.size;
        }
        
        private void updateSize(Node x) {
            if (x != null) {
                x.size = 1 + getSize(x.lchild) + getSize(x.rchild);
            }
        }
        
        private int compareKeys(FnList<Character> k1, FnList<Character> k2) {
            FnList<Character> w1 = k1;
            FnList<Character> w2 = k2;
            
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
        
        private void rotateLeft(Node x) {
            if (x == null || x.rchild == null) return;
            
            Node y = x.rchild;
            x.rchild = y.lchild;
            if (y.lchild != null) {
                y.lchild.parent = x;
            }
            
            y.parent = x.parent;
            if (x.parent == null) {
                root = y;
            } else if (x == x.parent.lchild) {
                x.parent.lchild = y;
            } else {
                x.parent.rchild = y;
            }
            
            y.lchild = x;
            x.parent = y;
            
            updateSize(x);
            updateSize(y);
        }
        
        private void rotateRight(Node x) {
            if (x == null || x.lchild == null) return;
            
            Node y = x.lchild;
            x.lchild = y.rchild;
            if (y.rchild != null) {
                y.rchild.parent = x;
            }
            
            y.parent = x.parent;
            if (x.parent == null) {
                root = y;
            } else if (x == x.parent.lchild) {
                x.parent.lchild = y;
            } else {
                x.parent.rchild = y;
            }
            
            y.rchild = x;
            x.parent = y;
            
            updateSize(x);
            updateSize(y);
        }
        
        private Node select(Node x, int k) {
            while (x != null) {
                int leftSize = getSize(x.lchild);
                if (k == leftSize + 1) {
                    return x;
                } else if (k <= leftSize) {
                    x = x.lchild;
                } else {
                    k -= leftSize + 1;
                    x = x.rchild;
                }
            }
            return null;
        }
        
        public void reroot() {
            if (root == null) return;
            if (root.size <= 1) return;
            
            int total = root.size;
            int k = rand.nextInt(total) + 1;
            Node target = select(root, k);
            if (target == null) return;
            
            while (target.parent != null) {
                if (target == target.parent.lchild) {
                    rotateRight(target.parent);
                } else {
                    rotateLeft(target.parent);
                }
            }
            root = target;
        }
        
        // Insert or update: returns true if new key, false if existing key updated
        public boolean insertOrUpdate(FnList<Character> key, Integer value) {
            if (root == null) {
                Node n = new Node();
                n.key = key;
                n.value = value;
                n.size = 1;
                n.parent = null;
                n.lchild = null;
                n.rchild = null;
                root = n;
                return true;
            }
            
            Node curr = root;
            Node parent = null;
            
            while (curr != null) {
                parent = curr;
                int cmp = compareKeys(key, curr.key);
                if (cmp == 0) {
                    // Key exists, update value
                    curr.value = value;
                    return false;
                } else if (cmp < 0) {
                    curr = curr.lchild;
                } else {
                    curr = curr.rchild;
                }
            }
            
            // Key not found, insert new node
            Node n = new Node();
            n.key = key;
            n.value = value;
            n.size = 1;
            n.parent = parent;
            n.lchild = null;
            n.rchild = null;
            
            int cmp = compareKeys(key, parent.key);
            if (cmp < 0) {
                parent.lchild = n;
            } else {
                parent.rchild = n;
            }
            
            // Update sizes up the tree
            Node p = n.parent;
            while (p != null) {
                updateSize(p);
                p = p.parent;
            }
            
            return true;
        }
        
        // Get value for key, or null if not found
        public Integer get(FnList<Character> key) {
            Node curr = root;
            while (curr != null) {
                int cmp = compareKeys(key, curr.key);
                if (cmp == 0) {
                    return curr.value;
                } else if (cmp < 0) {
                    curr = curr.lchild;
                } else {
                    curr = curr.rchild;
                }
            }
            return null;
        }
        
        // Convert RBST to list of key-value pairs using inorder traversal
        public FnList<FnTupl2<FnList<Character>, Integer>> toList() {
            return inorderToList(root);
        }
        
        private FnList<FnTupl2<FnList<Character>, Integer>> inorderToList(Node x) {
            if (x == null) {
                return FnListSUtil.nil();
            }
            
            // Inorder: left, current, right
            FnList<FnTupl2<FnList<Character>, Integer>> left = inorderToList(x.lchild);
            FnTupl2<FnList<Character>, Integer> pair = new FnTupl2<>(x.key, x.value);
            FnList<FnTupl2<FnList<Character>, Integer>> right = inorderToList(x.rchild);
            
            // Combine: left + [pair] + right
            FnList<FnTupl2<FnList<Character>, Integer>> result = FnListSUtil.cons(pair, right);
            return FnListSUtil.append(left, result);
        }
    }
    
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize4() {
	// HX-2025-12-15:
	// Your implementation must contain the following steps:
	// 1. Call pg2701_word$strmize() to get a stream of words
	// 2. Then use the RBST implemented in Quiz02_06 to count the number of
	//    occurrences of each word in the stream of words.
	//    Note that you need to modify your Quiz02_06 implementation to turn
	//    it into an generic associative map for this part.
	// 3. Then figure out a way to turn the RBST-based map into a list WNS
	//    (FnList) of word-count pairs
	// 4. Use the mergesort (mergeSort) in Assign05_01 to sort WNS using
	//    the order (w1, n1) <= (w2, n2) if n1 > n2 or n1 = n2 and w1 <= w2
	// 5. The sorted WNS is the return value of pg2701_word$count$listize4()
	
	// Step 1: Call pg2701_word$strmize() to get a stream of words
	LnStrm<FnList<Character>> wordStrm = Final_01.pg2701_word$strmize();
	
	// Step 2: Use RBST to count occurrences of each word
	RBSTMap wordCountMap = new RBSTMap();
	
	LnStcn<FnList<Character>> cons = wordStrm.eval0();
	while (cons.consq()) {
	    FnList<Character> word = cons.hd();
	    
	    Integer currentCount = wordCountMap.get(word);
	    if (currentCount == null) {
		// Word not found, insert with count 1
		wordCountMap.insertOrUpdate(word, 1);
	    } else {
		// Word found, increment count
		wordCountMap.insertOrUpdate(word, currentCount + 1);
	    }
	    
	    cons = cons.tl().eval0();
	}
	
	// Step 3: Turn the RBST-based map into a list WNS of word-count pairs
	FnList<FnTupl2<FnList<Character>, Integer>> WNS = wordCountMap.toList();
	
	// Step 4: Use the mergesort (mergeSort) in Assign05_01 to sort WNS
	ToIntBiFunction<FnTupl2<FnList<Character>, Integer>, 
			FnTupl2<FnList<Character>, Integer>> pairCmp = 
	    (p1, p2) -> comparePairs(p1, p2);
	
	FnList<FnTupl2<FnList<Character>, Integer>> sortedWNS = 
	    mergeSort(WNS, pairCmp);
	
	// Step 5: Return the sorted WNS
	return sortedWNS;
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
    
    // Helper to convert word to string for printing
    private static String wordToString(FnList<Character> word) {
	StringBuilder sb = new StringBuilder();
	word.foritm(ch -> sb.append(ch));
	return sb.toString();
    }
    
    public static void main (String[] args) {
	// HX-2025-12-16:
	// Please write minimal testing code for pg2701_word$count$listize4()
	// In particular, please print out the first 100 word-count pairs, where
	// each line should contain only one word-count pair.
	
	System.out.println("Computing word counts using RBST...");
	System.out.println();
	
	FnList<FnTupl2<FnList<Character>, Integer>> wordCounts = 
	    pg2701_word$count$listize4();
	
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
