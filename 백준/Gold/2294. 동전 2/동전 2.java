import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, K;
    private static Set<Integer> coins;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new HashSet<>();
        for (int i = 0; i < N; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int[] DP = new int[K + 1];
        Arrays.fill(DP, Integer.MAX_VALUE);
        DP[K] = 0;

        for (int sum = K; sum > 0; sum--) {

            int currentCount = DP[sum];

            if (currentCount == Integer.MAX_VALUE) {
                continue;
            }

            for (int coin : coins) {

                int target = sum - coin;

                if (target < 0) {
                    continue;
                }

                DP[target] = Math.min(DP[target], currentCount + 1);
            }
        }

        System.out.println(DP[0] == Integer.MAX_VALUE ? -1 : DP[0]);
    }
}
