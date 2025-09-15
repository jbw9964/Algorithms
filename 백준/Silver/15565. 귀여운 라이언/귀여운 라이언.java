import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, K;
    private static int[] infos, prefixCount;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        infos = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        prefixCount = new int[N];
        prefixCount[0] = isLion(0) ? 1 : 0;
        for (int i = 1; i < N; i++) {
            prefixCount[i] = prefixCount[i - 1] + (isLion(i) ? 1 : 0);
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        if (prefixCount[N - 1] < K) {
            System.out.println(-1);
            return;
        }

        int left = K, right = N;
        while (left < right) {

            int mid = (left + right) >>> 1;

            int maxCount = inspectMaxCountForWindowSize(mid);

            if (maxCount >= K) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    private static int inspectMaxCountForWindowSize(int size) {

        if (size <= 0 || size > N) {
            throw new IllegalArgumentException();
        }

        int maxima = 0;

        for (int from = 0; from + size - 1 < N; from++) {
            int to = from + size - 1;

            int count = prefixCount[to] - prefixCount[from] + (isLion(from) ? 1 : 0);

            maxima = Math.max(maxima, count);
        }

        return maxima;
    }

    private static boolean isLion(int i) {
        return infos[i] == 1;
    }
}
