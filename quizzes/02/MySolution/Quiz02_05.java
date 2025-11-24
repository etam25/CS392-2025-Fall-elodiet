public class Quiz02_05 {
    
    public static class RBTnode {
        int key;
        int color;
        RBTnode lchild;
        RBTnode rchild;
        
        public RBTnode(int key, int color) {
            this.key = key;
            this.color = color;
            this.lchild = null;
            this.rchild = null;
        }
        
        public RBTnode(int key, int color, RBTnode left, RBTnode right) {
            this.key = key;
            this.color = color;
            this.lchild = left;
            this.rchild = right;
        }
    }
    
    private static final int RED = 0;
    private static final int BLACK = 1;
    
    private static class RBTInfo {
        boolean isValid;
        int blackHeight;
        
        public RBTInfo(boolean isValid, int blackHeight) {
            this.isValid = isValid;
            this.blackHeight = blackHeight;
        }
    }
    
    public static boolean isRBT(RBTnode rbt) {
        if (rbt == null) return true;
        if (rbt.color != BLACK) return false;
        return checkRBT(rbt).isValid;
    }
    
    private static RBTInfo checkRBT(RBTnode node) {
        if (node == null) {
            return new RBTInfo(true, 0);
        }
        
        RBTInfo leftInfo = checkRBT(node.lchild);
        if (!leftInfo.isValid) {
            return new RBTInfo(false, -1);
        }
        
        RBTInfo rightInfo = checkRBT(node.rchild);
        if (!rightInfo.isValid) {
            return new RBTInfo(false, -1);
        }
        
        if (node.color == RED) {
            boolean leftIsRed = (node.lchild != null && node.lchild.color == RED);
            boolean rightIsRed = (node.rchild != null && node.rchild.color == RED);
            
            if (leftIsRed || rightIsRed) {
                return new RBTInfo(false, -1);
            }
        }
        
        if (leftInfo.blackHeight != rightInfo.blackHeight) {
            return new RBTInfo(false, -1);
        }
        
        int currentBlackHeight = leftInfo.blackHeight;
        if (node.color == BLACK) {
            currentBlackHeight += 1;
        }
        
        return new RBTInfo(true, currentBlackHeight);
    }
    
    public static RBTnode genRedBlackBST() {
        System.out.println("Generating minimal black height Red-Black BST...");
        
        RBTnode root = new RBTnode(0, BLACK);
        java.util.Queue<RBTnode> queue = new java.util.LinkedList<>();
        java.util.Queue<Integer> levelQueue = new java.util.LinkedList<>();
        queue.offer(root);
        levelQueue.offer(0);
        
        int counter = 1;
        
        while (!queue.isEmpty() && counter < 1000000) {
            RBTnode current = queue.poll();
            int level = levelQueue.poll();
            
            if (counter < 1000000) {
                int childColor = ((level + 1) % 2 == 0) ? BLACK : RED;
                current.lchild = new RBTnode(counter++, childColor);
                queue.offer(current.lchild);
                levelQueue.offer(level + 1);
            }
            
            if (counter < 1000000) {
                int childColor = ((level + 1) % 2 == 0) ? BLACK : RED;
                current.rchild = new RBTnode(counter++, childColor);
                queue.offer(current.rchild);
                levelQueue.offer(level + 1);
            }
        }
        
        int actualBlackHeight = getBlackHeight(root);
        System.out.println("Actual black height achieved: " + actualBlackHeight);
        System.out.println("Total nodes: " + counter);
        
        double log2n = Math.log(1000000) / Math.log(2);
        System.out.println("Theoretical minimum black height: ~" + 
                           (int)Math.ceil(log2n / 2));
        
        return root;
    }
    
    public static RBTnode genRedBlackBST_v2() {
        System.out.println("\n=== Alternative Strategy: Level-by-Level Coloring ===");
        
        RBTnode root = new RBTnode(0, BLACK);
        java.util.Queue<RBTnode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        int counter = 1;
        
        while (!queue.isEmpty() && counter < 1000000) {
            RBTnode current = queue.poll();
            
            if (counter < 1000000) {
                current.lchild = new RBTnode(counter++, BLACK);
                queue.offer(current.lchild);
            }
            
            if (counter < 1000000) {
                current.rchild = new RBTnode(counter++, BLACK);
                queue.offer(current.rchild);
            }
        }
        
        colorForMinimalBlackHeight(root, 0);
        
        if (root != null) {
            root.color = BLACK;
        }
        
        int actualBlackHeight = getBlackHeight(root);
        System.out.println("Black height with level-coloring: " + actualBlackHeight);
        System.out.println("Total nodes: " + countNodes(root));
        
        return root;
    }
    
    private static void colorForMinimalBlackHeight(RBTnode node, int level) {
        if (node == null) return;
        
        if (level == 0) {
            node.color = BLACK;
        } else if (level % 2 == 1) {
            node.color = RED;
        } else {
            node.color = BLACK;
        }
        
        colorForMinimalBlackHeight(node.lchild, level + 1);
        colorForMinimalBlackHeight(node.rchild, level + 1);
    }
    
    private static int getBlackHeight(RBTnode node) {
        if (node == null) return 0;
        
        int leftBH = getBlackHeight(node.lchild);
        int blackIncrement = (node.color == BLACK) ? 1 : 0;
        
        return leftBH + blackIncrement;
    }
    
    private static int countNodes(RBTnode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.lchild) + countNodes(node.rchild);
    }
    
    private static int getHeight(RBTnode node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.lchild), getHeight(node.rchild));
    }
    
    private static boolean isBST(RBTnode node, int min, int max) {
        if (node == null) return true;
        if (node.key <= min || node.key >= max) return false;
        return isBST(node.lchild, min, node.key) && 
               isBST(node.rchild, node.key, max);
    }
    
    private static void countColors(RBTnode node, int[] counts) {
        if (node == null) return;
        if (node.color == RED) counts[0]++;
        else counts[1]++;
        countColors(node.lchild, counts);
        countColors(node.rchild, counts);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Testing isRBT() ===\n");
        
        RBTnode valid = new RBTnode(10, BLACK,
            new RBTnode(5, RED,
                new RBTnode(2, BLACK),
                new RBTnode(7, BLACK)),
            new RBTnode(15, RED,
                new RBTnode(12, BLACK),
                new RBTnode(20, BLACK))
        );
        System.out.println("Test 1 - Valid RBT: " + isRBT(valid));
        
        RBTnode invalidRoot = new RBTnode(10, RED,
            new RBTnode(5, BLACK),
            new RBTnode(15, BLACK)
        );
        System.out.println("Test 2 - Red root (invalid): " + isRBT(invalidRoot));
        
        RBTnode invalidReds = new RBTnode(10, BLACK,
            new RBTnode(5, RED,
                new RBTnode(2, RED),
                null),
            new RBTnode(15, BLACK)
        );
        System.out.println("Test 3 - Consecutive reds (invalid): " + isRBT(invalidReds));
        
        RBTnode invalidBH = new RBTnode(10, BLACK,
            new RBTnode(5, BLACK,
                new RBTnode(2, BLACK),
                null),
            new RBTnode(15, BLACK)
        );
        System.out.println("Test 4 - Unequal black heights (invalid): " + isRBT(invalidBH));
        
        RBTnode single = new RBTnode(42, BLACK);
        System.out.println("Test 5 - Single black node: " + isRBT(single));
        
        System.out.println("Test 6 - Null tree: " + isRBT(null));
        
        System.out.println("\n=== Testing genRedBlackBST() ===\n");
        
        long startTime = System.currentTimeMillis();
        RBTnode largeTree = genRedBlackBST();
        long endTime = System.currentTimeMillis();
        
        System.out.println("\nGeneration time: " + (endTime - startTime) + "ms");
        System.out.println("Is valid RBT: " + isRBT(largeTree));
        System.out.println("Is valid BST: " + isBST(largeTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Node count: " + countNodes(largeTree));
        System.out.println("Total height: " + getHeight(largeTree));
        
        int[] colorCounts = {0, 0};
        countColors(largeTree, colorCounts);
        System.out.println("Red nodes: " + colorCounts[0]);
        System.out.println("Black nodes: " + colorCounts[1]);
        
        startTime = System.currentTimeMillis();
        RBTnode largeTree2 = genRedBlackBST_v2();
        endTime = System.currentTimeMillis();
        
        System.out.println("\nAlternative generation time: " + (endTime - startTime) + "ms");
        System.out.println("Is valid RBT: " + isRBT(largeTree2));
        
        System.out.println("\n=== Theoretical Analysis ===");
        double log2n = Math.log(1000000) / Math.log(2);
        System.out.println("log2(1,000,000) ≈ " + String.format("%.2f", log2n));
        System.out.println("Perfect tree height: " + (int)Math.ceil(log2n));
        System.out.println("Minimal black height (theoretical): " + (int)Math.ceil(log2n / 2));
        System.out.println("\nExplanation: By alternating RED and BLACK levels in a complete");
        System.out.println("binary tree, we maximize nodes per black height level, achieving");
        System.out.println("minimal black height of approximately log2(n)/2 ≈ 10");
    }
}