import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;
    private static char[][] table;

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static final int[] dr = {0, 0, -1, 1}, dc = {1, -1, 0, 0};

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        table = new char[R][];
        for (int i = 0; i < R; i++) {
            table[i] = br.readLine().toCharArray();
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        Queue<Cord> sheep = new LinkedList<>();
        Queue<Cord> wolves = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char val = table[i][j];

                if (val == 'S') {
                    sheep.add(new Cord(i, j));
                } else if (val == 'W') {
                    wolves.add(new Cord(i, j));
                }
            }
        }

        while (!sheep.isEmpty()) {
            Cord cord = sheep.poll();

            for (int i = 0; i < 4; i++) {
                int r = cord.r + dr[i];
                int c = cord.c + dc[i];

                if (inRange(r, c) && table[r][c] == '.') {
                    table[r][c] = 'D';
                }
            }
        }

        int result = bfs(wolves);
        System.out.println(result);

        if (result == 1) {
            for (int i = 0; i < R; i++) {
                System.out.println(String.valueOf(table[i]));
            }
        }
    }

    private static int bfs(Collection<Cord> wolves) {

        Queue<Cord> q = new LinkedList<>(wolves);

        boolean[][] visited = new boolean[R][C];
        q.forEach(c -> visited[c.r][c.c] = true);

        while (!q.isEmpty()) {
            Cord cord = q.poll();

            int r = cord.r, c = cord.c;

            if (table[r][c] == 'S') {
                return 0;
            }

            for (int i = 0; i < 4; i++) {
                int newR = r + dr[i];
                int newC = c + dc[i];

                if (
                        !inRange(newR, newC) ||
                        visited[newR][newC] ||
                        table[newR][newC] == 'D'
                ) {
                    continue;
                }

                visited[newR][newC] = true;
                q.offer(new Cord(newR, newC));
            }
        }

        return 1;
    }
}

class Cord {

    int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
