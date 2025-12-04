import Library.FnList.*;
import Library.MyQueue.*;

public class Assign03_03<T> extends MyQueueBase<T> {
    int nitm = -1;
    FnList<T> frnt = null;
    FnList<T> rear = null;
    
    @Override
    public void foritm(java.util.function.Consumer<? super T> action) {
        FnList<T> current = frnt;
        while (current != null && !current.nilq()) {
            action.accept(current.hd());
            current = current.tl();
        }
    }
    
    @Override
    public void iforitm(java.util.function.BiConsumer<Integer, ? super T> action) {
        FnList<T> current = frnt;
        int index = 0;
        while (current != null && !current.nilq()) {
            action.accept(index, current.hd());
            current = current.tl();
            index++;
        }
    }
    
    public Assign03_03() {
        nitm = 0;
        frnt = null;
        rear = null;
    }
    
    public int size() {
        return nitm;
    }
    
    public boolean isFull() {
        return false;
    }
    
    public T top$raw() {
        // Caller checks if queue is empty, so no size check here
        // Handle case where frnt might be null
        if (frnt == null) {
            return null;
        }
        return frnt.hd();
    }
    
    public T deque$raw() {
        if (nitm == 0) {
            return null;
        }
        T item = frnt.hd();
        frnt = frnt.tl();
        nitm--;
        if (nitm == 0) {
            frnt = null;
            rear = null;
        }
        return item;
    }
    
    public void enque$raw(T itm) {
        FnList<T> newNode = new FnList<T>(itm, new FnList<T>());
        
        if (nitm == 0) {
            frnt = newNode;  
            rear = newNode;
        } else {
            // Rebuild the entire list with the new item appended
            frnt = appendToEnd(frnt, itm);
            rear = newNode;
        }
        nitm++;
    }
    
    // Helper method to append an item to the end of the list
    private FnList<T> appendToEnd(FnList<T> list, T item) {
        // Get the tail first
        FnList<T> tail = list.tl();
        
        // Check if the tail is empty (nilq())
        if (tail.nilq()) {
            // This is the last real node, append the new item here
            return new FnList<T>(list.hd(), new FnList<T>(item, new FnList<T>()));
        }
        
        // Recursively rebuild the list
        return new FnList<T>(list.hd(), appendToEnd(tail, item));
    }
    
    public static void main(String[] args) {
        Assign03_03<Integer> queue = new Assign03_03<>();
        System.out.println("Testing Queue Implementation:");
        System.out.println("Size: " + queue.size());
        System.out.println("Is empty: " + (queue.size() == 0));
        
        // Test enqueue
        System.out.println("\nEnqueuing 1, 2, 3:");
        queue.enque$raw(1);
        queue.enque$raw(2);
        queue.enque$raw(3);
        System.out.println("Size: " + queue.size());
        System.out.println("Front element (top): " + queue.top$raw());
        
        // Test dequeue
        System.out.println("\nDequeuing elements:");
        while (queue.size() > 0) {
            Integer item = queue.deque$raw();
            System.out.println("Dequeued: " + item + ", Size now: " + queue.size());
        }
        System.out.println("Front element after empty: " + queue.top$raw());
        System.out.println("Dequeue from empty: " + queue.deque$raw());
        
        // Test enqueue after empty
        System.out.println("\nEnqueuing after empty:");
        queue.enque$raw(10);
        System.out.println("Size: " + queue.size());
        System.out.println("Front element: " + queue.top$raw());
    }
}