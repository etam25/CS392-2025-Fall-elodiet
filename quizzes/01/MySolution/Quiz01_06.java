package MySolution;

import Library.FnList.*;
import java.util.function.ToIntBiFunction;

abstract public class Quiz01_06 {
	public static<T> FnList<T> someSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
        return FnListSUtil.<T>quickSort(xs, cmp);
    }

	public static <T extends Comparable<T>> FnList<T> someStableSort(FnList<T> xs, ToIntBiFunction<T, T> cmp) {
		IndexedPair<T>[] indexed = createIndexedArray(xs);

		FnList<IndexedPair<T>> indexedList = arrayToFnList(indexed);
		FnList<IndexedPair<T>> sortedIndexed = someSort(indexedList, (IndexedPair<T> pair1, IndexedPair<T> pair2) -> {
			int elemCmp = cmp.applyAsInt(pair1.elem, pair2.elem);

			if (elemCmp == 0) {
				return Integer.compare(pair1.index, pair2.index);
			}
			return elemCmp;
		});
		return extractElements(sortedIndexed);
	}

	private static class IndexedPair<T> implements Comparable<IndexedPair<T>> {
		int index;
		T elem;

		IndexedPair(int idx, T e) {
			index = idx;
			this.elem = e;
		}

		@Override 
		public int compareTo(IndexedPair<T> other) {
			return Integer.compare(this.index, other.index);
		}
	}

	private static<T> IndexedPair<T>[] createIndexedArray(FnList<T> xs) {
		int len = xs.length();
		IndexedPair<T>[] result = new IndexedPair[len];
		int[] idx = {0};

		xs.foritm((elem) -> {
			result[idx[0]] = new IndexedPair<>(idx[0], elem);
			idx[0]++;
		});
		return result;
	}
	private static <T> FnList<IndexedPair<T>> arrayToFnList(IndexedPair<T>[] arr) {
		FnList<IndexedPair<T>> result = new FnList<>();
		for (int i = arr.length - 1; i >= 0; i--) {
			result = new FnList<>(arr[i], result);
		}
		return result;
	}

	private static <T> FnList<T> extractElements(FnList<IndexedPair<T>> indexedList) {
        FnList<T>[] resultHolder = new FnList[1];
        resultHolder[0] = new FnList<>();
        indexedList.rforitm((pair) -> {
            resultHolder[0] = new FnList<T>(pair.elem, resultHolder[0]);
        });
        return resultHolder[0];
    }

	public static void main(String[] args) {
        System.out.println("Testing someStableSort with Parity Sort\n");
        
        // Test 1: Small example
        System.out.println("Test 1: Small parity sort [0,1,2,3,4,5]");
        FnList<Integer> small = buildList(new int[]{0, 1, 2, 3, 4, 5});
		FnList<Integer> smallResult = someStableSort(small, (Integer a, Integer b) -> {
            int parityA = a % 2;
            int parityB = b % 2;
            if (parityA != parityB) {
                return Integer.compare(parityA, parityB);
            }
            return Integer.compare(a, b);
        });
        System.out.print("Result: ");
        printList(smallResult);
        System.out.println("Expected: 0,2,4,1,3,5\n");
        
        // Test 2: Large 1M integers with parity sort
        System.out.println("Test 2: Parity sort on 1M integers");
        long startTime = System.currentTimeMillis();
        
        // Build list [0, 1, 2, ..., 999999]
        int[] largeArray = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            largeArray[i] = i;
        }
        FnList<Integer> largeList = buildList(largeArray);
        
        System.out.println("Built list with 1M elements");
        
        FnList<Integer> largeResult = someStableSort(largeList, (a, b) -> {
            int parityA = a % 2;
            int parityB = b % 2;
            if (parityA != parityB) {
                return Integer.compare(parityA, parityB);
            }
            return Integer.compare(a, b);
        });
        
        long endTime = System.currentTimeMillis();
        System.out.println("Sorting completed in " + (endTime - startTime) + "ms");
        
        boolean correct = verifyParitySort(largeResult);
        System.out.println("Parity sort correct: " + correct);
        
        System.out.print("First 10 elements: ");
        printFirstN(largeResult, 10);
        System.out.print("Last 10 elements: ");
        printLastN(largeResult, 10);
        System.out.println("\nTest complete");
    }
    
    private static FnList<Integer> buildList(int[] arr) {
        FnList<Integer> result = new FnList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            result = new FnList<>(arr[i], result);
        }
        return result;
    }
    
    private static <T> void printList(FnList<T> xs) {
        System.out.print("[");
        int[] count = {0};
        xs.foritm((elem) -> {
            if (count[0] > 0) System.out.print(",");
            if (count[0] < 20) {
                System.out.print(elem);
            } else if (count[0] == 20) {
                System.out.print("...");
            }
            count[0]++;
        });
        System.out.println("]");
    }
    
    private static void printFirstN(FnList<Integer> xs, int n) {
        System.out.print("[");
        int[] count = {0};
        xs.foritm((elem) -> {
            if (count[0] >= n) return;
            if (count[0] > 0) System.out.print(",");
            System.out.print(elem);
            count[0]++;
        });
        System.out.println("]");
    }

    private static void printLastN(FnList<Integer> xs, int n) {
        int len = xs.length();
        System.out.print("[");
        int[] count = {0};
        xs.foritm((elem) -> {
            int pos = count[0];
            if (pos >= len - n) {
                if (pos > len - n) System.out.print(",");
                System.out.print(elem);
            }
            count[0]++;
        });
        System.out.println("]");
    }
 
    private static boolean verifyParitySort(FnList<Integer> xs) {
        int[] lastEven = {-1};
        int[] firstOdd = {-1};
        int[] lastOdd = {-1};
        boolean[] valid = {true};
        
        xs.foritm((elem) -> {
            if (elem % 2 == 0) {
                if (firstOdd[0] != -1) {
                    valid[0] = false;
                }
                if (lastEven[0] != -1 && elem < lastEven[0]) {
                    valid[0] = false;
                }
                lastEven[0] = elem;
            } else {
                if (firstOdd[0] == -1) {
                    firstOdd[0] = elem;
                }
                if (lastOdd[0] != -1 && elem < lastOdd[0]) {
                    valid[0] = false;
                }
                lastOdd[0] = elem;
            }
        });
        
        return valid[0];
    }

}