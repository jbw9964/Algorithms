import java.io.*;
import java.util.*;
import java.util.function.*;

class Solution {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {

            int T = Integer.parseInt(br.readLine());
            final int R = 100;

            int[][] map = new int[R][];
            for (int j = 0; j < R; j++) {
                map[j] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            int answer = solve(map);

            sb.append("#").append(T).append(" ").append(answer).append('\n');
        }

        System.out.println(sb.toString());
    }

    private static int solve(int[][] map) {
        int R, C;
        R = C = map.length;

        BiFunction<Integer, Integer, Boolean> inRange =
                (r, c) -> r >= 0 && r < R && c >= 0 && c < C;

        int initR = R - 1;
        int initC;
        for (int c = 0; c < C; c++) {
            if (map[initR][c] == 2) {
                initC = c;
                boolean[][] visited = new boolean[R][C];
                visited[initR][initC] = true;
                return traversal(map, initR, initC, inRange, visited);
            }
        }

        throw new RuntimeException();
    }

    private static final int[] dr = {0, 0, -1}, dc = {1, -1, 0};

    private static int traversal(
            int[][] map, int r, int c,
            BiFunction<Integer, Integer, Boolean> inRange,
            boolean[][] visited
    ) {

        if (r == 0) {
            return c;
        }

        for (int i = 0; i < dr.length; i++) {

            int newR = r + dr[i];
            int newC = c + dc[i];

            if (!inRange.apply(newR, newC) || visited[newR][newC]) {
                continue;
            }

            if (map[newR][newC] == 1) {
                visited[newR][newC] = true;
                return traversal(map, newR, newC, inRange, visited);
            }
        }

        throw new RuntimeException();
    }
}
