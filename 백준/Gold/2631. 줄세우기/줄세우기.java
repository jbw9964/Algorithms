import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] numbers;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        int[] DP = new int[N];

        for (int i = 0; i < N; i++) {

            int pivot = numbers[i];
            DP[i] = 1;

            for (int j = 0; j < i; j++) {

                int compare = numbers[j];

                if (compare < pivot) {
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                }

            }
        }

        int lcsLength = Arrays.stream(DP).max()
                .orElseThrow(RuntimeException::new);

        System.out.println(N - lcsLength);
    }
}
