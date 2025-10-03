import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static long C;
    private static long[] weights;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        weights = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        {   // single case
            int i = Arrays.binarySearch(weights, C);
            if (i >= 0) {
                System.out.println(1);
                return;
            }
        }

        {   // double case
            for (int pivot = 0; pivot < N - 1; pivot++) {
                long pivotVal = weights[pivot];

                if (pivotVal >= C)  {
                    break;
                }

                int i = Arrays.binarySearch(weights, pivot + 1, N, C - pivotVal);
                if (i >= 0) {
                    System.out.println(1);
                    return;
                }
            }
        }

        // triple case
        for (int head = 0; head < N - 2; head++) {
            for (int tail = N - 1; tail > head; tail--) {

                long baseVal = weights[head] + weights[tail];
                if (baseVal >= C)   {
                    continue;
                }

                int i = Arrays.binarySearch(weights, head + 1, tail, C - baseVal);
                if (i >= 0) {
                    System.out.println(1);
                    return;
                }
            }
        }

        System.out.println(0);
    }
}
