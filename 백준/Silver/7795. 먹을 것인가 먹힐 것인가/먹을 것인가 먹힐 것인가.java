import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] aNumbers, bNumbers;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        aNumbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        bNumbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {
            init();

            answer.append(solve()).append('\n');
        }

        System.out.print(answer.toString());
    }

    private static long solve() {

        long cnt = 0;

        for (int num : aNumbers) {
            int lowerCnt = binSearch(num, bNumbers);
            cnt += lowerCnt;
        }

        return cnt;
    }

    private static int binSearch(int threshold, int[] arr) {

        int len = arr.length;

        if (threshold <= arr[0])    {
            return 0;
        }
        if (threshold > arr[len - 1])    {
            return len;
        }

        int left = 0;
        int right = len - 1;
        while (left < right) {

            int mid = (left + right) >>> 1;

            if (arr[mid] < threshold) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }
}
