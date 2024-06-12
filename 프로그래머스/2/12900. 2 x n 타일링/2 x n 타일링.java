
class Solution {
    private static final long MOD = 1_000_000_007;

    public int solution(int n) {
        long[] countTable = new long[n + 1];
        countTable[1] = 1;
        countTable[2] = 2;

        for (int i = 3; i <= n; i++)
        countTable[i] = (countTable[i - 1] + countTable[i - 2]) % MOD;

        return (int) countTable[n];
    }
}