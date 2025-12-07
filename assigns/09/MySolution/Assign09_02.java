import Library.FnList.*;
import Library.LnList.*;
import Library.FnTuple.*;

public class Assign09_02 {
    
    // Knight move offsets (8 possible moves)
    private static final int[][] KNIGHT_MOVES = {
        {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
        {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };
    
    // HX-2025-12-02:
    // Please use Warnsdorf's rule to
    // search for knight's tours on a chess board
    // of dimension (chessBoardSize x chessBoardSize)
    public static
    FnList<FnList<FnTupl2<Integer, Integer>>>
    genKnightsTours(int chessBoardSize) {
        final int boardSize = chessBoardSize;
        final int totalSquares = boardSize * boardSize;
        
        FnList<FnList<FnTupl2<Integer, Integer>>> allTours = FnListSUtil.nil();
        
        // Try each starting position until we find some tours
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                FnTupl2<Integer, Integer> start = new FnTupl2<>(row, col);
                boolean[][] visited = new boolean[boardSize][boardSize];
                visited[row][col] = true;
                FnList<FnTupl2<Integer, Integer>> path = FnListSUtil.sing(start);
                
                // Try to find ONE tour from this starting position
                FnList<FnTupl2<Integer, Integer>> tour = 
                    searchOneTour(path, visited, boardSize, totalSquares);
                
                // If we found a tour, add it to results
                if (!FnListSUtil.nilq(tour)) {
                    allTours = FnListSUtil.cons(tour, allTours);
                }
            }
        }
        
        return allTours;
    }
    
    // Search for ONE tour using Warnsdorf's rule (greedy approach)
    private static
    FnList<FnTupl2<Integer, Integer>>
    searchOneTour(
        FnList<FnTupl2<Integer, Integer>> path,
        boolean[][] visited,
        int boardSize,
        int totalSquares
    ) {
        // Base case: found a complete tour
        if (path.length() == totalSquares) {
            return path;
        }
        
        // Get current position (head of path)
        FnTupl2<Integer, Integer> current = path.hd();
        
        // Get valid next moves sorted by Warnsdorf's rule
        FnList<FnTupl2<Integer, Integer>> validMoves = 
            getValidMovesSorted(current, visited, boardSize);
        
        // If no valid moves, this path is a dead end
        if (FnListSUtil.nilq(validMoves)) {
            return FnListSUtil.nil();
        }
        
        // Try each valid move until we find a solution
        while (!FnListSUtil.nilq(validMoves)) {
            FnTupl2<Integer, Integer> nextPos = validMoves.hd();
            
            // Make a copy of visited array
            boolean[][] newVisited = copyVisited(visited, boardSize);
            newVisited[nextPos.sub0][nextPos.sub1] = true;
            
            // Add move to path
            FnList<FnTupl2<Integer, Integer>> newPath = 
                FnListSUtil.cons(nextPos, path);
            
            // Recursively search from new position
            FnList<FnTupl2<Integer, Integer>> result = 
                searchOneTour(newPath, newVisited, boardSize, totalSquares);
            
            // If we found a complete tour, return it
            if (!FnListSUtil.nilq(result) && result.length() == totalSquares) {
                return result;
            }
            
            // Otherwise try the next move
            validMoves = validMoves.tl();
        }
        
        // No tour found from this position
        return FnListSUtil.nil();
    }
    
    // Get valid moves sorted by Warnsdorf's rule (fewest onward moves first)
    private static
    FnList<FnTupl2<Integer, Integer>>
    getValidMovesSorted(
        FnTupl2<Integer, Integer> pos,
        boolean[][] visited,
        int boardSize
    ) {
        // Get all valid moves with their accessibility counts
        FnList<FnTupl3<Integer, Integer, Integer>> movesWithCounts = 
            FnListSUtil.nil();
        
        for (int[] move : KNIGHT_MOVES) {
            int newRow = pos.sub0 + move[0];
            int newCol = pos.sub1 + move[1];
            
            if (isValid(newRow, newCol, visited, boardSize)) {
                int accessibility = countAccessibility(
                    new FnTupl2<>(newRow, newCol), visited, boardSize
                );
                movesWithCounts = FnListSUtil.cons(
                    new FnTupl3<>(accessibility, newRow, newCol),
                    movesWithCounts
                );
            }
        }
        
        // Sort by accessibility (Warnsdorf's rule: fewest moves first)
        FnList<FnTupl3<Integer, Integer, Integer>> sorted = 
            FnListSUtil.mergeSort(movesWithCounts, (a, b) -> {
                // First compare by accessibility count
                int cmp = Integer.compare(a.sub0, b.sub0);
                if (cmp != 0) return cmp;
                // Tie-break by position for determinism
                cmp = Integer.compare(a.sub1, b.sub1);
                if (cmp != 0) return cmp;
                return Integer.compare(a.sub2, b.sub2);
            });
        
        // Extract just the positions
        return FnListSUtil.map_list(sorted, 
            t -> new FnTupl2<>(t.sub1, t.sub2)
        );
    }
    
    // Count how many valid moves are available from a position
    private static
    int countAccessibility(
        FnTupl2<Integer, Integer> pos,
        boolean[][] visited,
        int boardSize
    ) {
        int count = 0;
        for (int[] move : KNIGHT_MOVES) {
            int newRow = pos.sub0 + move[0];
            int newCol = pos.sub1 + move[1];
            if (isValid(newRow, newCol, visited, boardSize)) {
                count++;
            }
        }
        return count;
    }
    
    // Check if a position is valid and unvisited
    private static
    boolean isValid(int row, int col, boolean[][] visited, int boardSize) {
        return row >= 0 && row < boardSize &&
               col >= 0 && col < boardSize &&
               !visited[row][col];
    }
    
    // Copy visited array
    private static
    boolean[][] copyVisited(boolean[][] visited, int boardSize) {
        boolean[][] copy = new boolean[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            System.arraycopy(visited[i], 0, copy[i], 0, boardSize);
        }
        return copy;
    }
    
    // Testing code
    public static void main(String[] args) {
        System.out.println("Testing Knight's Tours with Warnsdorf's Rule\n");
        
        // Test 5x5 board (easier to find)
        System.out.println("=== Testing 5x5 board ===");
        testBoard(5);
        
        // Test 6x6 board
        System.out.println("\n=== Testing 6x6 board ===");
        testBoard(6);
        
        // Test 8x8 board (standard chess board)
        System.out.println("\n=== Testing 8x8 board ===");
        testBoard(8);
        
        // Bonus: larger boards
        System.out.println("\n=== Testing 10x10 board ===");
        testBoard(10);
    }
    
    private static void testBoard(int size) {
        System.out.println("Board size: " + size + "x" + size);
        System.out.println("Total squares: " + (size * size));
        
        long startTime = System.currentTimeMillis();
        
        FnList<FnList<FnTupl2<Integer, Integer>>> tours = 
            genKnightsTours(size);
        
        long endTime = System.currentTimeMillis();
        
        int count = 0;
        FnList<FnList<FnTupl2<Integer, Integer>>> current = tours;
        
        while (!FnListSUtil.nilq(current)) {
            count++;
            FnList<FnTupl2<Integer, Integer>> tour = current.hd();
            
            if (count <= 3) { // Only print details for first 3 tours
                System.out.println("\nTour #" + count + ":");
                System.out.println("Length: " + tour.length());
                
                // Print first few moves
                System.out.print("First 10 moves: ");
                FnList<FnTupl2<Integer, Integer>> reversed = tour.reverse();
                int printed = 0;
                while (!FnListSUtil.nilq(reversed) && printed < 10) {
                    FnTupl2<Integer, Integer> pos = reversed.hd();
                    System.out.print("(" + pos.sub0 + "," + pos.sub1 + ") ");
                    reversed = reversed.tl();
                    printed++;
                }
                System.out.println();
                
                // Verify it's a valid tour
                if (isValidTour(tour, size)) {
                    System.out.println("Valid tour!");
                } else {
                    System.out.println("Invalid tour!");
                }
            }
            
            current = current.tl();
        }
        
        System.out.println("\nTime: " + (endTime - startTime) + "ms");
        System.out.println("Total tours found: " + count);
    }
    
    // Verify that a tour is valid
    private static
    boolean isValidTour(FnList<FnTupl2<Integer, Integer>> tour, int boardSize) {
        // Check length
        if (tour.length() != boardSize * boardSize) {
            return false;
        }
        
        // Check all moves are valid knight moves
        FnList<FnTupl2<Integer, Integer>> reversed = tour.reverse();
        FnTupl2<Integer, Integer> prev = reversed.hd();
        reversed = reversed.tl();
        
        while (!FnListSUtil.nilq(reversed)) {
            FnTupl2<Integer, Integer> curr = reversed.hd();
            
            int rowDiff = Math.abs(curr.sub0 - prev.sub0);
            int colDiff = Math.abs(curr.sub1 - prev.sub1);
            
            // Valid knight move: (2,1) or (1,2)
            if (!((rowDiff == 2 && colDiff == 1) || 
                  (rowDiff == 1 && colDiff == 2))) {
                return false;
            }
            
            prev = curr;
            reversed = reversed.tl();
        }
        
        // Check no position is repeated
        boolean[][] visited = new boolean[boardSize][boardSize];
        FnList<FnTupl2<Integer, Integer>> path = tour;
        while (!FnListSUtil.nilq(path)) {
            FnTupl2<Integer, Integer> pos = path.hd();
            if (visited[pos.sub0][pos.sub1]) {
                return false;
            }
            visited[pos.sub0][pos.sub1] = true;
            path = path.tl();
        }
        
        return true;
    }
}