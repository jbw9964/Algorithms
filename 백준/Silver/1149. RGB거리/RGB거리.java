import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][3];

        StringTokenizer st = new StringTokenizer(br.readLine());

        dp[0][0] = Integer.parseInt(st.nextToken());
        dp[0][1] = Integer.parseInt(st.nextToken());
        dp[0][2] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            int costR = Integer.parseInt(st.nextToken());
            int costG = Integer.parseInt(st.nextToken());
            int costB = Integer.parseInt(st.nextToken());

            dp[i][0] = costR + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costG + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costB + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        int ans = Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2]));

        System.out.println(ans);
    }
}
