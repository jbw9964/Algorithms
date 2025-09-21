import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final int
            R = 8,
            C = 8,
            TIME_MAX = 8;

    private static final int[]
            dr = {0, 0, 1, -1, -1, -1, 1, 1, 0},
            dc = {1, -1, 0, 0, -1, 1, 1, -1, 0};

    private static final char[]
            EMPTY = "........".toCharArray();

    private static char[][][] tableOnTime;

    private static void init() throws IOException {

        tableOnTime = new char[TIME_MAX + 1][R][];

        for (int i = 0; i < R; i++) {
            tableOnTime[0][i] = br.readLine().toCharArray();
        }

        for (int time = 1; time < TIME_MAX + 1; time++) {
            tableOnTime[time][0] = EMPTY;
            System.arraycopy(
                    tableOnTime[time - 1], 0,
                    tableOnTime[time], 1, R - 1
            );
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = bfs(new State(R - 1, 0, 0));

        System.out.println(answer);
    }

    private static int bfs(State init) {

        boolean[][][] visited = new boolean[TIME_MAX + 1][R][C];
        visited[0][init.r][init.c] = true;

        Queue<State> q = new LinkedList<>();
        q.add(init);

        while (!q.isEmpty()) {
            State curr = q.poll();

            int r = curr.r;
            int c = curr.c;
            int currTime = curr.time;

            if (r == 0 && c == C - 1) {
                return 1;
            }

            int nextTime = Math.min(TIME_MAX, currTime + 1);
            for (int i = 0; i < dr.length; i++) {
                int nextR = r + dr[i];
                int nextC = c + dc[i];

                if (
                        visitable(nextR, nextC, currTime) &&
                        !visited[nextTime][nextR][nextC]
                ) {
                    visited[nextTime][nextR][nextC] = true;
                    q.add(new State(nextR, nextC, nextTime));
                }
            }
        }

        return 0;
    }

    private static boolean visitable(int r, int c, int preVisitTime) {

        if (!inRange(r, c)) {
            return false;
        }

        for (int dt = 0; dt <= 1; dt++) {
            int timeIndex = Math.min(TIME_MAX, preVisitTime + dt);

            if (tableOnTime[timeIndex][r][c] == '#') {
                return false;
            }
        }

        return true;
    }
}

class State {

    final int r, c, time;

    public State(int r, int c, int time) {
        this.r = r;
        this.c = c;
        this.time = time;
    }

    @Override
    public String toString() {
        return "State{" +
               "r=" + r +
               ", c=" + c +
               ", time=" + time +
               '}';
    }
}
