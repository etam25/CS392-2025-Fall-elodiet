import Library.LnStrm.*;

public class Quiz02_04 {
    public static class AVLnode {
        int key;
        AVLnode lchild;
        AVLnode rchild;

        public AVLnode(int key) {
            this.key = key;
            this.lchild = null;
            this.rchild = null;
        }

        public AVLnode(int key, AVLnode left, AVLnode right) {
            this.key = key;
            this.lchild = left;
            this.rchild = right;
        } 
    }

    private static class AVLInfo {
        boolean isAVL;
        int height;

        public AVLInfo(boolean isAVL, int height) {
            this.isAVL = isAVL;
            this.height = height;
        }
    }

    public static boolean isAVL(AVLnode avl) {
        return checkAVL(avl).isAVL;
    }
    private static AVLInfo checkAVL(AVLnode node) {
        if (node == null) {
            return new AVLInfo(true, -1);
        }
        AVLInfo leftInfo = checkAVL(node.lchild);
        if (!leftInfo.isAVL) {
            return new AVLInfo(false, 0);
        }

        AVLInfo rightInfo = checkAVL(node.rchild);
        if (!rightInfo.isAVL) {
            return new AVLInfo(false, 0);
        }

        int heightDiff = Math.abs(leftInfo.height - rightInfo.height);
        boolean isBalanced = (heightDiff <= 1);

        int currentHeight = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new AVLInfo(isBalanced, currentHeight);
    }

    public static AVLnode genAVLBST_maxHeight() {
        int[] counter = {0};

        int targetHeight = findHeightForNodes(1000000);

        System.out.println("Generating maximal height AVL tree");
        System.out.println("Target height for 1,000,000 nodes: " + targetHeight);

        AVLnode root = buildFibonacciAVL(targetHeight, counter);

        int remaining = 1000000 - counter[0];
        if (remaining > 0) {
            System.out.println("Adding " + remaining + " more nodes");
            for (int i = 0; i < remaining; i++) {
                root = insertAVL(root, counter[0]++);
            }
        }

        int actualHeight = getHeight(root);
        System.out.println("Actual height achieved: " + actualHeight);
        System.out.println("Total nodes: " + counter[0]);

        return root;
    }

    private static AVLnode buildFibonacciAVL(int height, int[] counter) {
        if (height < 0) return null;
        if (counter[0] >= 1000000) return null;

        AVLnode left = buildFibonacciAVL(height - 1, counter);

        if (counter[0] >= 1000000) return left;
        AVLnode node = new AVLnode(counter[0]++);
        node.lchild = left;

        if (counter[0] >= 1000000) return node;
        node.rchild = buildFibonacciAVL(height - 2, counter);

        return node;
    }

    private static int findHeightForNodes(int targetNodes) {
        int h = 0;
        while (fibTreeNodes(h) < targetNodes) {
            h++;
        }
        return h - 1;
    }

    private static int fibTreeNodes(int height) {
        if (height < 0) return 0;
        if (height == 0) return 1;
        if (height == 1) return 2;

        int prev2 = 1;
        int prev1 = 2;
        int current = 0;

        for (int i = 2; i <= height; i++) {
            current = prev1 + prev2 + 1;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
    private static AVLnode insertAVL(AVLnode node, int key) {
        if (node == null) return new AVLnode(key);

        if (key < node.key) {
            node.lchild = insertAVL(node.lchild, key);

        } else {
            node.rchild = insertAVL(node.rchild, key);
        }

        return balance(node);
    }

    private static AVLnode balance(AVLnode node) {
        if (node == null) return null;

        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.lchild) < 0) {
                node.lchild = rotateLeft(node.lchild);
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (getBalance(node.rchild) > 0) {
                node.rchild = rotateRight(node.rchild);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private static int getBalance(AVLnode node) {
        if (node == null) return 0;
        return getHeight(node.lchild) - getHeight(node.rchild);
    }

    private static AVLnode rotateRight(AVLnode y) {
        AVLnode x = y.lchild;
        AVLnode T2 = x.rchild;

        x.rchild = y;
        y.lchild = T2;

        return x;
    }

    private static AVLnode rotateLeft(AVLnode x) {
        AVLnode y = x.rchild;
        AVLnode T2 = y.lchild;

        y.lchild = x;
        x.rchild = T2;

        return y;
    }

    private static int getHeight(AVLnode node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.lchild), getHeight(node.rchild));
    }

    private static int countNodes(AVLnode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.lchild) + countNodes(node.rchild);
    }

    private static boolean isBST(AVLnode node, int min, int max) {
        if (node == null) return true;
        if (node.key <= min || node.key >= max) return false;
        return isBST(node.lchild, min, node.key) &&
               isBST(node.rchild, node.key, max);
    }

    public static void main(String[] args) {
        System.out.println("Testing isAVL()\n");

        // test 1
        AVLnode valid = new AVLnode(10,
            new AVLnode(5, 
                new AVLnode(2),
                new AVLnode(7)),
            new AVLnode(15, 
                new AVLnode(12),
                new AVLnode(20))
         );
         System.out.println("Test 1 - Balanced tree: " + isAVL(valid));

         // test 2
         AVLnode invalid = new AVLnode(10, 
            new AVLnode(5,
                new AVLnode(2, 
                    new AVLnode(1),
                null),
            null),
            null
         );
         System.out.println("Test 2 - Unbalanced tree: " + isAVL(invalid));

         // test 3
         AVLnode single = new AVLnode(20);
         System.out.println("Test 3 - Single node: " + isAVL(single));

         // test 4
         System.out.println("Test 4 - Null tree: " + isAVL(null));

         // generate maximal height AVL BST with 1M nodes
         long startTime = System.currentTimeMillis();
         AVLnode largeTree = genAVLBST_maxHeight();
         long endTime = System.currentTimeMillis();

         System.out.println("\nGeneration time: " + (endTime - startTime) + "ms");
        System.out.println("Is valid AVL: " + isAVL(largeTree));
        System.out.println("Is valid BST: " + isBST(largeTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Node count: " + countNodes(largeTree));
        
        // Theoretical comparison
        System.out.println("\n=== Theoretical Analysis ===");
        double log2n = Math.log(1000000) / Math.log(2);
        System.out.println("log2(1,000,000) ≈ " + String.format("%.2f", log2n));
        System.out.println("Min possible height (perfect tree): " + (int)Math.ceil(log2n - 1));
        System.out.println("Max AVL height ≈ 1.44 * log2(n) ≈ " + String.format("%.2f", 1.44 * log2n));
        System.out.println("\nOur maximal height AVL achieves near-theoretical maximum!");

    }
}