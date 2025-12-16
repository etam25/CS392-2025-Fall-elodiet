package MyLibrary.LnTree;

public class LnTree<T> {
    private Node root;

    private class Node {
        T item;
        int size;
        Node lchild;
        Node rchild;
        Node(T x0, Node lxs, Node rxs) {
            item = x0;
            lchild = lxs;
            rchild = rxs;
        }
    }
}
