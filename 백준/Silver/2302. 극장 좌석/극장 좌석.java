import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static final int[] dp = new int[41];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        initDP();

        int ans = 1, prev = 0;
        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());

            ans *= dp[vip - prev - 1];
            prev = vip;
        }

        ans *= dp[N - prev];

        System.out.println(ans);
    }

    private static void initDP()    {
        dp[0] = dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
    }
}
