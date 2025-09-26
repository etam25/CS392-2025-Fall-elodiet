import java.util.function.Consumer;
import java.util.function.BiConsumer;

abstract class MyDequeBase<T> {
    // Core deque operations
    public abstract int size();
    public abstract boolean isEmpty();
    public abstract boolean isFull();
    
    // Peek operations
    public abstract T rpeek(); // rear peek
    public abstract T fpeek(); // front peek
    
    // Enqueue/Dequeue operations
    public abstract void renque(T item); // rear enqueue
    public abstract T rdeque(); // rear dequeue
    public abstract void fenque(T item); // front enqueue
    public abstract T fdeque(); // front dequeue
    
    // Higher-order methods
    public abstract void foritm(Consumer<T> func);
    public abstract void iforitm(BiConsumer<Integer, T> func);
    public abstract void rforitm(Consumer<T> func);
    public abstract void irforitm(BiConsumer<Integer, T> func);
    
    // System output method
    public abstract void System$out$print();
}

/**
 * Node class for doubly-linked list implementation
 */
class DoublyLinkedNode<T> {
    T data;
    DoublyLinkedNode<T> next;
    DoublyLinkedNode<T> prev;
    
    public DoublyLinkedNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    public DoublyLinkedNode(T data, DoublyLinkedNode<T> next, DoublyLinkedNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public DoublyLinkedNode<T> getNext() {
        return next;
    }
    
    public void setNext(DoublyLinkedNode<T> next) {
        this.next = next;
    }
    
    public DoublyLinkedNode<T> getPrev() {
        return prev;
    }
    
    public void setPrev(DoublyLinkedNode<T> prev) {
        this.prev = prev;
    }
}

public class MyDequeList<T> extends MyDequeBase<T> {
    private DoublyLinkedNode<T> front;
    private DoublyLinkedNode<T> rear;
    private int size;
    
    /**
     * Constructor - creates an empty deque
     */
    public MyDequeList() {
        front = null;
        rear = null;
        size = 0;
    }
    
    /**
     * Returns the number of elements in the deque
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Checks if the deque is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Checks if the deque is full (always false for linked implementation)
     */
    @Override
    public boolean isFull() {
        return false; // Linked list implementation is never full
    }
    
    /**
     * Peek at the rear element without removing it
     */
    @Override
    public T rpeek() {
        if (isEmpty()) {
            return null;
        }
        return rear.getData();
    }
    
    /**
     * Peek at the front element without removing it
     */
    @Override
    public T fpeek() {
        if (isEmpty()) {
            return null;
        }
        return front.getData();
    }
    
    /**
     * Enqueue an item at the rear
     */
    @Override
    public void renque(T item) {
        DoublyLinkedNode<T> newNode = new DoublyLinkedNode<>(item);
        
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            newNode.setPrev(rear);
            rear = newNode;
        }
        size++;
    }
    
    /**
     * Dequeue an item from the rear
     */
    @Override
    public T rdeque() {
        if (isEmpty()) {
            return null;
        }
        
        T data = rear.getData();
        
        if (size == 1) {
            front = rear = null;
        } else {
            rear = rear.getPrev();
            rear.setNext(null);
        }
        size--;
        return data;
    }
    
    /**
     * Enqueue an item at the front
     */
    @Override
    public void fenque(T item) {
        DoublyLinkedNode<T> newNode = new DoublyLinkedNode<>(item);
        
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.setNext(front);
            front.setPrev(newNode);
            front = newNode;
        }
        size++;
    }
    
    /**
     * Dequeue an item from the front
     */
    @Override
    public T fdeque() {
        if (isEmpty()) {
            return null;
        }
        
        T data = front.getData();
        
        if (size == 1) {
            front = rear = null;
        } else {
            front = front.getNext();
            front.setPrev(null);
        }
        size--;
        return data;
    }
    
    /**
     * Apply a function to each item from front to rear
     */
    @Override
    public void foritm(Consumer<T> func) {
        DoublyLinkedNode<T> current = front;
        while (current != null) {
            func.accept(current.getData());
            current = current.getNext();
        }
    }
    
    /**
     * Apply a function to each item with index from front to rear
     */
    @Override
    public void iforitm(BiConsumer<Integer, T> func) {
        DoublyLinkedNode<T> current = front;
        int index = 0;
        while (current != null) {
            func.accept(index, current.getData());
            current = current.getNext();
            index++;
        }
    }
    
    /**
     * Apply a function to each item from rear to front
     */
    @Override
    public void rforitm(Consumer<T> func) {
        DoublyLinkedNode<T> current = rear;
        while (current != null) {
            func.accept(current.getData());
            current = current.getPrev();
        }
    }
    
    /**
     * Apply a function to each item with index from rear to front
     */
    @Override
    public void irforitm(BiConsumer<Integer, T> func) {
        DoublyLinkedNode<T> current = rear;
        int index = size - 1;
        while (current != null) {
            func.accept(index, current.getData());
            current = current.getPrev();
            index--;
        }
    }
    
    /**
     * System output print method - prints the deque contents
     */
    @Override
    public void System$out$print() {
        System.out.print("MyDequeList[" + size + "]: ");
        if (isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        
        System.out.print("Front -> ");
        DoublyLinkedNode<T> current = front;
        while (current != null) {
            System.out.print(current.getData());
            if (current.getNext() != null) {
                System.out.print(" <-> ");
            }
            current = current.getNext();
        }
        System.out.println(" <- Rear");
    }
    
    /**
     * Additional utility method to print in reverse
     */
    public void System$out$print$reverse() {
        System.out.print("MyDequeList[" + size + "] (reverse): ");
        if (isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        
        System.out.print("Rear -> ");
        DoublyLinkedNode<T> current = rear;
        while (current != null) {
            System.out.print(current.getData());
            if (current.getPrev() != null) {
                System.out.print(" <-> ");
            }
            current = current.getPrev();
        }
        System.out.println(" <- Front");
    }
    
    /**
     * Main method for comprehensive testing
     */
    public static void main(String[] args) {
        MyDequeList<Integer> deque = new MyDequeList<>();
        
        System.out.println("=== MyDequeList Comprehensive Testing ===\n");
        
        // Test initial state
        System.out.println("1. Initial State:");
        deque.System$out$print();
        System.out.println("Size: " + deque.size());
        System.out.println("Is empty: " + deque.isEmpty());
        System.out.println("Is full: " + deque.isFull());
        System.out.println("Front peek: " + deque.fpeek());
        System.out.println("Rear peek: " + deque.rpeek());
        
        // Test rear enqueue operations
        System.out.println("\n2. Testing Rear Enqueue Operations:");
        System.out.println("Rear enqueuing: 1, 2, 3");
        deque.renque(1);
        deque.renque(2);
        deque.renque(3);
        deque.System$out$print();
        System.out.println("Front peek: " + deque.fpeek());
        System.out.println("Rear peek: " + deque.rpeek());
        
        // Test front enqueue operations
        System.out.println("\n3. Testing Front Enqueue Operations:");
        System.out.println("Front enqueuing: 0, -1");
        deque.fenque(0);
        deque.fenque(-1);
        deque.System$out$print();
        System.out.println("Front peek: " + deque.fpeek());
        System.out.println("Rear peek: " + deque.rpeek());
        
        // Test higher-order methods
        System.out.println("\n4. Testing Higher-Order Methods:");
        
        System.out.println("\nforitm (front to rear):");
        deque.foritm(item -> System.out.print(item + " "));
        System.out.println();
        
        System.out.println("\niforitm (front to rear with indices):");
        deque.iforitm((index, item) -> System.out.println("  Index " + index + ": " + item));
        
        System.out.println("\nrforitm (rear to front):");
        deque.rforitm(item -> System.out.print(item + " "));
        System.out.println();
        
        System.out.println("\nirforitm (rear to front with indices):");
        deque.irforitm((index, item) -> System.out.println("  Index " + index + ": " + item));
        
        // Test reverse print
        System.out.println("\n5. Testing Reverse Print:");
        deque.System$out$print$reverse();
        
        // Test dequeue operations
        System.out.println("\n6. Testing Dequeue Operations:");
        
        System.out.println("Front dequeue: " + deque.fdeque());
        deque.System$out$print();
        
        System.out.println("Rear dequeue: " + deque.rdeque());
        deque.System$out$print();
        
        System.out.println("Front dequeue: " + deque.fdeque());
        deque.System$out$print();
        
        // Test mixed operations
        System.out.println("\n7. Testing Mixed Operations:");
        deque.renque(10);
        deque.fenque(5);
        deque.System$out$print();
        System.out.println("Front peek: " + deque.fpeek());
        System.out.println("Rear peek: " + deque.rpeek());
        
        // Test emptying the deque
        System.out.println("\n8. Emptying the Deque:");
        while (!deque.isEmpty()) {
            System.out.println("Front dequeue: " + deque.fdeque() + ", Size: " + deque.size());
            deque.System$out$print();
        }
        
        // Test operations on empty deque
        System.out.println("\n9. Testing Operations on Empty Deque:");
        System.out.println("Front dequeue from empty: " + deque.fdeque());
        System.out.println("Rear dequeue from empty: " + deque.rdeque());
        System.out.println("Front peek from empty: " + deque.fpeek());
        System.out.println("Rear peek from empty: " + deque.rpeek());
        
        // Test higher-order methods on empty deque
        System.out.println("\nHigher-order methods on empty deque:");
        deque.foritm(item -> System.out.print(item + " "));
        System.out.println("(should print nothing)");
        
        // Test as stack (LIFO) using front operations
        System.out.println("\n10. Testing as Stack (LIFO) using Front Operations:");
        deque.fenque(100);
        deque.fenque(200);
        deque.fenque(300);
        System.out.println("Pushed (front enque): 100, 200, 300");
        deque.System$out$print();
        
        System.out.println("Popping (front deque):");
        while (!deque.isEmpty()) {
            System.out.println("Popped: " + deque.fdeque());
        }
        
        // Test as queue (FIFO) using front dequeue and rear enqueue
        System.out.println("\n11. Testing as Queue (FIFO):");
        deque.renque(1000);
        deque.renque(2000);
        deque.renque(3000);
        System.out.println("Enqueued (rear): 1000, 2000, 3000");
        deque.System$out$print();
        
        System.out.println("Dequeuing (front):");
        while (!deque.isEmpty()) {
            System.out.println("Dequeued: " + deque.fdeque());
        }
        
        System.out.println("\n=== Testing Complete ===");
    }
}