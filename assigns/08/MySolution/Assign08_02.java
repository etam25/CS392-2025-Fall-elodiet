import MyLibrary.FnList.*;
import MyLibrary.LnList.*;
import MyLibrary.FnTuple.*;
import MyLibrary.MyMap00.*;
import MyLibrary.LnStrm.*;
import java.util.function.BiConsumer;

public class Assign08_02<V> implements MyMap00<String, V> {
    // HX-2025-11-12:
    // Please give an implementation of hash table
    // based on open addressing. The probing strategy
    // chosen for handling collisions is quadratic probing.
    private FnTupl2<String, FnList<V>> table[];
    private boolean[] deleted; // Track deleted slots
    private int numKeys;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;
    
    // Constructor with default capacity
    @SuppressWarnings("unchecked")
    public Assign08_02() {
        this(DEFAULT_CAPACITY);
    }
    
    // Constructor with specified capacity
    @SuppressWarnings("unchecked")
    public Assign08_02(int capacity) {
        this.capacity = capacity;
        this.table = (FnTupl2<String, FnList<V>>[]) new FnTupl2[capacity];
        this.deleted = new boolean[capacity];
        this.numKeys = 0;
    }
    
    // Hash function
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    
    // Quadratic probing: h(k, i) = (h(k) + i^2) mod capacity
    private int probe(int hash, int i) {
        return (hash + i * i) % capacity;
    }
    
    // Find slot for insertion (either empty or matching key)
    private int findSlot(String key) {
        int hash = hash(key);
        
        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            
            // Found empty slot (not deleted)
            if (table[index] == null && !deleted[index]) {
                return index;
            }
            
            // Found existing key
            if (table[index] != null && table[index].sub0.equals(key)) {
                return index;
            }
        }
        
        return -1; // Table is full
    }
    
    // Find key in table
    private int findKey(String key) {
        int hash = hash(key);
        
        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            
            // Empty slot (not deleted) means key not found
            if (table[index] == null && !deleted[index]) {
                return -1;
            }
            
            // Found the key
            if (table[index] != null && table[index].sub0.equals(key)) {
                return index;
            }
        }
        
        return -1; // Key not found
    }
    
    @Override
    public int size() {
        return numKeys;
    }
    
    @Override
    public boolean isFull() {
        return numKeys >= capacity * LOAD_FACTOR_THRESHOLD;
    }
    
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }
    
    @Override
    public LnStrm<FnTupl2<String, FnList<V>>> strmize() {
        FnList<FnTupl2<String, FnList<V>>> result = FnListSUtil.nil();
        
        // Collect all entries from table
        for (int i = capacity - 1; i >= 0; i--) {
            if (table[i] != null) {
                result = FnListSUtil.cons(table[i], result);
            }
        }
        
        return FnListSUtil.strmize(result);
    }
    
    @Override
    public FnList<V> search$raw(String key) {
        int index = findKey(key);
        if (index >= 0) {
            return table[index].sub1;
        }
        return null;
    }
    
    @Override
    public FnList<V> search$exn(String key) {
        FnList<V> result = search$opt(key);
        if (result == null) {
            throw new MyMap00NoKeyExn();
        }
        return result;
    }
    
    @Override
    public FnList<V> search$opt(String key) {
        int index = findKey(key);
        if (index >= 0) {
            return table[index].sub1;
        }
        return null;
    }
    
    @Override
    public void insert$raw(String key, V val) {
        insert$opt(key, val);
    }
    
    @Override
    public void insert$exn(String key, V val) {
        if (!insert$opt(key, val)) {
            throw new MyMap00FullExn();
        }
    }
    
    @Override
    public boolean insert$opt(String key, V val) {
        if (isFull()) {
            return false;
        }
        
        int index = findSlot(key);
        if (index < 0) {
            return false; // Can't find slot
        }
        
        if (table[index] == null) {
            // New key
            FnList<V> newVals = FnListSUtil.sing(val);
            table[index] = new FnTupl2<>(key, newVals);
            deleted[index] = false;
            numKeys++;
        } else {
            // Existing key - prepend value to list
            table[index].sub1 = FnListSUtil.cons(val, table[index].sub1);
        }
        
        return true;
    }
    
    @Override
    public FnList<V> remove$raw(String key) {
        return remove$opt(key);
    }
    
    @Override
    public FnList<V> remove$exn(String key) {
        FnList<V> result = remove$opt(key);
        if (result == null) {
            throw new MyMap00NoKeyExn();
        }
        return result;
    }
    
    @Override
    public FnList<V> remove$opt(String key) {
        int index = findKey(key);
        if (index < 0) {
            return null;
        }
        
        FnList<V> removedVals = table[index].sub1;
        table[index] = null;
        deleted[index] = true; // Mark as deleted for probing
        numKeys--;
        
        return removedVals;
    }
    
    @Override
    public void foritm(BiConsumer<? super String, ? super V> work) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                String key = table[i].sub0;
                FnList<V> vals = table[i].sub1;
                
                // Process each value for this key
                FnListSUtil.foritm(vals, val -> {
                    work.accept(key, val);
                });
            }
        }
    }
}