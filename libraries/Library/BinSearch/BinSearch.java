import java.util.Arrays;

public class BinSearch<T extends Comparable<T>> {
    public BinSearch() { }

    public int indexOf(T[] A, T key) {
        int lo = 0;
        int hi = A.length - 1;
	// HX: Do search in A[lo..hi]
	while (lo <= hi) {
            // Key is in A[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
	    final int sgn = key.compareTo(A[mid]);
            if      (sgn < 0) hi = mid - 1;
            else if (sgn > 0) lo = mid + 1;
            else return mid;
        }
	return -1;
    }
}
