
class Solution {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        int[][] DP = new int[n][n];

        for (int i = n - 2; i >= 0; i--)    {
            DP[i][i + 1] = calc(matrix_sizes[i][0], matrix_sizes[i][1], matrix_sizes[i + 1][1]);

            for (int j = i + 2; j < n; j++) {
                int minima = Integer.MAX_VALUE;

                for (int k = i; k < j; k++)
                minima = Math.min(
                    minima, 
                    calc(matrix_sizes[i][0], matrix_sizes[k][1], matrix_sizes[j][1]) 
                    + DP[i][k] + DP[k + 1][j]
                );

                DP[i][j] = minima;
            }
        }

        return DP[0][n - 1];
    }

    private static int calc(int n, int m, int k)    {
        return n * m * k;
    }
}