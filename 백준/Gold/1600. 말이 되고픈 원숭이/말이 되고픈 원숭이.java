import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C, K;
    private static int[][] table;
    private static boolean[][][] visit;

    private static final int[] dr1 = {
            0, 0, 1, -1
    }, dc1 = {
            1, -1, 0, 0
    };

    private static final int[] dr2 = {
            1, 2, 2, 1, -1, -2, -2, -1
    }, dc2 = {
            2, 1, -1, -2, -2, -1, 1, 2
    };

    private static boolean inRange(int r, int c) {
        return (r >= 0 && r < R && c >= 0 && c < C);
    }


    public static void main(String[] args) throws IOException {

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        table = new int[R][C];
        visit = new boolean[R][C][K + 1];

        for (int i = 0; i < R; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    table[i][j] = -1;
                }
            }
        }
        
        System.out.println(bfs(0, 0));
    }


    private static int bfs(int initR, int initC) {

        visit[initR][initC][K] = true;

        Queue<State> q = new LinkedList<>();
        q.add(new State(initR, initC, 0, K));

        while (!q.isEmpty()) {

            State cur = q.poll();
            int steps = cur.steps;
            int jumps = cur.remainJumps;

            if (cur.r == R - 1 && cur.c == C - 1) {
                return steps;
            }


            for (int i = 0; i < dr1.length; i++) {
                int r = cur.r + dr1[i];
                int c = cur.c + dc1[i];

                if (moveable(r, c, jumps)) {
                    visit[r][c][jumps] = true;
                    q.add(new State(r, c, steps + 1, jumps));
                }
            }

            if (jumps <= 0) {
                continue;
            }

            for (int i = 0; i < dr2.length; i++) {
                int r = cur.r + dr2[i];
                int c = cur.c + dc2[i];

                if (moveable(r, c, jumps - 1)) {
                    visit[r][c][jumps - 1] = true;
                    q.add(new State(r, c, steps + 1, jumps - 1));
                }
            }
        }

        return -1;
    }

    private static boolean moveable(int r, int c, int jumps) {
        return inRange(r, c) && table[r][c] == 0 && !visit[r][c][jumps];
    }
}

class State {

    int r, c;
    int steps, remainJumps;

    public State(int r, int c, int steps, int remainJumps) {
        this.r = r;
        this.c = c;
        this.steps = steps;
        this.remainJumps = remainJumps;
    }
}
