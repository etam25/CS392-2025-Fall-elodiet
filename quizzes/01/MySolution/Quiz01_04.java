package MySolution;

import Library.LnList.*;

public class Quiz01_04 {
    public static <T extends Comparable<T>> LnList<T> InsertSort(LnList<T> xs) {
        if (xs.nilq1()) {
            return new LnList<T>();
        }
        LnList<T> sorted = new LnList<T>(xs.hd1(), new LnList<T>());
        LnList<T> remaining = xs.tl1();
        while (!remaining.nilq1()) {
            T current = remaining.hd1();
            
            sorted = insertIntoSorted(sorted, current);

            remaining = remaining.tl1();
        }

        return sorted;
    }

    private static <T extends Comparable<T>> LnList<T> insertIntoSorted(LnList<T> sorted, T elem) {
        if (sorted.nilq1()) {
            return new LnList<T>(elem, new LnList<T>());
        }

        T head = sorted.hd1();
        int cmp = elem.compareTo(head);

        if (cmp < 0) {
            return new LnList<T>(elem, sorted);
        }

        LnList<T> tail = sorted.tl1();
        LnList<T> newTail = insertIntoSorted(tail, elem);

        return new LnList<T>(head, newTail);
    }

    public static void main(String[] args) {
        System.out.println("InsertSort testing\n");

        System.out.println("Test 1: Empty list");
        LnList<Integer> empty = new LnList<Integer>();
        LnList<Integer> result1 = InsertSort(empty);
        System.out.print("Input:  ");
        empty.System$out$print1();
        System.out.print("\nOutput: ");
        result1.System$out$print1();
        System.out.println("\n");

        System.out.println("Test 2 : Single element");
        LnList<Integer> single = new LnList<Integer>(42, new LnList<Integer>());
        LnList<Integer> result2 = InsertSort(single);
        System.out.print("Input:  ");
        single.System$out$print1();
        System.out.print("\nOutput: ");
        result2.System$out$print1();
        System.out.println("\n");

        System.out.println("Test 3: Already sorted");
        LnList<Integer> sorted = new LnList<Integer>(1, 
                                 new LnList<Integer>(2, 
                                 new LnList<Integer>(3, 
                                 new LnList<Integer>(4, new LnList<Integer>()))));
        LnList<Integer> result3 = InsertSort(sorted);
        System.out.print("Input:  ");
        sorted.System$out$print1();
        System.out.print("\nOutput: ");
        result3.System$out$print1();
        System.out.println("\n");

        System.out.println("Test 4: Reverse sorted");
        LnList<Integer> reverse = new LnList<Integer>(5, 
                                  new LnList<Integer>(4, 
                                  new LnList<Integer>(3, 
                                  new LnList<Integer>(2, 
                                  new LnList<Integer>(1, new LnList<Integer>())))));
        LnList<Integer> result4 = InsertSort(reverse);
        System.out.print("Input:  ");
        reverse.System$out$print1();
        System.out.print("\nOutput: ");
        result4.System$out$print1();
        System.out.println("\n");

        System.out.println("Test 5: Random order with duplicates");
        LnList<Integer> random = new LnList<Integer>(3, 
                                 new LnList<Integer>(1, 
                                 new LnList<Integer>(3, 
                                 new LnList<Integer>(1, 
                                 new LnList<Integer>(3, new LnList<Integer>())))));
        LnList<Integer> result5 = InsertSort(random);
        System.out.print("Input:  ");
        random.System$out$print1();
        System.out.print("\nOutput: ");
        result5.System$out$print1();
        System.out.println("\n");
        
        System.out.println("All tests complete");
        return;
    }
}