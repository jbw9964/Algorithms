import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final int MAX = 100 * 100 + 1;

    public static void main(String[] args) throws IOException {

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());

        int[][] dp = new int[N + 1][MAX];
        st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {

            int givenBytes = Integer.parseInt(st1.nextToken());
            int givenCost = Integer.parseInt(st2.nextToken());

            int[] prev = dp[i - 1];
            int[] curr = dp[i];

            assert givenCost >= 0;

            System.arraycopy(prev, 0, curr, 0, givenCost);

            for (int j = givenCost; j < MAX; j++) {
                curr[j] = Math.max(prev[j], prev[j - givenCost] + givenBytes);
            }
        }

        for (int minCost = 0; minCost < MAX; minCost++) {
            if (dp[N][minCost] >= M) {
                System.out.println(minCost);
                System.exit(0);
            }
        }

        throw new RuntimeException();
    }
}
