import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, CASE_NUM;
    private static boolean[] horizontal, vertical;
    private static boolean[] diagonal1, diagonal2;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        horizontal = new boolean[N];
        vertical = new boolean[N];

        diagonal1 = new boolean[2 * N - 1];
        diagonal2 = new boolean[2 * N - 1];

        Arrays.fill(horizontal, true);
        Arrays.fill(vertical, true);
        Arrays.fill(diagonal1, true);
        Arrays.fill(diagonal2, true);
    }

    public static void main(String[] args) throws IOException {

        init();

        dfs(0);

        System.out.println(CASE_NUM);
    }

    private static void dfs(int row) {

        if (row >= N) {
            CASE_NUM++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (placeable(row, col)) {
                visit(row, col);
                dfs(row + 1);
                leave(row, col);
            }
        }
    }


    private static boolean placeable(int i, int j) {
        return horizontal[i] && vertical[j] &&
                diagonal1[N - 1 + i - j] && diagonal2[i + j];
    }

    private static void visit(int i, int j) {
        record(i, j, false);
    }

    private static void leave(int i, int j) {
        record(i, j, true);
    }

    private static void record(int i, int j, boolean val) {
        horizontal[i] = val;
        vertical[j] = val;

        diagonal1[N - 1 + i - j] = val;
        diagonal2[i + j] = val;
    }
}
