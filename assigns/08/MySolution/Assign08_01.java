import Library.FnList.*;
import Library.LnList.*;
import Library.FnTuple.*;
import Library.MyMap00.*;
import Library.LnStrm.*;
import java.util.function.BiConsumer;

public class Assign08_01<V> implements MyMap00<String, V> {
    // HX-2025-11-12:
    // Please give an implementation of hash table
    // that uses separate chaining for handling collisions.
    private LnList<FnTupl2<String, FnList<V>>> table[];
    private int numKeys; // track number of distinct keys
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    
    // Constructor with default capacity
    @SuppressWarnings("unchecked")
    public Assign08_01() {
        this(DEFAULT_CAPACITY);
    }
    
    // Constructor with specified capacity
    @SuppressWarnings("unchecked")
    public Assign08_01(int capacity) {
        this.capacity = capacity;
        this.table = (LnList<FnTupl2<String, FnList<V>>>[]) new LnList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LnList<FnTupl2<String, FnList<V>>>();
        }
        this.numKeys = 0;
    }
    
    // Hash function to map keys to indices
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    
    // Returns the number of distinct keys
    @Override
    public int size() {
        return numKeys;
    }
    
    // Checks if the map is full (for this implementation, never full)
    @Override
    public boolean isFull() {
        return false; // Hash table with separate chaining doesn't have a fixed limit
    }
    
    // Checks if the map is empty
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }
    
    // Returns a stream of all key-value list pairs
    @Override
    public LnStrm<FnTupl2<String, FnList<V>>> strmize() {
        FnList<FnTupl2<String, FnList<V>>> result = FnListSUtil.nil();
        
        for (int i = 0; i < capacity; i++) {
            LnList<FnTupl2<String, FnList<V>>> bucket = table[i];
            bucket.foritm(entry -> {
                // This won't work directly, need to build list differently
            });
        }
        
        // Build result by iterating through all buckets
        for (int i = capacity - 1; i >= 0; i--) {
            LnList<FnTupl2<String, FnList<V>>> bucket = table[i];
            FnList<FnTupl2<String, FnList<V>>> bucketList = bucket.listize();
            FnListSUtil.foritm(bucketList, entry -> {});
            result = FnListSUtil.append(bucketList, result);
        }
        
        return FnListSUtil.strmize(result);
    }
    
    // Search for key, assumes key exists
    @Override
    public FnList<V> search$raw(String key) {
        int index = hash(key);
        LnList<FnTupl2<String, FnList<V>>> bucket = table[index];
        FnList<FnTupl2<String, FnList<V>>> bucketList = bucket.listize();
        
        while (!FnListSUtil.nilq(bucketList)) {
            FnTupl2<String, FnList<V>> entry = bucketList.hd();
            if (entry.sub0.equals(key)) {
                return entry.sub1;
            }
            bucketList = bucketList.tl();
        }
        
        return null; // Should not reach here if key exists
    }
    
    // Search for key, throws exception if not found
    @Override
    public FnList<V> search$exn(String key) {
        FnList<V> result = search$opt(key);
        if (result == null) {
            throw new MyMap00NoKeyExn();
        }
        return result;
    }
    
    // Search for key, returns null if not found
    @Override
    public FnList<V> search$opt(String key) {
        int index = hash(key);
        LnList<FnTupl2<String, FnList<V>>> bucket = table[index];
        FnList<FnTupl2<String, FnList<V>>> bucketList = bucket.listize();
        
        while (!FnListSUtil.nilq(bucketList)) {
            FnTupl2<String, FnList<V>> entry = bucketList.hd();
            if (entry.sub0.equals(key)) {
                return entry.sub1;
            }
            bucketList = bucketList.tl();
        }
        
        return null; // Key not found
    }
    
    // Insert key-value pair, assumes insertion will work
    @Override
    public void insert$raw(String key, V val) {
        insert$opt(key, val);
    }
    
    // Insert key-value pair, throws exception if insertion fails
    @Override
    public void insert$exn(String key, V val) {
        if (!insert$opt(key, val)) {
            throw new MyMap00FullExn();
        }
    }
    
    // Insert key-value pair, returns false if insertion fails
    @Override
    public boolean insert$opt(String key, V val) {
        int index = hash(key);
        LnList<FnTupl2<String, FnList<V>>> bucket = table[index];
        FnList<FnTupl2<String, FnList<V>>> bucketList = bucket.listize();
        
        // Search for existing key
        FnList<FnTupl2<String, FnList<V>>> current = bucketList;
        while (!FnListSUtil.nilq(current)) {
            FnTupl2<String, FnList<V>> entry = current.hd();
            if (entry.sub0.equals(key)) {
                // Key exists, prepend value to its list (LIFO)
                entry.sub1 = FnListSUtil.cons(val, entry.sub1);
                return true;
            }
            current = current.tl();
        }
        
        // Key doesn't exist, create new entry
        FnList<V> newVals = FnListSUtil.sing(val);
        FnTupl2<String, FnList<V>> newEntry = new FnTupl2<>(key, newVals);
        bucket.prepend(newEntry);
        numKeys++;
        
        return true;
    }
    
    // Remove key, assumes key exists
    @Override
    public FnList<V> remove$raw(String key) {
        return remove$opt(key);
    }
    
    // Remove key, throws exception if not found
    @Override
    public FnList<V> remove$exn(String key) {
        FnList<V> result = remove$opt(key);
        if (result == null) {
            throw new MyMap00NoKeyExn();
        }
        return result;
    }
    
    // Remove key, returns null if not found
    @Override
    public FnList<V> remove$opt(String key) {
        int index = hash(key);
        LnList<FnTupl2<String, FnList<V>>> bucket = table[index];
        
        // Rebuild the bucket without the key
        FnList<FnTupl2<String, FnList<V>>> bucketList = bucket.listize();
        FnList<FnTupl2<String, FnList<V>>> newBucketList = FnListSUtil.nil();
        FnList<V> removedVals = null;
        
        while (!FnListSUtil.nilq(bucketList)) {
            FnTupl2<String, FnList<V>> entry = bucketList.hd();
            if (entry.sub0.equals(key)) {
                removedVals = entry.sub1;
                numKeys--;
            } else {
                newBucketList = FnListSUtil.cons(entry, newBucketList);
            }
            bucketList = bucketList.tl();
        }
        
        // Rebuild the bucket
        table[index] = new LnList<FnTupl2<String, FnList<V>>>();
        FnListSUtil.foritm(newBucketList, entry -> {
            table[index].prepend(entry);
        });
        
        return removedVals;
    }
    
    // Iterate over all key-value pairs
    @Override
    public void foritm(BiConsumer<? super String, ? super V> work) {
        for (int i = 0; i < capacity; i++) {
            LnList<FnTupl2<String, FnList<V>>> bucket = table[i];
            FnList<FnTupl2<String, FnList<V>>> bucketList = bucket.listize();
            
            FnListSUtil.foritm(bucketList, entry -> {
                String key = entry.sub0;
                FnList<V> vals = entry.sub1;
                
                // Process each value for this key
                FnListSUtil.foritm(vals, val -> {
                    work.accept(key, val);
                });
            });
        }
    }
}