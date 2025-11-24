package MySolution;
import Library.FnList.*;
import Library.FnA1sz.*;

public class Quiz02_01 {
    public static <T extends Comparable<T>> FnList<Integer> FnA1szLongestMonoSubsequence(FnA1sz<T> xs) {
        int n = xs.length();
        if (n == 0) return FnListSUtil.nil();

        // dp[i] is the length of longest ascending subsequence starting at the index i
        int[] dp = new int[n];

        // base case: each element alone forms sequence of length 1
        for (int i = 0; i < n; i++)  dp[i] = 1;

        // fill dp array from right to left
        // for each position i, look at all j > i where xs[j] >= xs[i]
        // this is O(n^2)
        for (int i = n - 2; i >= 0; i++) {
            for (int j = i + 1; i < n; j++) {
                if (xs.getAt(j).compareTo(xs.getAt(i)) >= 0) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }

        // find the max length and leftmost starting index
        int maxLen = 0;
        int startIdx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                startIdx = i;
            }
        }
        
        // reconstruct leftmost subsequence
        // go left to right, always pick up earliest valid next index
        FnList<Integer> result = FnListSUtil.nil();
        int currIdx = startIdx;
        int remaining = maxLen;
        T currVal = xs.getAt(startIdx);
        result = FnListSUtil.cons(startIdx, result);
        remaining--;

        while (remaining > 0) {
            // find leftmost j ? currIdx where:
            // 1. xs[j] >= currVal (ascending)
            // 2. dp[j] == remaining (can complete sequence)
            for (int j = currIdx + 1; j < n; j++) {
                if (xs.getAt(j).compareTo(currVal) >= 0 && dp[j] == remaining) {
                    result = FnListSUtil.cons(j, result);
                    currIdx = j;
                    currVal = xs.getAt(j);
                    remaining--;
                    break;
                }
            }
        }

        return FnListSUtil.reverse(result);
    }

    public static void main(String[] args) {
        // test case from problem: [1, 2, 1, 2, 3, 1, 2, 3, 4]
        // expected: (0, 1, 2, 3, 4, 7, 8) -> [1, 2, 2, 3, 3, 4]
        FnList<Integer> testList = FnListSUtil.nil();
        int[] testArr = {1, 2, 1, 2, 3, 1, 2, 3, 4};
        for (int i = testArr.length - 1; i >= 0; i--) {
            testList = FnListSUtil.cons(testArr[i], testList);
        }
        FnA1sz<Integer> xs = new FnA1sz<Integer>(testList);

        System.out.print("Input: ");
        xs.System$out$print();
        System.out.println();

        FnList<Integer> result = FnA1szLongestMonoSubsequence(xs);
        System.out.print("Result indices: ");
        FnListSUtil.System$out$print(result);
        System.out.println();

        // print actual subsequence values
        System.out.print("Subsequence values: [");
        final FnA1sz<Integer> finalXs = xs;
        result.iforitm((i, idx) -> {
            if (i > 0) System.out.print(",");
            System.out.print(finalXs.getAt(idx));
        });
        System.out.println("]");
        }
}