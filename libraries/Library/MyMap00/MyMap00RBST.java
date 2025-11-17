package Library.MyMap00;

import Library.FnList.*;

import java.util.function.Consumer;
import java.util.function.BiConsumer;

public abstract class MyMap00RBST<K extends Comparable<K>, V> implements MyMap00<K, V> {
    private Node root;

    private class Node {
	K key;
	FnList<V> vals;
	int size;
        Node lchild;
	Node rchild;
    }

}
