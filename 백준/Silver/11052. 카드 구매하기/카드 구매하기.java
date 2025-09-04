import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] costs;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        costs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

    }

    public static void main(String[] args) throws IOException {

        init();

        int[] DP = new int[N + 1];

        for (int pivot = 1; pivot <= N; pivot++) {
            DP[pivot] = costs[pivot - 1];

            for (int i = 1; i <= pivot / 2; i++) {
                int check = DP[i] + DP[pivot - i];
                DP[pivot] = Math.max(check, DP[pivot]);
            }
        }

        System.out.println(DP[N]);
    }
}
