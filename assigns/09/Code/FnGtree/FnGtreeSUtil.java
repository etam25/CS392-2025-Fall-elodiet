import Library.FnList.*;
import Library.LnStrm.*;
import Library.MyPQueue.*;
import java.util.function.Consumer;

public class FnGtreeSUtil {
    
    // HX-2025-12-02:
    // This method enumerates nodes according
    // to their priority numbers (obtained by
    // calling priority()
    public static<T> LnStrm<T>
    PFirstEnumerate(FnGtree<T> root) {
        // Create a priority queue that orders nodes by their priority
        MyPQueueArray<FnGtree<T>> pqueue = 
            new MyPQueueArray<FnGtree<T>>(1000, node -> node.priority());
        
        // Add the root to the priority queue
        pqueue.enque$raw(root);
        
        // Return a lazy stream that processes nodes by priority
        return new LnStrm<T>(() -> {
            if (pqueue.isEmpty()) {
                return new LnStcn<T>();  // Empty stream
            }
            
            // Dequeue the node with highest priority (lowest number)
            FnGtree<T> current = pqueue.deque$raw();
            
            // Add all children to the priority queue
            FnList<FnGtree<T>> children = current.children();
            while (!FnListSUtil.nilq(children)) {
                pqueue.enque$raw(children.hd());
                children = children.tl();
            }
            
            // Return current value and continue with remaining nodes
            return new LnStcn<T>(current.value(), PFirstEnumerate_helper(pqueue));
        });
    }
    
    // Helper method to continue enumeration with existing priority queue
    private static<T> LnStrm<T>
    PFirstEnumerate_helper(MyPQueueArray<FnGtree<T>> pqueue) {
        return new LnStrm<T>(() -> {
            if (pqueue.isEmpty()) {
                return new LnStcn<T>();  // Empty stream
            }
            
            // Dequeue the node with highest priority (lowest number)
            FnGtree<T> current = pqueue.deque$raw();
            
            // Add all children to the priority queue
            FnList<FnGtree<T>> children = current.children();
            while (!FnListSUtil.nilq(children)) {
                pqueue.enque$raw(children.hd());
                children = children.tl();
            }
            
            // Return current value and continue with remaining nodes
            return new LnStcn<T>(current.value(), PFirstEnumerate_helper(pqueue));
        });
    }
    
} // end of [public class FnGtreeSUtil{...}]

// Test class
class TestPFirstEnumerate {
    
    // Simple implementation of FnGtree for testing
    static class SimpleGtree<T> implements FnGtree<T> {
        private T val;
        private int pri;
        private FnList<FnGtree<T>> kids;
        
        public SimpleGtree(T value, int priority, FnList<FnGtree<T>> children) {
            this.val = value;
            this.pri = priority;
            this.kids = children;
        }
        
        public SimpleGtree(T value, int priority) {
            this(value, priority, FnListSUtil.nil());
        }
        
        @Override
        public T value() { return val; }
        
        @Override
        public int priority() { return pri; }
        
        @Override
        public FnList<FnGtree<T>> children() { return kids; }
    }
    
    public static void main(String[] args) {
        System.out.println("Testing MyPQueueArray and PFirstEnumerate\n");
        
        // Test 1: Basic priority queue operations
        System.out.println("=== Test 1: Basic Priority Queue ===");
        testBasicPQueue();
        
        // Test 2: Simple tree enumeration
        System.out.println("\n=== Test 2: Simple Tree Enumeration ===");
        testSimpleTree();
        
        // Test 3: Complex tree enumeration
        System.out.println("\n=== Test 3: Complex Tree Enumeration ===");
        testComplexTree();
    }
    
    static void testBasicPQueue() {
        MyPQueueArray<Integer> pq = new MyPQueueArray<Integer>(10, x -> x);
        
        System.out.println("Enqueueing: 5, 3, 7, 1, 9, 2");
        pq.enque$raw(5);
        pq.enque$raw(3);
        pq.enque$raw(7);
        pq.enque$raw(1);
        pq.enque$raw(9);
        pq.enque$raw(2);
        
        System.out.print("Dequeuing in priority order: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.deque$raw() + " ");
        }
        System.out.println();
    }
    
    static void testSimpleTree() {
        // Create a simple tree
        
        SimpleGtree<String> d = new SimpleGtree<>("D", 4);
        SimpleGtree<String> b = new SimpleGtree<>("B", 3, FnListSUtil.sing(d));
        SimpleGtree<String> c = new SimpleGtree<>("C", 2);
        SimpleGtree<String> a = new SimpleGtree<>("A", 1, 
            FnListSUtil.cons(b, FnListSUtil.sing(c)));
        
        System.out.println("Tree structure:");
        System.out.println("       A(1)");
        System.out.println("      /   \\");
        System.out.println("    B(3)  C(2)");
        System.out.println("    /");
        System.out.println("  D(4)");
        
        System.out.print("\nEnumeration by priority: ");
        LnStrm<String> stream = FnGtreeSUtil.PFirstEnumerate(a);
        
        while (true) {
            LnStcn<String> result = stream.eval0();
            if (result.nilq()) break;
            System.out.print(result.hd() + " ");
            stream = result.tl();
        }
        System.out.println("\nExpected: A C B D");
    }
    
    static void testComplexTree() {
        // Create a more complex tree
        
        SimpleGtree<String> e = new SimpleGtree<>("E", 1);
        SimpleGtree<String> f = new SimpleGtree<>("F", 6);
        SimpleGtree<String> g = new SimpleGtree<>("G", 4);
        SimpleGtree<String> h = new SimpleGtree<>("H", 8);
        
        SimpleGtree<String> b = new SimpleGtree<>("B", 2, FnListSUtil.sing(e));
        SimpleGtree<String> c = new SimpleGtree<>("C", 7, FnListSUtil.sing(f));
        SimpleGtree<String> d = new SimpleGtree<>("D", 3, 
            FnListSUtil.cons(g, FnListSUtil.sing(h)));
        
        FnList<FnGtree<String>> aChildren = FnListSUtil.cons(b, 
            FnListSUtil.cons(c, FnListSUtil.sing(d)));
        SimpleGtree<String> a = new SimpleGtree<>("A", 5, aChildren);
        
        System.out.println("Tree structure:");
        System.out.println("           A(5)");
        System.out.println("        /   |    \\");
        System.out.println("      B(2) C(7)  D(3)");
        System.out.println("      /     |     |  \\");
        System.out.println("    E(1)   F(6)  G(4) H(8)");
        
        System.out.print("\nEnumeration by priority: ");
        LnStrm<String> stream = FnGtreeSUtil.PFirstEnumerate(a);
        
        while (true) {
            LnStcn<String> result = stream.eval0();
            if (result.nilq()) break;
            System.out.print(result.hd() + " ");
            stream = result.tl();
        }
        System.out.println("\nExpected: A B D E G C F H");
        System.out.println("(Priority order: 5, 2, 3, 1, 4, 7, 6, 8)");
    }
}