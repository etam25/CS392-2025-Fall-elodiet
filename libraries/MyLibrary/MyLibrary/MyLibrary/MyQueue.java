package MyLibrary;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* 
 * Ex) if the queue is a line:
        - enqueue: adding a person to the line
        - deque: removing the person
        - can only add them to the back, remove from the front
        - can peek the front of the line (look at the front);
        doesn't change order
        - can be empty or full
 */

public class MyQueue<T> {
    /* 
     * PUBLIC EXCEPTION CLASS
     * thrown when dequeuing or peeking from an empty queue
     */
    public static final class MyQueueEmptyExn extends Exception {
        public MyQueueEmptyExn(String g) { super(g);}
    }
    /** thrown when enqueuing into a full queue */
    public static final class MyQueueFullExn extends Exception {
        public MyQueueFullExn(String g) { super(g);}
    }
    /* 
     * INTERNAL REPRESENTATION
     * backing array for elements (circular buffer)
     */
    private final Object[] buf;
    // max number of elements allowed
    private final int cap;
    //head points to front element; tail points to next insert position
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    /* 
     * CONSTRUCTORS
     * create a queue with given capacity
     */
     public MyQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.cap = capacity;
        this.buf = new Object[capacity];
     }

     /** default queue capacity = 100 */
     public MyQueue() { this(100); }
     /* 
      * BASIC INFO
      */
      public int size() { return size;} // number of items currently stored
      public boolean isEmpty() { return size == 0; }
      public boolean isFull() { return size == cap; }
      
      /* 
       * TOP (PEEK)
       */
      public T top$raw() {
        if (isEmpty()) throw new IllegalStateException("top$raw on empty queue");
        @SuppressWarnings("unchecked") T v = (T) buf[head];
        return v;
      }
      /** safe optional: returns null if empty */
      public T top$opt() {
        return isEmpty() ? null : top$raw();
      }

    /** safe checked: throws MyQueueEmptyExn if empty */
    public T top$exn() throws MyQueueEmptyExn {
        if (isEmpty()) throw new MyQueueEmptyExn("queue is empty");
        return top$raw();
    }

    /* 
     * DEQUE (REMOVE FROM FRONT)
     */

     /** unsafe: throws IllegalStateException if empty */
     public T deque$raw() {
        if (isEmpty()) throw new IllegalStateException("deque$raw on empty queue");
        @SuppressWarnings("unchecked") T v = (T) buf[head];
        buf[head] = null;
        head = (head + 1) % cap;
        size--;
        return v;
     }

     /** checked: throws MyQueueEmptyExn if empty */
     public T deque$exn() throws MyQueueEmptyExn {
        if (isEmpty()) throw new MyQueueEmptyExn("queue is empty");
        return deque$raw();
     }

     /*
      * ENQUE (ADD TO BACK) 
      */
    /** unsafe: throws IllegalStateException if full */
    public void enque$raw(T itm) {
        if (isFull()) throw new IllegalStateException("enque$raw on full queue");
        buf[tail] = itm;
        tail = (tail + 1) % cap;
        size++;
    }
    /** optional: returns false if full, true if success */
    public boolean enque$opt(T itm) {
        if (isFull()) return false;
        enque$raw(itm);
        return true;
    }
    /** checked: throws MyQueueFullExn if full */
    public void enque$exn(T itm) throws MyQueueFullExn {
        if (isFull()) throw new MyQueueFullExn("queue is full");
        enque$raw(itm);
    }
    /*
     * ITERATION UTILITIES
     * apply a consumer to each item (forward order)
     */
    public void foritm(Consumer<? super T> f) {
        for (int i = 0; i < size; i++) {
            int idx = (head + i) % cap;
            @SuppressWarnings("unchecked") T v = (T) buf[idx];
            f.accept(v);
        }
    }

    /** Apply a BiConsumer with (index, item) forward */
    public void iforitm(BiConsumer<Integer, ? super T>f) {
        for (int i = 0; i < size; i++) {
            int idx = (head + i) % cap;
            @SuppressWarnings("unchecked") T v = (T) buf[idx];
            f.accept(i, v);
        }
    }

    /** Reverse order Consumer */
    public void rforitm(Consumer<? super T>f) {
        for (int i = size - 1; i >= 0; i--) {
            int idx = (head + i) % cap;
            @SuppressWarnings("unchecked") T v = (T) buf[idx];
            f.accept(v);
        }
    }

    /** Reverse order with index */
    public void iroforitm(BiConsumer<Integer, ? super T>f) {
        for (int i = size - 1; i >= 0; i--) {
            int idx = (head + i) % cap;
            @SuppressWarnings("unchecked") T v = (T) buf[idx];
            f.accept(i, v);
        }
    }
    /* 
     * UTILITY: PRINT QUEUE CONTENTS
    */
    public void System$out$print() {
        System.out.print("[");
        foritm(x -> System.out.print(x + " "));
        System.out.print("]");
    }

    /* 
     * EXAMPLE USAGE
     */

     public static void main(String[] args) {
        // create a queue with capacity 100
        MyQueue<Integer> q = new MyQueue<>(100);
        for(int i = 0; i < 100; i++) {
            q.enque$raw(i);
        }
        /* 
         * with raw: check if the queue is full before trying 
         * to add the number 12345 to it
         */
        if (!q.isFull()) {
            q.enque$raw(45);
        } else {
            System.out.println("Queue is full");
        }
        /* 
         * with exn (Exception)
         */
        try {
            q.enque$exn(45);
        } catch (MyQueueFullExn e) {
            System.out.println("Queue is full");
        }
        /* 
         * with opt
         */

         if (q.enque$opt((Integer) 45) == false) {
            System.out.println("Queue is full");
         }
     }

}

/* 
 * method: 
     - top: look at-but don't remove-first element
     - deque: remove from the front
     - enqueue: add to the back
 * behavior 
     - raw: doesn't check to make sure the method is safe.
     Fails badly if not 
     - exn: throws an exception if something goes wrong
     - opt: returns true if its successful, false if unsuccessful 
 * other methods: 
     - int size(): current size of queue
     - boolean isFull(): true if its full, false if not
     - boolean isEmpty(): true if empty, false if full
     - System$out$print(): print al lqueue contents to stdout 
     - foritm: apply a function (Consumer) to each item (forward order)
     - iforitm: smae but also provides items index
     - rforitm and irforitm: reverse versions of the above 
 */