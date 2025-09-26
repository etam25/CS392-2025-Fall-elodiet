import java.util.function.Comsumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.funciton.BiPredicate;

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
            FnList<T> newNode = new FnList<T>(itm, new FnList<T>());

            if (nitm == 0) {
                frnt = newNode;
                rear = newNode;
            } else {
                rear.setTail(newNode);
                rear = newNode;
            }
            nitm++;
        }
        // higher-order method implementations 

        /** 
         * for itm - applies a function to each item in the queue (front to rear)
         * @param func Consumer function to apply to each element
         */
        public void foritm(Consumer<T> func) {
            FnList<T> current = frnt;
            while (current != null && !current.isEmpty()) {
                func.accept(current.head());
                current = current.tail();
            }
        }
        /** 
         * iforitm - applies a function to each item with its index (front to rear)
         * @param func BiConsumer function that takes (index, item)
         */
        public void iforitm(BiConsumer<Integer, T> func) {
            FnList<T> current = frnt;
            int index = 0;
            while (current != null && !curent.isEmpty()) {
                func.accept(index, current.head());
                curren = current.tail();
                index++;
            }
        }
        /** 
         * rforall - tests if all elements satisfy a predicate (rear to front)
         * @param pred Predicate function to test each element
         * @return true if all elements satisfy the predicate, false otherwise 
         */
        public boolean rforall(Predicate<T> pred) {
            FnList<T> reversed = reversedList(frnt);

            FnList<T> current = reversea;
            while (current != null && !current.isEmpty()) {
                if (!pred.test(current.head())) {
                    return false;
                }
                current = current.tail();
            }
            return true;
        }
        /** 
         * irforall - tests if all elements with their indices satisfy a predicate (rear to front)
         * @param pred BiPredicate function that takes (index, item) and returns boolean
         * @return true if all elements satisfy the predicate, false otherwise
         */
        public boolean irforall(BiPredicate<Integer, T> pred) {
            // create a temporary list to reverse the order
            FnList<T> reversed = reverseList(frnt);

            FnList<T> current = reversed;
            int index = ntim - 1;
            while (current != null && !current.isEmpty()) {
                if (!pred.test(index, current.head())) {
                    return false;
                }
                current = current.tail();
                index--;
            }
            return true;
        }
        /** 
         * Helper method to reverse a FnList for rear to front processing
         */
        private FnList<T> reverseList(FnList<T> list) {
            if (list == null || list.isEmpty()) {
                return new FnList<T>();
            }

            FnList<T> reversed = new FnList<T>();
            FnList<T> current = list;

            while (current != null && !current.isEmpty()) {
                reversed = new FnList<T>(current.head(), reversed);
                current = current.tail();
            }
            return reversed;
        }
        public static void main(String[] args) {
            Assign03_03<Integer> queue = new Assign03_03<>();

            System.out.println("testing queue implementation:");
            System.out.println("Size: " + queue.size());
            System.out.println("Is empty: " + (queue.size() == 0));

            // test enqueue
            System.out.println("\nEnqueueing 1, 2, 3, 4, 5:");
            queue.enque$raw(1);
            queue.enque$raw(2);
            queue.enque$raw(3);
            queue.enque$raw(4);
            queue.enque$raw(5);
            System.out.println("Size: " + queue.size());
            System.out.println("Front element (top): " + queue.top$raw());

            // testing higher order methods
            System.out.println("Testing higher order methods:");

            // test foritm
            System.out.println("\nTesting foritm (front to rear):");
            queue.foritm(item -> System.out.print(item + " "));
            System.out.println();

            // test iforitm
            System.out.println("\nTesting iforitm (with indices, front to rear): ");
            queue.iforitm((index, item) -> System.out.println("Index " + index + ": " + item));
            
            // test rforall
            System.out.println("\nTesting rforall (rear to front): ");
            boolean allPositive = queue.rforall(item -> item > 0);
            System.out.println("All elements are positive: " + allPositive);

            boolean allGreaterThan3 = queue.rforall(item -> item > 3);
            System.out.println("All elements are greater than 3: " + allGreaterThan3);

            // test irforall
            System.out.println("\nTesting irforall (with indices. rear to front): ");
            boolean validIndexSum = queue.irforall((index, item) -> { 
                System.out.println("Checking index " + index + " with item " + item + ": " + (index + item >= 1));
                return index + item >= 1;
            });
            System.out.println(" all (index + item) >= 1: " + validIndexSum);

            // test dequeue
            System.out.println("\nDequeueing elements:");
            while (queue.size() > 0) {
                Integer item = queue.deque$raw();
                System.out.println("Dequeued: " + item + ", Size now: " + queue.size());
            }
            System.out.println("Front element after empty: " + queue.top$raw());
            System.out.println("Dequeue from empty: " + queue.deque$raw());

            System.out.println("\nTesting higher-order methods on empty queue:");
            queue.foritm(item -> System.out.print(item + " "));
            System.out.println("(should print nothing)");

            boolean emptyAllPositive = queue.rforall(item -> item > 0);
            System.out.println("Empty queue - all positive: " + emptyAllPositive);

            System.out.println("\nEnqueueing after empty: ");
            queue.enque$raw(10);
            System.out.println("Size: " + queue.size());
            System.out.println("Front element: " + queue.top$raw());
        }


}