import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, R, C;
    private static int[][] table;
    private static final int[] dr = {0, 0, -1, 1}, dc = {-1, 1, 0, 0};

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void init() throws IOException {
        N = R = C = Integer.parseInt(br.readLine());

        table = new int[R][];
        for (int i = 0; i < N; i++) {
            table[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        System.out.println(bfs());
    }

    private static int bfs() {

        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;

        Queue<Cord> q = new LinkedList<>();
        q.add(new Cord(0, 0));

        int delCnt = 0;

        while (delCnt < N * N) {

            Queue<Cord> nextQ = new LinkedList<>();
            
            while (!q.isEmpty()) {
                Cord cord = q.poll();

                int r = cord.r;
                int c = cord.c;

                if (r == R - 1 && c == C - 1) {
                    return delCnt;
                }

                for (int i = 0; i < dr.length; i++) {
                    int newR = r + dr[i];
                    int newC = c + dc[i];

                    if (!inRange(newR, newC) || visited[newR][newC]) {
                        continue;
                    }

                    visited[newR][newC] = true;
                    Cord newCord = new Cord(newR, newC);

                    if (table[newR][newC] == 1) {
                        q.add(newCord);
                    }
                    else {
                        nextQ.add(newCord);
                    }
                }
            }

            q = nextQ;
            delCnt++;
        }

        throw new RuntimeException();
    }
}

class Cord {

    final int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
