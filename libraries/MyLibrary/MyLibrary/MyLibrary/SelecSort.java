package MyLibrary;

public class SelecSort {
    private SelecSort() { }

    private static <T> void exchange(T[] A, int i, int j) {
        T tmp;
        tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
        return;
    }

    // left inclusive, right exclusive
    private static <T extends Comparable<T>> int indexMin(T[] A, int i, int j) {
        assert (i < j);
            if (i + 1 >= j) {
                return i;
        } else {
            int r = indexMin(A, i + 1, j);
            return less(A[i], A[r]) ? i : r;
        }
    }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
        return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> void sort(T[] A) {
        final int len = A.length;
        for (int i = 0; i < len; i++) {
            exchange(A, i, indexMin(A, i, len));
        }
    }


public static void main(String[] args) {
    
}

}
