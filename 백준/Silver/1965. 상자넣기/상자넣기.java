import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] boxes;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        boxes = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

    }

    public static void main(String[] args) throws IOException {

        init();

        int MAXIMA = Integer.MIN_VALUE;
        int[] DP = new int[N];

        for (int i = 1; i < N; i++) {

            int current = boxes[i];

            for (int j = 0; j < i; j++) {

                int trial = boxes[j];

                if (trial >= current) {
                    continue;
                }

                DP[i] = Math.max(DP[i], DP[j] + 1);
            }

            MAXIMA = Math.max(MAXIMA, DP[i]);
        }

        System.out.println(MAXIMA + 1);
    }
}
