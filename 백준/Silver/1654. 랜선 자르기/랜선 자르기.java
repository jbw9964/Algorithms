import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int K, N;
    private static int[] lengths;
    private static int MAX_LENGTH;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lengths = new int[K];
        for (int i = 0; i < K; i++) {
            lengths[i] = Integer.parseInt(br.readLine());
            MAX_LENGTH = Math.max(lengths[i], MAX_LENGTH);
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int left = 1, right = MAX_LENGTH;

        while (left < right - 1)    {
            int mid = (left + right) >>> 1;
            int numOfChunks = getSumOfChunksWith(mid);

            if (numOfChunks >= N)   {
                left = mid;
            }
            else {
                right = mid;
            }
        }

        int answer = getSumOfChunksWith(right) >= N ? right : left;

        System.out.println(answer);
    }

    private static int getSumOfChunksWith(int thresholdLength)  {
        int sum = 0;

        for (int length : lengths) {
            sum += length / thresholdLength;
        }

        return sum;
    }
}
