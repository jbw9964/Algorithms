import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        int[] times = new int[n + 1];
        int[] costs = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            times[i] = Integer.parseInt(st.nextToken());
            costs[i] = Integer.parseInt(st.nextToken());
        }

        // System.out.println();

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {

            // init
            int cost = costs[i];
            int time = times[i];
            for (int j = i + time - 1; j <= n; j++) {
                dp[i][j] = cost;
            }

            // init upper columns [j, i]
            for (int j = i - 1; j >= 1; j--) {

                int maxima = dp[j][i];

                // [j, k]
                for (int k = j; k < i; k++) {

                    int costFront = dp[j][k];
                    int costRear = dp[k + 1][i];

                    maxima = Math.max(maxima, costFront + costRear);
                }

                dp[j][i] = maxima;
            }

            // showArr(dp);
        }

        System.out.println(dp[1][n]);
    }

    private static void showArr(int[][] arr) {
        Arrays.stream(arr).forEach(r -> System.out.println(Arrays.toString(r)));
        System.out.println();
    }
}