import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, R, C;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static int[][] table;

    public static void main(String[] args) throws IOException {

        N = R = C = Integer.parseInt(br.readLine());

        table = new int[R][];

        for (int i = 0; i < R; i++) {
            int[] row = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            table[i] = row;
        }

        System.out.println(bfs());
    }

    private static int bfs()    {

        int ans = Integer.MAX_VALUE;

        boolean[][][] visited = new boolean[N * N][R][C];

        Queue<Cord> queue = new LinkedList<>();
        queue.add(new Cord(0, 0, 0));

        while (!queue.isEmpty()) {

            Cord curr = queue.poll();
            int blacks = curr.numOfBlacks;

            if (curr.r == R - 1 && curr.c == C - 1) {
                ans = Math.min(ans, blacks);
                continue;
            }

            for (int i = 0; i < dr.length; i++) {
                int r = curr.r + dr[i];
                int c = curr.c + dc[i];

                if (!inRange(r, c)) {
                    continue;
                }

                if (hasVisited(visited, r, c, blacks)){
                    continue;
                }

                visited[blacks][r][c] = true;
                queue.add(new Cord(
                        r, c,
                        blacks + (table[r][c] == 1 ? 0 : 1)
                ));
            }
        }

        return ans;
    }

    private static boolean hasVisited(boolean[][][] visited, int r, int c, int blacks) {
        for (int b = 0; b <= blacks; b++) {
            if (visited[b][r][c]) {
                return true;
            }
        }
        return false;
    }
}


class Cord {

    int r, c;
    int numOfBlacks;

    public Cord(int r, int c, int numOfBlacks) {
        this.r = r;
        this.c = c;
        this.numOfBlacks = numOfBlacks;
    }
}