import Library.LnList.*;

public class Quiz01_04 {
    public static <T extends Comparable<T>> LnList<T> InsertSort(LnList<T> xs) {
        if (xs.nilq1() || xs.tl1().nilq1()) {
            return xs;
        }
        
        LnList<T> sorted = LnListSUtil.nil();
        LnList<T> current = xs;
        
        while (current.consq1()) {
            LnList<T> node = current;
            current = current.tl1();
            
            // Insert node into sorted list
            sorted = insertNode(sorted, node);
        }
        
        return sorted;
    }
    
    private static <T extends Comparable<T>> LnList<T> insertNode(LnList<T> sorted, LnList<T> node) {
        // Detach the node
        node.unlink1();
        
        if (sorted.nilq1()) {
            return node;
        }
        
        T nodeValue = node.hd1();
        
        // If node should be first
        if (nodeValue.compareTo(sorted.hd1()) <= 0) {
            node.link1(sorted);
            return node;
        }
        
        // Find insertion point
        LnList<T> prev = sorted;
        LnList<T> curr = sorted.tl1();
        
        while (curr.consq1()) {
            if (nodeValue.compareTo(curr.hd1()) <= 0) {
                break;
            }
            prev = curr;
            curr = curr.tl1();
        }
        
        // Insert node between prev and curr
        LnList<T> rest = prev.unlink1();
        prev.link1(node);
        node.link1(rest);
        
        return sorted;
    }
    
    public static void main(String[] args) {
        System.out.println("InsertSort testing\n");
        
        System.out.println("Test 1: Empty list");
        LnList<Integer> empty = LnListSUtil.nil();
        LnList<Integer> result1 = InsertSort(empty);
        System.out.print("Input:  ");
        empty.System$out$print1();
        System.out.print("\nOutput: ");
        result1.System$out$print1();
        System.out.println("\n");
        
        System.out.println("Test 2: Single element");
        LnList<Integer> single = LnListSUtil.cons(42, LnListSUtil.nil());
        LnList<Integer> result2 = InsertSort(single);
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
        LnList<Integer> result3 = InsertSort(sorted);
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
        LnList<Integer> result4 = InsertSort(reverse);
        System.out.print("Input:  ");
        reverse.System$out$print1();
        System.out.print("\nOutput: ");
        result4.System$out$print1();
        System.out.println("\n");
        
        System.out.println("Test 5: Random order with duplicates");
        LnList<Integer> random = LnListSUtil.cons(3, 
            LnListSUtil.cons(1, 
            LnListSUtil.cons(3, 
            LnListSUtil.cons(1, 
            LnListSUtil.cons(3, LnListSUtil.nil())))));
        LnList<Integer> result5 = InsertSort(random);
        System.out.print("Input:  ");
        random.System$out$print1();
        System.out.print("\nOutput: ");
        result5.System$out$print1();
        System.out.println("\n");
        
        System.out.println("All tests complete");
    }
}