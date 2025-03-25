import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] dp = new int[N];
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int curr = numbers[i];
            dp[i] = curr;

            for (int j = 0; j < i; j++) {
                int prev = numbers[j];

                if (curr <= prev)    {
                    continue;
                }

                dp[i] = Math.max(dp[i], dp[j] + curr);
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }

    private static void showArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
