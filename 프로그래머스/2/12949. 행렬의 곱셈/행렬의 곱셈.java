
class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int N = arr1.length;
        int M = arr2.length;
        int K = arr2[0].length;

        int[][] answer = new int[N][K];

        for (int i = 0; i < N; i++)
        for (int j = 0; j < K; j++) {
            int sum = 0;

            for (int k = 0; k < M; k++)
            sum += arr1[i][k] * arr2[k][j];

            answer[i][j] = sum;
        }

        return answer;
    }
}