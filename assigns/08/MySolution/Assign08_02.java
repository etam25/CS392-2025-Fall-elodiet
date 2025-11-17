import Library.FnList.*;
import Library.LnList.*;
import Library.FnTuple.*;
import Library.MyMap00.*;
import Library.LnStrm.*;
import java.util.function.BiConsumer;

public class Assign08_02<V> implements MyMap00<String, V> {
    
    private FnTupl2<String, FnList<V>> table[];
    private boolean[] deleted;
    private int numKeys;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;
    
    @SuppressWarnings("unchecked")
    public Assign08_02() {
        this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
    public Assign08_02(int capacity) {
        this.capacity = capacity;
        this.table = (FnTupl2<String, FnList<V>>[]) new FnTupl2[capacity];
        this.deleted = new boolean[capacity];
        this.numKeys = 0;
    }
    
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    
    private int probe(int hash, int i) {
        return (hash + i * i) % capacity;
    }
    
    private int findSlot(String key) {
        int hash = hash(key);
        
        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            
            if (table[index] == null && !deleted[index]) {
                return index;
            }
            
            if (table[index] != null && table[index].sub0.equals(key)) {
                return index;
            }
        }
        
        return -1;
    }
    
    private int findKey(String key) {
        int hash = hash(key);
        
        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            
            if (table[index] == null && !deleted[index]) {
                return -1;
            }
            
            if (table[index] != null && table[index].sub0.equals(key)) {
                return index;
            }
        }
        
        return -1;
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
            return false;
        }
        
        if (table[index] == null) {
            FnList<V> newVals = FnListSUtil.sing(val);
            table[index] = new FnTupl2<>(key, newVals);
            deleted[index] = false;
            numKeys++;
        } else {
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
        deleted[index] = true;
        numKeys--;
        
        return removedVals;
    }
    
    @Override
    public void foritm(BiConsumer<? super String, ? super V> work) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                String key = table[i].sub0;
                FnList<V> vals = table[i].sub1;
                
                FnListSUtil.foritm(vals, val -> {
                    work.accept(key, val);
                });
            }
        }
    }
}