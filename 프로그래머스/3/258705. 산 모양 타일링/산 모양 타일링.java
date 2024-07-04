
class Solution {
    private static final int MOD = 10_007;

    public int solution(int n, int[] tops) {
        int[][] table = new int[2][n];
        table[0][0] = 3 + tops[0];
        table[1][0] = 2 + tops[0];

        for (int i = 1; i < n; i++) {
            table[1][i] = table[1][i - 1] + table[0][i - 1] * (1 + tops[i]);
            table[0][i] = table[0][i - 1] * (1 + tops[i]) + table[1][i - 1] + table[0][i - 1];

            table[1][i] %= MOD;
            table[0][i] %= MOD;
        }

        return table[0][n - 1];
    }
}