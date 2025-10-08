import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

    }

    public static void main(String[] args) throws IOException {

        init();

        int[] DP = new int[N + 1];
        Arrays.fill(DP, Integer.MAX_VALUE);
        DP[0] = 0;
        DP[1] = 1;

        for (int curr = 2; curr <= N; curr++) {

            int sqrt = 1;
            int prev;

            while ((prev = curr - sqrt * sqrt) >= 0) {
                DP[curr] = Math.min(DP[curr], DP[prev] + 1);
                sqrt++;
            }

        }

        System.out.println(DP[N]);
    }
}
