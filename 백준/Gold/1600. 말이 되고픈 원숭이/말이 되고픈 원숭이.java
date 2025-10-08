import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int K, R, C;
    private static int[][] table;

    private static final int[]
            dr1 = {0, 0, -1, 1},
            dc1 = {-1, 1, 0, 0};
    private static final int[]
            dr2 = {-2, -1, 1, 2, 2, 1, -1, -2},
            dc2 = {1, 2, 2, 1, -1, -2, -2, -1};

    private static void init() throws IOException {

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        table = new int[R][];
        for (int i = 0; i < R; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = solve();

        System.out.println(answer);
    }

    @SuppressWarnings("DuplicatedCode")
    private static int solve() {

        boolean[][][] visited = new boolean[K + 1][R][C];
        visited[0][0][0] = true;

        Queue<State> q = new LinkedList<>();
        q.add(new State(0, 0, 0, 0));

        while (!q.isEmpty()) {

            State s = q.poll();
            int moves = s.moves;
            int usedHops = s.usedHops;

            if (s.r == R - 1 && s.c == C - 1) {
                return moves;
            }

            for (int i = 0; i < dr1.length; i++) {
                int r = s.r + dr1[i];
                int c = s.c + dc1[i];

                if (
                        !inRange(r, c) ||
                        table[r][c] != 0 ||
                        visited[usedHops][r][c]
                ) {
                    continue;
                }

                visited[usedHops][r][c] = true;
                q.add(new State(r, c, usedHops, moves + 1));
            }

            if (usedHops >= K)   {
                continue;
            }

            int nextHop = usedHops + 1;

            for (int i = 0; i < dr2.length; i++) {
                int r = s.r + dr2[i];
                int c = s.c + dc2[i];

                if (
                        !inRange(r, c) ||
                        table[r][c] != 0 ||
                        visited[nextHop][r][c]
                ) {
                    continue;
                }

                visited[nextHop][r][c] = true;
                q.add(new State(r, c, nextHop, moves + 1));
            }
        }

        return -1;
    }
}

class State {

    final int r, c, usedHops;
    final int moves;

    public State(int r, int c, int usedHops, int moves) {
        this.r = r;
        this.c = c;
        this.usedHops = usedHops;
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "State{" +
               "r=" + r +
               ", c=" + c +
               ", usedHops=" + usedHops +
               ", moves=" + moves +
               '}';
    }
}
