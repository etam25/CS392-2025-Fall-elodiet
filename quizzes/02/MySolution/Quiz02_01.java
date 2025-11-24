import Library.FnList.*;
import Library.FnA1sz.*;

public class Quiz02_01 {

    public static <T extends Comparable<T>> FnList<Integer> FnA1szLongestMonoSubsequence(FnA1sz<T> xs) {
        int n = xs.length();
        if (n == 0) return FnListSUtil.nil();

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) dp[i] = 1;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (xs.getAt(j).compareTo(xs.getAt(i)) >= 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLen = 0;
        int startIdx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                startIdx = i;
            }
        }

        FnList<Integer> result = FnListSUtil.nil();
        int currIdx = startIdx;
        int remaining = maxLen - 1;
        T currVal = xs.getAt(startIdx);
        result = FnListSUtil.cons(startIdx, result);

        while (remaining > 0) {
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
        FnList<Integer> testList = FnListSUtil.nil();
        int[] testArr = {1, 2, 1, 2, 3, 1, 2, 3, 4};
        for (int i = testArr.length - 1; i >= 0; i--) {
            testList = FnListSUtil.cons(testArr[i], testList);
        }
        FnA1sz<Integer> xs = new FnA1sz<>(testList);

        System.out.print("Input: ");
        xs.System$out$print();
        System.out.println();

        FnList<Integer> result = FnA1szLongestMonoSubsequence(xs);
        System.out.print("Result indices: ");
        FnListSUtil.System$out$print(result);
        System.out.println();

        System.out.print("Subsequence values: [");
        final FnA1sz<Integer> finalXs = xs;

        result.iforitm((i, indexInArray) -> {
            if (i > 0) System.out.print(",");
            System.out.print(finalXs.getAt(indexInArray));
        });

        System.out.println("]");
    }
}
