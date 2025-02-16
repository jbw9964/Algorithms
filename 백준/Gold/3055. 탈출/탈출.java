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

    private static Cord initPoint, targetPoint;
    private static Queue<Cord> waterToExpand;
    private static char[][] table;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        waterToExpand = new ArrayDeque<>(R * C);

        table = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {

                switch (line[j]) {
                    case 'D':
                        targetPoint = new Cord(i, j);
                        break;
                    case 'S':
                        initPoint = new Cord(i, j);
                        line[j] = '.';
                        break;
                    case '*':
                        waterToExpand.add(new Cord(i, j));
                }

                table[i][j] = line[j];
            }
        }

        if (initPoint == null || targetPoint == null) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        System.out.println(bfs());
    }

    private static String bfs() {

        boolean[][] visited = new boolean[R][C];
        visited[initPoint.r][initPoint.c] = true;

        Queue<Cord> queue = new LinkedList<>();
        queue.add(initPoint);

        int step = 0;
        while (!queue.isEmpty()) {

            Queue<Cord> nextQueue = new LinkedList<>();

            while (!queue.isEmpty()) {
                Cord curr = queue.poll();

                if (curr.r == targetPoint.r && curr.c == targetPoint.c) {
                    return String.valueOf(step);
                }

                for (int i = 0; i < dr.length; i++) {
                    int r = curr.r + dr[i];
                    int c = curr.c + dc[i];

                    if (!inRange(r, c)) {
                        continue;
                    }

                    if (table[r][c] == 'X' || table[r][c] == '*') {
                        continue;
                    }

                    if (visited[r][c]) {
                        continue;
                    }

                    if (waterNearBy(r, c))  {
                        continue;
                    }

                    visited[r][c] = true;
                    nextQueue.add(new Cord(r, c));
                }
            }

            expandWaters();
            queue = nextQueue;

            step++;
        }

        return "KAKTUS";
    }

    private static boolean waterNearBy(int r, int c) {

        if (table[r][c] == 'D') {
            return false;
        }

        for (int i = 0; i < dr.length; i++) {
            int newR = r + dr[i];
            int newC = c + dc[i];

            if (inRange(newR, newC) && table[newR][newC] == '*')    {
                return true;
            }
        }

        return false;
    }

    private static void expandWaters() {
        int size = waterToExpand.size();

        while (size-- > 0) {
            Cord curr = waterToExpand.poll();

            assert curr != null;

            for (int i = 0; i < dr.length; i++) {

                int r = curr.r + dr[i];
                int c = curr.c + dc[i];

                if (!inRange(r, c) || table[r][c] != '.') {
                    continue;
                }

                table[r][c] = '*';
                waterToExpand.add(new Cord(r, c));
            }
        }
    }

    private static void showTable() {
        for (char[] line : table) {
            System.out.println(String.valueOf(line));
        }
        System.out.println();
    }
}

class Cord {

    int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Cord{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }
}