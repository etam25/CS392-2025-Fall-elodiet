import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnGtree.*;

// Sudoku representation: 9x9 grid with 0 representing empty cells
class Sudoku {
    private int[][] board;
    
    public Sudoku(int[][] board) {
        this.board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }
    
    public int[][] getBoard() {
        return board;
    }
    
    public int get(int row, int col) {
        return board[row][col];
    }
    
    public Sudoku set(int row, int col, int value) {
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                newBoard[i][j] = this.board[i][j];
            }
        }
        newBoard[row][col] = value;
        return new Sudoku(newBoard);
    }
    
    public boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }
    
    public boolean isValid(int row, int col, int num) {
        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) return false;
        }
        
        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) return false;
        }
        
        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        
        return true;
    }
    
    public int[] findNextEmpty() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // No empty cell found
    }
    
    public void print() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Tree node for Sudoku solving
class SudokuNode implements FnGtree<Sudoku> {
    private Sudoku puzzle;
    private FnList<FnGtree<Sudoku>> childrenCache = null;
    
    public SudokuNode(Sudoku puzzle) {
        this.puzzle = puzzle;
    }
    
    @Override
    public Sudoku value() {
        return puzzle;
    }
    
    @Override
    public FnList<FnGtree<Sudoku>> children() {
        if (childrenCache != null) return childrenCache;
        
        if (puzzle.isSolved()) {
            childrenCache = FnListSUtil.nil();
            return childrenCache;
        }
        
        int[] pos = puzzle.findNextEmpty();
        if (pos == null) {
            childrenCache = FnListSUtil.nil();
            return childrenCache;
        }
        
        int row = pos[0];
        int col = pos[1];
        
        FnList<FnGtree<Sudoku>> children = FnListSUtil.nil();
        
        // Try digits 1-9
        for (int num = 9; num >= 1; num--) {
            if (puzzle.isValid(row, col, num)) {
                Sudoku newPuzzle = puzzle.set(row, col, num);
                children = FnListSUtil.cons(new SudokuNode(newPuzzle), children);
            }
        }
        
        childrenCache = children;
        return children;
    }
}

public class Quiz02_03 {
    
    public LnStrm<Sudoku> Sudoku_dfs_solve(Sudoku puzzle) {
        SudokuNode root = new SudokuNode(puzzle);
        LnStrm<Sudoku> stream = FnGtreeSUtil.DFirstEnumerate(root);
        return stream.filter0(s -> s.isSolved());
    }
    
    public LnStrm<Sudoku> Sudoku_bfs_solve(Sudoku puzzle) {
        SudokuNode root = new SudokuNode(puzzle);
        LnStrm<Sudoku> stream = FnGtreeSUtil.BFirstEnumerate(root);
        return stream.filter0(s -> s.isSolved());
    }
    
    public static void main(String[] args) {
        Quiz02_03 solver = new Quiz02_03();
        
        // Hard Sudoku puzzle from sudoku.com
        int[][] hardPuzzle = {
            {0, 0, 0, 6, 0, 0, 4, 0, 0},
            {7, 0, 0, 0, 0, 3, 6, 0, 0},
            {0, 0, 0, 0, 9, 1, 0, 8, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 5, 0, 1, 8, 0, 0, 0, 3},
            {0, 0, 0, 3, 0, 6, 0, 4, 5},
            {0, 4, 0, 2, 0, 0, 0, 6, 0},
            {9, 0, 3, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 1, 0, 0}
        };
        
        Sudoku puzzle = new Sudoku(hardPuzzle);
        
        System.out.println("Original Puzzle:");
        puzzle.print();
        System.out.println();
        
        // Test DFS solver
        System.out.println("=== Testing DFS Solver ===");
        long startTime = System.currentTimeMillis();
        LnStrm<Sudoku> dfsStream = solver.Sudoku_dfs_solve(puzzle);
        LnStcn<Sudoku> dfsSolution = dfsStream.eval0();
        
        if (dfsSolution.consq()) {
            long endTime = System.currentTimeMillis();
            System.out.println("DFS Solution found in " + (endTime - startTime) + "ms:");
            dfsSolution.hd().print();
        } else {
            System.out.println("No solution found with DFS");
        }
        
        System.out.println();
        
        // Test BFS solver
        System.out.println("=== Testing BFS Solver ===");
        startTime = System.currentTimeMillis();
        LnStrm<Sudoku> bfsStream = solver.Sudoku_bfs_solve(puzzle);
        LnStcn<Sudoku> bfsSolution = bfsStream.eval0();
        
        if (bfsSolution.consq()) {
            long endTime = System.currentTimeMillis();
            System.out.println("BFS Solution found in " + (endTime - startTime) + "ms:");
            bfsSolution.hd().print();
        } else {
            System.out.println("No solution found with BFS");
        }
    }
}