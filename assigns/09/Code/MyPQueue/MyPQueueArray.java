package Library.MyPQueue;

import java.util.function.ToIntFunction;

public class MyPQueueArray<T> extends MyPQueueBase<T> {
    private Object[] heap;
    private int size;
    private int capacity;
    private ToIntFunction<T> priorityFunc;

    public MyPQueueArray(int capacity, ToIntFunction<T> priorityFunc) {
        this.capacity = capacity;
        this.heap = new Object[capacity];
        this.size = 0;
        this.priorityFunc = priorityFunc;
    }

    public MyPQueueArray(ToIntFunction<T> priorityFunc) {
        this(100, priorityFunc);
    }

    @Override
    public int size() {
        return size;
    }

    @Override 
    public boolean isFull() {
        return size >= capacity;
    }

    @Override
    public T top$raw() {
        if (isEmpty()) {
            throw new MyPQueueEmptyExn();
        }
        return (T) heap[0];
    }

    @Override
    public T deque$raw() {
        if (isEmpty()) {
            throw new MyPQueueEmptyExn();
        }
        T result = (T) heap[0];
        size--;
        if (size > 0) {
            heap[0] = heap[size];
            heap[size] = null;
            siftDown(0);
        } else {
            heap[0] = null;
        }
        return result;
    }

    @Override 
    public void enque$raw(T itm) {
        if (isFull()) {
            throw new MyPQueueFullExn();
        }
        heap[size] = itm;
        siftUp(size);
        size++;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T current = (T) heap[index];
            T parent = (T) heap[parentIndex];
            if (priorityFunc.applyAsInt(current) < priorityFunc.applyAsInt(parent)) {
                heap[index] = parent;
                heap[parentIndex] = current;
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;  // FIXED: was "smallext"
            
            if (leftChild < size) {
                T current = (T) heap[smallest];
                T left = (T) heap[leftChild];
                if (priorityFunc.applyAsInt(left) < priorityFunc.applyAsInt(current)) {
                    smallest = leftChild;
                }
            }
            
            if (rightChild < size) {
                T current = (T) heap[smallest];
                T right = (T) heap[rightChild];
                if (priorityFunc.applyAsInt(right) < priorityFunc.applyAsInt(current)) {
                    smallest = rightChild;
                }
            }
            
            if (smallest != index) {
                Object temp = heap[index];
                heap[index] = heap[smallest];
                heap[smallest] = temp;
                index = smallest;
            } else {
                break;
            }
        }
    }

    public void printHeap() {
        System.out.print("Heap: [");
        for (int i = 0; i < size; i++) {
            T item = (T) heap[i];
            System.out.print(item + "(" + priorityFunc.applyAsInt(item) + ")");
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}