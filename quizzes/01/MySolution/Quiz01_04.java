import Library.LnList.*;

public class Quiz01_04 {
    public static <T extends Comparable<T>> LnList<T> LnListInsertSort(LnList<T> xs) {
        // Handle empty list
        if (xs.nilq1()) {
            return xs;
        }
        
        // Handle single element list
        LnList<T> tail = xs.tl1();
        if (tail.nilq1()) {
            return xs;
        }
        
        // Start with first element as the sorted list
        LnList<T> sorted = xs;
        // Unlink to isolate first element and get the rest
        LnList<T> rest = sorted.unlink1(); // sorted now points to first element only, rest has remaining elements
        
        // Process each element from rest
        while (rest.consq1()) {
            // The current element is the first node in rest
            // We need to isolate it by unlinking its tail
            LnList<T> current = rest;
            LnList<T> afterCurrent = rest.tl1();
            
            // Isolate current by unlinking its tail
            if (afterCurrent.nilq1()) {
                // current is the last element, we need to isolate it
                // Since current is the only element in rest, we can just set rest to empty
                // But we need current isolated, so we unlink its (null) tail
                rest = current.unlink1(); // This will return an empty list since current has no tail
            } else {
                // Unlink the tail from current to isolate it
                rest = current.unlink1(); // current is now isolated, rest points to the remaining elements
            }
            
            // Insert current into sorted list
            sorted = insertNode(sorted, current);
        }
        
        return sorted;
    }
    
    private static <T extends Comparable<T>> LnList<T> insertNode(LnList<T> sorted, LnList<T> node) {
        T nodeValue = node.hd1();
        
        // If sorted is empty, return node as the sorted list
        if (sorted.nilq1()) {
            return node;
        }
        
        // If node should be inserted at the beginning
        if (nodeValue.compareTo(sorted.hd1()) <= 0) {
            node.link1(sorted);
            return node;
        }
        
        // Find the correct insertion point
        LnList<T> prev = sorted;
        LnList<T> curr = sorted.tl1();
        
        while (curr.consq1()) {
            if (nodeValue.compareTo(curr.hd1()) <= 0) {
                // Insert between prev and curr
                // Unlink curr from prev, then link node between them
                LnList<T> afterPrev = prev.unlink1(); // prev is now isolated, afterPrev points to curr
                prev.link1(node); // Link node to prev
                node.link1(afterPrev); // Link afterPrev (which contains curr and rest) to node
                return sorted;
            }
            prev = curr;
            curr = curr.tl1();
        }
        
        // Insert at the end
        prev.link1(node);
        return sorted;
    }
    
    public static int main(String[] args) {
        // Minimal testing code
        // Note: Creating test lists without constructors is difficult,
        // so we use the constructor LnList(T x0, LnList<T> xs) which is a public method
        // to build test cases
        
        // Test with a simple list [3, 1, 2]
        LnList<Integer> empty = new LnList<Integer>();
        LnList<Integer> list2 = new LnList<Integer>(2, empty);
        LnList<Integer> list1 = new LnList<Integer>(1, list2);
        LnList<Integer> testList = new LnList<Integer>(3, list1);
        
        LnList<Integer> result = LnListInsertSort(testList);
        result.System$out$print1();
        System.out.println();
        
        return 0;
    }
}