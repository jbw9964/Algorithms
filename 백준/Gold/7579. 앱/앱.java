import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final int MAX = 100 * 100;

    public static void main(String[] args) throws IOException {
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());

        st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int[] dp = new int[MAX + 1];

        for (int i = 0; i < N; i++) {
            int bytes = Integer.parseInt(st1.nextToken());
            int cost = Integer.parseInt(st2.nextToken());

            for (int j = MAX; j >= cost; j--) {
                int prev = dp[j - cost];
                dp[j] = Math.max(dp[j], prev + bytes);
            }
        }

        for (int minCost = 0; minCost <= MAX; minCost++) {
            if (dp[minCost] >= M) {
                System.out.println(minCost);
                System.exit(0);
            }
        }

        throw new RuntimeException();
    }
}
