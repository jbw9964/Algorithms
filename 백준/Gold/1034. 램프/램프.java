import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, K;
    private static Map<Long, Integer> bitMap;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bitMap = new HashMap<>(M);
        for (int i = 0; i < N; i++) {
            long bit = Long.parseLong(br.readLine(), 2);
            bitMap.put(bit, bitMap.getOrDefault(bit, 0) + 1);
        }

        K = Integer.parseInt(br.readLine());
    }


    public static void main(String[] args) throws IOException {

        init();

        int maxima = 0;

        for (long bit : bitMap.keySet()) {
            int zeros = countZeros(bit);
            if (zeros <= K && (zeros & 0b1L) == (K & 0b1L)) {
                maxima = Math.max(maxima, bitMap.get(bit));
            }
        }

        System.out.println(maxima);
    }

    private static int countZeros(long bit) {

        int count = 0;
        for (int i = 0; i < M; i++) {
            if ((bit & 0b1L) == 0) {
                count++;
            }
            bit >>>= 1;
        }

        return count;
    }
}
