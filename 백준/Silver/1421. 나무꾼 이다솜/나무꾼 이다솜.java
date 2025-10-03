import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, C, W;
    private static long[] lengths;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        lengths = new long[N];
        for (int i = 0; i < N; i++) {
            lengths[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int maxima = (int) Arrays.stream(lengths).max()
                .orElseThrow(RuntimeException::new);

        long[] DP = new long[maxima + 1];

        for (long length : lengths) {
            for (int truncate = 1; truncate <= length; truncate++) {
                DP[truncate] += getMaxProfitWith(length, truncate);
            }
        }

        long answer = Arrays.stream(DP).max()
                .orElseThrow(RuntimeException::new);

        System.out.println(answer);
    }

    private static long getMaxProfitWith(long length, long truncate) {

        if (truncate > length) {
            throw new IllegalArgumentException();
        }

        long result = 0;
        long cutCount = 1;
        while (length > truncate)   {

            result = Math.max(
                    result,
                    truncate * cutCount * W - cutCount * C
            );

            cutCount++;
            length -= truncate;
        }

        if (length == truncate) {
            result = Math.max(
                    result,
                    truncate * cutCount * W - (cutCount - 1) * C
            );
        }

        return result;
    }
}
