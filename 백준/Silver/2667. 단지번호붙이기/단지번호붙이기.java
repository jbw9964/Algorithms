import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;
    private static int[][] table;
    private static boolean[][] visited;

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void init() throws IOException {

        R = C = Integer.parseInt(br.readLine());

        table = new int[R][];

        for (int i = 0; i < R; i++) {
            int[] values = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            table[i] = values;
        }

        visited = new boolean[R][C];
    }

    public static void main(String[] args) throws IOException {

        init();

        List<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (table[i][j] == 1 && !visited[i][j]) {
                    sizes.add(dfs(i, j));
                }
            }
        }

        sizes.sort(Integer::compareTo);

        System.out.println(sizes.size());
        for (int size : sizes)  {
            System.out.println(size);
        }
    }

    private static int dfs(int r, int c) {
        if (!inRange(r, c) || table[r][c] != 1 || visited[r][c]) {
            return 0;
        }

        visited[r][c] = true;

        return 1 + dfs(r + 1, c) + dfs(r - 1, c)
                + dfs(r, c - 1) + dfs(r, c + 1);
    }
}