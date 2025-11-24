package Library.LnTree;

public class LnTree<T> {
//
    private Node root;
//    
    private class Node {
	T item; // stored data
	int size;
	Node lchild; // left subtree
	Node rchild; // right subtree
	Node(T x0, Node lxs, Node rxs) {
	    item = x0;
	    lchild = lxs;
	    rchild = rxs;
	}
    }
//
} // end of [public class LnTree<T>{...}]
