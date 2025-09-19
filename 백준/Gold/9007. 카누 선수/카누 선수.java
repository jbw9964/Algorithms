import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int K, N;
    private static int[] arr1, arr2;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[] read1 = readArr();
        int[] read2 = readArr();
        int[] read3 = readArr();
        int[] read4 = readArr();

        arr1 = gen2ptArrFrom(read1, read2);
        arr2 = gen2ptArrFrom(read3, read4);

        if (arr1.length != arr2.length || arr1.length != N * N) {
            throw new RuntimeException();
        }
    }

    private static int[] readArr() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[] gen2ptArrFrom(int[] arr1, int[] arr2) {

        int len1 = arr1.length, len2 = arr2.length;

        int[] arr = new int[len1 * len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                arr[i * len1 + j] = arr1[i] + arr2[j];
            }
        }

        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {

            init();

            int lPtr = 0;
            int rPtr = arr2.length - 1;

            int optimal = Integer.MAX_VALUE;
            int minDiff = Integer.MAX_VALUE;

            while (true) {

                int sum = arr1[lPtr] + arr2[rPtr];
                int diff = Math.abs(K - sum);

                if (diff <= minDiff) {
                    optimal = diff == minDiff ? Math.min(optimal, sum) : sum;
                    minDiff = diff;
                }

                if (sum == K) {
                    break;
                }

                if (sum < K) {
                    if (lPtr++ == arr1.length - 1) {
                        break;
                    }
                } else {
                    if (rPtr-- == 0) {
                        break;
                    }
                }
            }

            answer.append(optimal).append("\n");
        }

        System.out.println(answer.toString());
    }
}
