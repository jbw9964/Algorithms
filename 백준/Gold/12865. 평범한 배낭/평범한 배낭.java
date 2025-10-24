import java.io.*;
import java.util.*;

class Main {

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

            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            weights[i] = weight;
            values[i] = value;
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int[] DP = new int[K + 1];

        for (int i = 0; i < N; i++) {

            int baseWeight = weights[i];
            int baseValue = values[i];

            for (int weightSum = K; weightSum >= baseWeight; weightSum--) {
                DP[weightSum] = Math.max(
                        DP[weightSum],
                        DP[weightSum - baseWeight] + baseValue
                );
            }
        }

        System.out.println(DP[K]);
    }
}
