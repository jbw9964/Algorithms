import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int[] bytes, costs;
    private static int N, M, MAX;

    private static void init() throws IOException {
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        bytes = new int[N];
        costs = new int[N];
        for (int i = 0; i < N; i++) {
            bytes[i] = Integer.parseInt(st1.nextToken());
            costs[i] = Integer.parseInt(st2.nextToken());
        }

        MAX = Arrays.stream(costs).sum();
    }

    public static void main(String[] args) throws IOException {
        init();

        int[] dp = new int[MAX + 1];

        for (int i = 0; i < N; i++) {
            int currByte = bytes[i];
            int currCost = costs[i];

            for (int j = MAX; j >= currCost; j--) {
                int prev = dp[j - currCost];
                dp[j] = Math.max(dp[j], prev + currByte);
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
