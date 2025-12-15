package MyLibrary.BinSearch;

import java.util.Arrays;

public class  BinSearch<T extends Comparable<T>> {
    public BinSearch() { }

    public int indexOf(T[] A, T key) {
        int lo = 0;
        int hi = A.length - 1;

    while (lo <= hi) {
        final int mid = lo + (hi - lo) / 2;
    final int sgn = key.compareTo(A[mid]);
        if      (sgn < 0) hi = mid - 1;
        else if (sgn > 0) lo = mid + 1;
        else return mid;
    }
    return -1;
    }
}
