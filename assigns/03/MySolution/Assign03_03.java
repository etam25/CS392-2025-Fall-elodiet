public class Assign03_03<T> extends MyQueueBase<T> {
    int nitm = -1;
    FnList<T> frnt = null;
    FnList<T> rear = null;

    public Assign03_03() {
        nitm = 0;
        frnt = new FnList<T>();
        rear = new FnList<T>();
    }
    public int size() {
        return nitm;
    }

    public boolean isFull() {
        return false;
    }

    public T top$raw() {
        // returns the front element without removing it
        if (nitm == 0) {
            return null;
        }
        return frnt.head();
    }
    public T deque$raw() {
        // removes and returns the front element 
        if (nitm == 0) {
            return null;
        }

        T item = frnt.head();
        frnt = frnt.tail();
        nitm--;

        if (nitm == 0) {
            rear = new FnList<T>();
        }
        return item;
    }

    public void enque$raw(T itm) {
        if (nitm == 0) {
            frnt = new FnList<T>(itm, new FnList<T>());
            rear.setTail(newNode);
            rear = newNode;
        }
        nitm++;
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

