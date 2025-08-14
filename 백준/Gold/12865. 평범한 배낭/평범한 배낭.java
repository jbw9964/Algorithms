import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, K;
    private static int[] weights, values;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weights = new int[N];
        values = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int[] DP = new int[K + 1];

        for (int i = 0; i < N; i++) {
            int currentWeight = weights[i];
            int currentValue = values[i];

            for (int weight = K; weight >= currentWeight; weight--) {
                DP[weight] = Math.max(
                        DP[weight],
                        DP[weight - currentWeight] + currentValue
                );
            }
        }

        System.out.println(DP[K]);
    }
}
