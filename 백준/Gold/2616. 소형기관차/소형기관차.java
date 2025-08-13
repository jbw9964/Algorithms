import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] passengers;
    private static int len;
    private static int[] prefixSum;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        passengers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        len = Integer.parseInt(br.readLine());

        prefixSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {

            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += passengers[j];
            }

            prefixSum[i] = sum;
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int[][] DP = new int[3 + 1][N + 1];

        for (int r = 1; r <= 3; r++) {

            for (int c = len * r; c <= N; c++) {
                DP[r][c] = Math.max(
                        DP[r][c - 1],
                        DP[r - 1][c - len] + prefixSum[c] - prefixSum[c - len]
                );
            }

        }

        System.out.println(DP[3][N]);
    }

}
