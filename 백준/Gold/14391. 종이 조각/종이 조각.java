import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[][] numbers;
    private static boolean[][] mask;
    private static final boolean VERTICAL = false;
    private static final boolean HORIZONTAL = !VERTICAL;
    private static int ANSWER;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mask = new boolean[N][M];
        numbers = new int[N][];
        for (int i = 0; i < N; i++) {
            numbers[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        ANSWER = Integer.MIN_VALUE;
    }

    public static void main(String[] args) throws IOException {
        init();

        solve(0);

        System.out.println(ANSWER);
    }

    private static void solve(int cnt) {
        if (cnt == N * M) {
            ANSWER = Math.max(ANSWER, inspectResult());
            return;
        }

        int r = cnt / M;
        int c = cnt % M;

        mask[r][c] = HORIZONTAL;
        solve(cnt + 1);

        mask[r][c] = VERTICAL;
        solve(cnt + 1);
    }

    static final BiPredicate<Integer, Integer> excludeVerticals
            = (r, c) -> mask[r][c] != HORIZONTAL;
    static final BiPredicate<Integer, Integer> excludeHorizontals
            = (r, c) -> mask[r][c] != VERTICAL;
    static final Function<int[], int[]> horizontalNext
            = (arr) -> new int[]{arr[0], arr[1] + 1};
    static final Function<int[], int[]> verticalNext
            = (arr) -> new int[]{arr[0] + 1, arr[1]};

    private static int inspectResult()  {

        int horizontal = inspectResult(excludeVerticals, horizontalNext);
        int vertical = inspectResult(excludeHorizontals, verticalNext);

        return horizontal + vertical;
    }

    private static int inspectResult(
            BiPredicate<Integer, Integer> exclude,
            Function<int[], int[]> next
    )  {
        int sum = 0;
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (exclude.test(i, j) || visited[i][j]) {
                    continue;
                }

                int value = 0;
                int[] curr = new int[]{i, j};
                while (curr[0] < N && curr[1] < M && !exclude.test(curr[0], curr[1])) {
                    value = 10 * value + numbers[curr[0]][curr[1]];
                    visited[curr[0]][curr[1]] = true;
                    curr = next.apply(curr);
                }
                sum += value;
            }
        }

        return sum;
    }
}