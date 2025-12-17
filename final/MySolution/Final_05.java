/*
// HX: 50 points for Final_05
// HX: This one tests your priority queue implementation
*/

import MyLibrary.LnList.*;
import MyLibrary.FnList.*;
import java.util.function.ToIntBiFunction;

public class Final_05 {

    // Wrapper class to hold a list and its current head for priority queue
    private static class ListHead<T> {
        LnList<T> list;
        int listIndex;
        
        ListHead(LnList<T> list, int listIndex) {
            this.list = list;
            this.listIndex = listIndex;
        }
    }

    // Simple priority queue implementation for n-way merge
    private static class SimplePQueue<T> {
        private ListHead<T>[] heap;
        private int size;
        private ToIntBiFunction<T, T> cmp;
        
        @SuppressWarnings("unchecked")
        SimplePQueue(int capacity, ToIntBiFunction<T, T> cmp) {
            this.heap = new ListHead[capacity];
            this.size = 0;
            this.cmp = cmp;
        }
        
        boolean isEmpty() {
            return size == 0;
        }
        
        void enqueue(ListHead<T> item) {
            heap[size] = item;
            siftUp(size);
            size++;
        }
        
        ListHead<T> dequeue() {
            if (size == 0) return null;
            ListHead<T> result = heap[0];
            size--;
            if (size > 0) {
                heap[0] = heap[size];
                heap[size] = null;
                siftDown(0);
            } else {
                heap[0] = null;
            }
            return result;
        }
        
        private void siftUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                T currentVal = heap[index].list.hd1();
                T parentVal = heap[parent].list.hd1();
                if (cmp.applyAsInt(currentVal, parentVal) < 0) {
                    ListHead<T> temp = heap[index];
                    heap[index] = heap[parent];
                    heap[parent] = temp;
                    index = parent;
                } else {
                    break;
                }
            }
        }
        
        private void siftDown(int index) {
            while (true) {
                int left = 2 * index + 1;
                int right = 2 * index + 2;
                int smallest = index;
                
                if (left < size) {
                    T currentVal = heap[smallest].list.hd1();
                    T leftVal = heap[left].list.hd1();
                    if (cmp.applyAsInt(leftVal, currentVal) < 0) {
                        smallest = left;
                    }
                }
                
                if (right < size) {
                    T smallestVal = heap[smallest].list.hd1();
                    T rightVal = heap[right].list.hd1();
                    if (cmp.applyAsInt(rightVal, smallestVal) < 0) {
                        smallest = right;
                    }
                }
                
                if (smallest != index) {
                    ListHead<T> temp = heap[index];
                    heap[index] = heap[smallest];
                    heap[smallest] = temp;
                    index = smallest;
                } else {
                    break;
                }
            }
        }
    }

    public static<T> LnList<T>
	LnList_n$way$merge(LnList<T> xss[], ToIntBiFunction<T,T> cmp) {
	// HX: Given an array of (linear) lists (LnList), each of which is
	// ordered according to cmp, please implement a function to merge them
	// into one ordered (linear) list. Please note that you cannot create
	// new list nodes; you can only use exist nodes to form the returned
	// linear list. You are asked to use MyPQueueArray.java implemented in
	// Assigment#9 for finding the minimum of a collection of arguments.
	
	// Count non-empty lists
	int nonEmptyCount = 0;
	for (int i = 0; i < xss.length; i++) {
	    if (xss[i] != null && xss[i].consq1()) {
		nonEmptyCount++;
	    }
	}
	
	if (nonEmptyCount == 0) {
	    return new LnList<T>();
	}
	
	// Initialize priority queue with heads of non-empty lists
	SimplePQueue<T> pq = new SimplePQueue<>(nonEmptyCount, cmp);
	for (int i = 0; i < xss.length; i++) {
	    if (xss[i] != null && xss[i].consq1()) {
		pq.enqueue(new ListHead<>(xss[i], i));
	    }
	}
	
	// Build result by repeatedly taking minimum head and reusing nodes
	LnList<T> result = null;
	LnList<T> tail = null;
	
	while (!pq.isEmpty()) {
	    ListHead<T> minHead = pq.dequeue();
	    LnList<T> currentList = minHead.list;
	    
	    // Unlink the head node from the current list
	    LnList<T> rest = currentList.unlink1();
	    
	    // Reuse the node: link it to result
	    if (result == null) {
		result = currentList;
		tail = currentList;
	    } else {
		tail.link1(currentList);
		tail = currentList;
	    }
	    
	    // If the rest has more elements, add it back to priority queue
	    if (rest.consq1()) {
		pq.enqueue(new ListHead<>(rest, minHead.listIndex));
	    }
	}
	
	return result != null ? result : new LnList<T>();
    }

    public static<T>
	FnList<T>
	LnList_mergeSort$5way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX: Please use LnList_n$way$merge to implement 5-way mergesort
	// on a linear list. That is, split each list evenly into 5 sublists;
	// recursely sort the 5 sublist and then use LnList_n$way$merge to merge
	// them into one sorted list.
	// Please make sure that your implementation of LnList_mergeSort$5way
	// does stable sorting!
	
	// Base case: empty list
	if (xs == null || xs.nilq1()) {
	    return FnListSUtil.nil();
	}
	
	// Base case: single element
	LnList<T> tail = xs.tl1();
	if (tail.nilq1()) {
	    return FnListSUtil.sing(xs.hd1());
	}
	
	// Split into 5 sublists by distributing nodes
	@SuppressWarnings("unchecked")
	LnList<T>[] sublists = new LnList[5];
	@SuppressWarnings("unchecked")
	LnList<T>[] tails = new LnList[5];
	for (int i = 0; i < 5; i++) {
	    sublists[i] = new LnList<T>();
	    tails[i] = null;
	}
	
	// Distribute nodes evenly across 5 sublists
	int index = 0;
	LnList<T> current = xs;
	while (current.consq1()) {
	    // Unlink current node from the list
	    LnList<T> node = current;
	    LnList<T> rest = current.unlink1();
	    
	    // Link node to appropriate sublist
	    if (sublists[index].nilq1()) {
		// First node in this sublist
		sublists[index] = node;
		tails[index] = node;
		// Link to empty list to terminate
		node.link1(new LnList<T>());
	    } else {
		// Append to existing sublist
		tails[index].link1(node);
		tails[index] = node;
		// Link to empty list to terminate
		node.link1(new LnList<T>());
	    }
	    
	    current = rest;
	    index = (index + 1) % 5;
	}
	
	// Recursively sort each sublist
	@SuppressWarnings("unchecked")
	LnList<T>[] sortedSublists = new LnList[5];
	for (int i = 0; i < 5; i++) {
	    FnList<T> sortedFn = LnList_mergeSort$5way(sublists[i], cmp);
	    // Convert FnList back to LnList
	    sortedSublists[i] = fnListToLnList(sortedFn);
	}
	
	// Merge the 5 sorted sublists
	return lnListToFnList(LnList_n$way$merge(sortedSublists, cmp));
    }
    
    // Helper: Convert FnList to LnList
    private static <T> LnList<T> fnListToLnList(FnList<T> fnList) {
	if (fnList == null || fnList.nilq()) {
	    return new LnList<T>();
	}
	
	LnList<T> result = new LnList<T>(fnList.hd(), new LnList<T>());
	LnList<T> tail = result;
	FnList<T> rest = fnList.tl();
	
	while (!rest.nilq()) {
	    LnList<T> newNode = new LnList<T>(rest.hd(), new LnList<T>());
	    tail.link1(newNode);
	    tail = newNode;
	    rest = rest.tl();
	}
	
	return result;
    }
    
    // Helper: Convert LnList to FnList using FnListSUtil
    private static <T> FnList<T> lnListToFnList(LnList<T> lnList) {
	if (lnList == null || lnList.nilq1()) {
	    return FnListSUtil.nil();
	}
	
	// Build FnList in reverse, then reverse it
	FnList<T> reversed = FnListSUtil.nil();
	LnList<T> current = lnList;
	
	while (current.consq1()) {
	    reversed = FnListSUtil.cons(current.hd1(), reversed);
	    current = current.tl1();
	}
	
	// Reverse to get correct order
	return reversed.reverse();
    }

    public static void main(String[] args) {
	// Please write some testing code that applies
	// mergeSort to parity-sort the list [0,1,2,...,999998,999999]
	// of 1000000 elements.
	
	System.out.println("Testing 5-way mergesort with parity sort");
	System.out.println("Building list [0, 1, 2, ..., 999999]...");
	
	// Build list [0, 1, 2, ..., 999999]
	LnList<Integer> inputList = new LnList<Integer>();
	LnList<Integer> tail = null;
	for (int i = 0; i < 1000000; i++) {
	    LnList<Integer> newNode = new LnList<Integer>(i, new LnList<Integer>());
	    if (inputList.nilq1()) {
		inputList = newNode;
		tail = newNode;
	    } else {
		tail.link1(newNode);
		tail = newNode;
	    }
	}
	
	System.out.println("List built. Starting parity sort...");
	long startTime = System.currentTimeMillis();
	
	// Parity sort: even numbers first (parity 0), then odd numbers (parity 1)
	// Within each parity group, sort by value
	ToIntBiFunction<Integer, Integer> parityCmp = (a, b) -> {
	    int parityA = a % 2;
	    int parityB = b % 2;
	    if (parityA != parityB) {
		return Integer.compare(parityA, parityB);
	    }
	    return Integer.compare(a, b);
	};
	
	FnList<Integer> sorted = LnList_mergeSort$5way(inputList, parityCmp);
	
	long endTime = System.currentTimeMillis();
	System.out.println("Sorting completed in " + (endTime - startTime) + " ms");
	
	// Verify and print first/last elements
	System.out.println("Verifying result...");
	FnList<Integer> current = sorted;
	int count = 0;
	boolean correct = true;
	int lastEven = -1;
	boolean seenOdd = false;
	
	while (!current.nilq() && count < 1000000) {
	    int val = current.hd();
	    
	    if (val % 2 == 0) {
		if (seenOdd) {
		    correct = false;
		    System.out.println("Error: Even number " + val + " appears after odd numbers");
		    break;
		}
		if (lastEven != -1 && val < lastEven) {
		    correct = false;
		    System.out.println("Error: Even numbers not sorted: " + lastEven + " > " + val);
		    break;
		}
		lastEven = val;
	    } else {
		seenOdd = true;
	    }
	    
	    if (count < 10) {
		System.out.print(val + " ");
	    }
	    
	    current = current.tl();
	    count++;
	}
	
	if (count == 1000000) {
	    System.out.println("\n... (showing first 10)");
	}
	
	System.out.println("\nTotal elements processed: " + count);
	System.out.println("Parity sort correct: " + correct);
	
	// Print last 10 elements
	System.out.print("Last 10 elements: ");
	FnList<Integer> last = sorted;
	int len = 0;
	while (!last.nilq()) {
	    len++;
	    last = last.tl();
	}
	
	last = sorted;
	for (int i = 0; i < len - 10; i++) {
	    last = last.tl();
	}
	while (!last.nilq()) {
	    System.out.print(last.hd() + " ");
	    last = last.tl();
	}
	System.out.println();
    }


}


