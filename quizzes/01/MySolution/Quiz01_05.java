import Library.LnList.*;

public class Quiz01_05 {
    public static <T extends Comparable<T>> LnList<T> QuickSort(LnList<T> xs) {
        if (xs.nilq1() || xs.tl1().nilq1()) {
            return xs;
        }
        
        // Use first element as pivot
        T pivot = xs.hd1();
        LnList<T> pivotNode = xs;
        LnList<T> rest = xs.tl1();
        pivotNode.unlink1(); // Detach pivot from rest
        
        // Partition the rest
        PartitionResult<T> partitioned = partition(rest, pivot);
        
        // Recursively sort partitions
        LnList<T> sortedLess = QuickSort(partitioned.less);
        LnList<T> sortedGreater = QuickSort(partitioned.greater);
        
        // Reconnect: sortedLess -> pivot -> equal -> sortedGreater
        LnList<T> result;
        
        // Connect pivot with equal nodes
        pivotNode.link1(partitioned.equal);
        
        // Connect less partition to pivot
        if (sortedLess.nilq1()) {
            result = pivotNode;
        } else {
            result = sortedLess;
            LnList<T> lastOfLess = findLast(sortedLess);
            lastOfLess.link1(pivotNode);
        }
        
        // Connect pivot+equal to greater partition
        LnList<T> lastOfPivotEqual = findLast(pivotNode);
        lastOfPivotEqual.link1(sortedGreater);
        
        return result;
    }
    
    private static class PartitionResult<T> {
        LnList<T> less;
        LnList<T> equal;
        LnList<T> greater;
        
        PartitionResult(LnList<T> less, LnList<T> equal, LnList<T> greater) {
            this.less = less;
            this.equal = equal;
            this.greater = greater;
        }
    }
    
    private static <T extends Comparable<T>> PartitionResult<T> partition(LnList<T> xs, T pivot) {
        LnList<T> less = LnListSUtil.nil();
        LnList<T> equal = LnListSUtil.nil();
        LnList<T> greater = LnListSUtil.nil();
        
        LnList<T> lastLess = LnListSUtil.nil();
        LnList<T> lastEqual = LnListSUtil.nil();
        LnList<T> lastGreater = LnListSUtil.nil();
        
        LnList<T> current = xs;
        
        while (current.consq1()) {
            LnList<T> node = current;
            current = current.tl1();
            node.unlink1(); // Detach node
            
            int cmp = node.hd1().compareTo(pivot);
            
            if (cmp < 0) {
                if (less.nilq1()) {
                    less = node;
                    lastLess = node;
                } else {
                    lastLess.link1(node);
                    lastLess = node;
                }
            } else if (cmp == 0) {
                if (equal.nilq1()) {
                    equal = node;
                    lastEqual = node;
                } else {
                    lastEqual.link1(node);
                    lastEqual = node;
                }
            } else {
                if (greater.nilq1()) {
                    greater = node;
                    lastGreater = node;
                } else {
                    lastGreater.link1(node);
                    lastGreater = node;
                }
            }
        }
        
        return new PartitionResult<T>(less, equal, greater);
    }
    
    private static <T> LnList<T> findLast(LnList<T> list) {
        if (list.nilq1()) {
            return list;
        }
        while (list.tl1().consq1()) {
            list = list.tl1();
        }
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println("QuickSort Testing\n");
        
        System.out.println("Test 1: Empty list");
        LnList<Integer> empty = LnListSUtil.nil();
        LnList<Integer> result1 = QuickSort(empty);
        System.out.print("Input:  ");
        empty.System$out$print1();
        System.out.print("\nOutput: ");
        result1.System$out$print1();
        System.out.println("\n");
        
        System.out.println("Test 2: Single element");
        LnList<Integer> single = LnListSUtil.cons(42, LnListSUtil.nil());
        LnList<Integer> result2 = QuickSort(single);
        System.out.print("Input:  ");
        single.System$out$print1();
        System.out.print("\nOutput: ");
        result2.System$out$print1();
        System.out.println("\n");
        
        System.out.println("Test 3: Already sorted");
        LnList<Integer> sorted = LnListSUtil.cons(1, 
            LnListSUtil.cons(2, 
            LnListSUtil.cons(3, 
            LnListSUtil.cons(4, LnListSUtil.nil()))));
        LnList<Integer> result3 = QuickSort(sorted);
        System.out.print("Input:  ");
        sorted.System$out$print1();
        System.out.print("\nOutput: ");
        result3.System$out$print1();
        System.out.println("\n");
        
        System.out.println("Test 4: Reverse sorted");
        LnList<Integer> reverse = LnListSUtil.cons(5, 
            LnListSUtil.cons(4, 
            LnListSUtil.cons(3, 
            LnListSUtil.cons(2, 
            LnListSUtil.cons(1, LnListSUtil.nil())))));
        LnList<Integer> result4 = QuickSort(reverse);
        System.out.print("Input:  ");
        reverse.System$out$print1();
        System.out.print("\nOutput: ");
        result4.System$out$print1();
        System.out.println("\n");
        
        System.out.println("Test 5: Random order with duplicates");
        LnList<Integer> random = LnListSUtil.cons(3, 
            LnListSUtil.cons(1, 
            LnListSUtil.cons(4, 
            LnListSUtil.cons(1, 
            LnListSUtil.cons(5, 
            LnListSUtil.cons(9, 
            LnListSUtil.cons(2, LnListSUtil.nil())))))));
        LnList<Integer> result5 = QuickSort(random);
        System.out.print("Input:  ");
        random.System$out$print1();
        System.out.print("\nOutput: ");
        result5.System$out$print1();
        System.out.println("\n");
        
        System.out.println("All tests complete");
    }
}