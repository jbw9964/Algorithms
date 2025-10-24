import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;
    private static int[][] table;

    private static int ANSWER;

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        table = new int[R][];
        for (int i = 0; i < R; i++) {
            table[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        dfs(0, 0, new boolean[R][C]);

        System.out.println(ANSWER);
    }

    private static void dfs(int idx, int sum, boolean[][] visited) {

        int r = idx / C;
        int c = idx % C;

        if (!inRange(r, c)) {
            ANSWER = Math.max(ANSWER, sum);
            return;
        }

        if (visited[r][c])  {
            dfs(idx + 1, sum, visited);
            return;
        }

        // dfs for horizontal
        LOOP_H:
        for (int size = 2; size <= C; size++) {
            int threshold = c + size - 1;

            for (int cc = c; cc <= threshold; cc++) {
                if (!inRange(r, cc) || visited[r][cc]) {
                    continue LOOP_H;
                }
            }

            int addition = 0;
            for (int cc = c; cc <= threshold; cc++) {
                addition = 10 * addition + table[r][cc];
                visited[r][cc] = true;
            }

            dfs(idx + 1, sum + addition, visited);

            for (int cc = c; cc <= threshold; cc++) {
                visited[r][cc] = false;
            }
        }

        // dfs for vertical
        LOOP_V:
        for (int size = 2; size <= R; size++) {
            int threshold = r + size - 1;

            for (int rr = r; rr <= threshold; rr++) {
                if (!inRange(rr, c) || visited[rr][c]) {
                    continue LOOP_V;
                }
            }

            int addition = 0;
            for (int rr = r; rr <= threshold; rr++) {
                addition = 10 * addition + table[rr][c];
                visited[rr][c] = true;
            }

            dfs(idx + 1, sum + addition, visited);

            for (int rr = r; rr <= threshold; rr++) {
                visited[rr][c] = false;
            }
        }

        // dfs for size=1
        visited[r][c] = true;
        dfs(idx + 1, sum + table[r][c], visited);
        visited[r][c] = false;
    }
}
