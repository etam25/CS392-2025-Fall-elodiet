import java.util.Random;

public class Quiz02_06 {
    Node root = null;
    private Random rand = new Random();

    public class Node {
        int key;     
        int size;    
        Node parent;
        Node lchild; 
        Node rchild; 
    }

    private int getSize(Node x) {
        return (x == null) ? 0 : x.size;
    }

    private void updateSize(Node x) {
        if (x != null) {
            x.size = 1 + getSize(x.lchild) + getSize(x.rchild);
        }
    }

    private void rotateLeft(Node x) {
        if (x == null || x.rchild == null) return;

        Node y = x.rchild;
        x.rchild = y.lchild;
        if (y.lchild != null) {
            y.lchild.parent = x;
        }

        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.lchild) {
            x.parent.lchild = y;
        } else {
            x.parent.rchild = y;
        }

        y.lchild = x;
        x.parent = y;

        updateSize(x);
        updateSize(y);
    }

    private void rotateRight(Node x) {
        if (x == null || x.lchild == null) return;

        Node y = x.lchild;
        x.lchild = y.rchild;
        if (y.rchild != null) {
            y.rchild.parent = x;
        }

        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.lchild) {
            x.parent.lchild = y;
        } else {
            x.parent.rchild = y;
        }

        y.rchild = x;
        x.parent = y;

        updateSize(x);
        updateSize(y);
    }

    private Node select(Node x, int k) {
        while (x != null) {
            int leftSize = getSize(x.lchild);
            if (k == leftSize + 1) {
                return x;
            } else if (k <= leftSize) {
                x = x.lchild;
            } else {
                k -= leftSize + 1;
                x = x.rchild;
            }
        }
        return null;
    }

    public void reroot() {
        if (root == null) return;
        if (root.size <= 1) return;

        int total = root.size;
        int k = rand.nextInt(total) + 1; 
        Node target = select(root, k);
        if (target == null) return;

        while (target.parent != null) {
            if (target == target.parent.lchild) {
                rotateRight(target.parent);
            } else {
                rotateLeft(target.parent);
            }
        }
        root = target;
    }

    public boolean insert(int key) {
        if (root == null) {
            Node n = new Node();
            n.key = key;
            n.size = 1;
            n.parent = null;
            root = n;
            return true;
        }

        Node curr = root;
        Node parent = null;

        while (curr != null) {
            parent = curr;
            if (key == curr.key) {
                return false;
            } else if (key < curr.key) {
                curr = curr.lchild;
            } else {
                curr = curr.rchild;
            }
        }

        Node n = new Node();
        n.key = key;
        n.size = 1;
        n.parent = parent;
        n.lchild = null;
        n.rchild = null;

        if (key < parent.key) {
            parent.lchild = n;
        } else {
            parent.rchild = n;
        }
        Node p = n.parent;
        while (p != null) {
            updateSize(p);
            p = p.parent;
        }

        return true;
    }

    private void inorder(Node x) {
        if (x == null) return;
        inorder(x.lchild);
        System.out.print(x.key + " ");
        inorder(x.rchild);
    }

    public static void main (String[] args) {
        Quiz02_06 tree = new Quiz02_06();

        int[] keys = {10, 5, 15, 3, 7, 12, 18};
        System.out.println("Insert tests:");
        for (int k : keys) {
            System.out.println("insert(" + k + ") = " + tree.insert(k));
        }
        System.out.println("insert(7) again (should be false): " + tree.insert(7));

        System.out.print("In-order traversal after inserts: ");
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("Root key: " + tree.root.key + ", size: " + tree.root.size);

        System.out.println("\nReroot tests:");
        for (int i = 0; i < 5; i++) {
            tree.reroot();
            System.out.println("After reroot #" + (i + 1) +
                               ": root = " + tree.root.key +
                               ", size = " + tree.root.size);
        }

        System.out.print("In-order traversal after reroots (should be sorted): ");
        tree.inorder(tree.root);
        System.out.println();

        return;
    }
}
