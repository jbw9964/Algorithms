import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static char[][] table;
    private static Cord initPoint;
    private static final List<Cord> fires = new ArrayList<>();

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        table = new char[R][];
        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();

            for (int j = 0; j < line.length; j++) {
                char c = line[j];

                if (c == 'J') {
                    initPoint = new Cord(i, j, 1);
                    line[j] = '.';
                } else if (c == 'F') {
                    fires.add(new Cord(i, j, 0));
                }
            }

            table[i] = line;
        }

        assert initPoint != null;
    }


    public static void main(String[] args) throws IOException {
        init();

        System.out.println(dfs());
    }


    private static String dfs() {

        boolean[][] visited = new boolean[R][C];
        visited[initPoint.r][initPoint.c] = true;

        Queue<Cord> queue = new LinkedList<>();
        queue.add(initPoint);

        while (!queue.isEmpty()) {

            Queue<Cord> nextQueue = new LinkedList<>();

            while (!queue.isEmpty()) {

                Cord curr = queue.poll();

                if (curr.r == R - 1 || curr.r == 0 || curr.c == C - 1 || curr.c == 0) {
                    return String.valueOf(curr.min);
                }

                for (int i = 0; i < dr.length; i++) {
                    int r = curr.r + dr[i];
                    int c = curr.c + dc[i];

                    if (!inRange(r, c) || table[r][c] != '.' || visited[r][c]) {
                        continue;
                    }

                    if (fireNearby(r, c)) {
                        continue;
                    }

                    visited[r][c] = true;
                    nextQueue.add(new Cord(r, c, curr.min + 1));
                }
            }

            spreadFires();

            queue = nextQueue;
        }

        return "IMPOSSIBLE";
    }

    private static boolean fireNearby(int r, int c) {
        for (int i = 0; i < dr.length; i++) {
            int rAdj = r + dr[i];
            int cAdj = c + dc[i];

            if (inRange(rAdj, cAdj) && table[rAdj][cAdj] == 'F') {
                return true;
            }
        }

        return false;
    }

    private static void spreadFires() {
        List<Cord> newFires = new ArrayList<>();
        for (Cord fire : fires) {

            for (int i = 0; i < dr.length; i++) {
                int r = fire.r + dr[i];
                int c = fire.c + dc[i];

                if (inRange(r, c) && table[r][c] == '.') {
                    newFires.add(new Cord(r, c, 0));
                    table[r][c] = 'F';
                }
            }
        }

        fires.clear();
        fires.addAll(newFires);
    }

    private static void showTable() {
        for (int i = 0; i < R; i++) {
            System.out.println(String.valueOf(table[i]));
        }
        System.out.println();
    }
}

class Cord {

    final int r, c, min;

    public Cord(int r, int c, int min) {
        this.r = r;
        this.c = c;
        this.min = min;
    }
}