import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, K;
    private static int[] prefixSum;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        prefixSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            prefixSum[i + 1] = prefixSum[i] + numbers[i];
        }
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {

            init();

            int count = 0;

            if (N == M) {

                if (getInRangeSum(0, N) < K) {
                    count++;
                }

            } else {
                for (int from = 0; from < N; from++) {
                    int sum = getInRangeSum(from, M);

                    if (sum < K) {
                        count++;
                    }
                }
            }

            answer.append(count).append("\n");
        }

        System.out.print(answer.toString());
    }

    private static int getInRangeSum(int from, int count) {

        int sum = 0;

        if (from + count > N) {
            sum += prefixSum[N] - prefixSum[from];
            count = from + count - N;
            from = 0;
        }

        sum += prefixSum[from + count] - prefixSum[from];

        return sum;
    }
}
