import java.util.*;

class Solution {

    private static final int MOD = 10_007;

    public int solution(int n, int[] tops) {

        int[][] DP = new int[2][n];

        // bottom = n + 1 case
        int[] withBottomCase = DP[0];

        // bottom = n case
        int[] withoutBottomCase = DP[1];

        withBottomCase[0] = 3 + tops[0];
        withoutBottomCase[0] = 2 + tops[0];

        for (int i = 1; i < n; i++) {
            withBottomCase[i] = (2 + tops[i]) * withBottomCase[i - 1] + withoutBottomCase[i - 1];
            withoutBottomCase[i] = (1 + tops[i]) * withBottomCase[i - 1] + withoutBottomCase[i - 1];

            withBottomCase[i] %= MOD;
            withoutBottomCase[i] %= MOD;
        }

        return withBottomCase[n - 1];
    }
}
