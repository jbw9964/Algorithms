import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    private static char[][] table;

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

        System.out.println(
                dfs(new State(0, 0, null), new boolean[R][C], 1)
        );
    }

    private static int dfs(State curr, boolean[][] visited, int cnt) {

        int initR = curr.r;
        int initC = curr.c;

        visited[initR][initC] = true;
        curr.visit(table[initR][initC]);

        int maxima = cnt;
        for (int i = 0; i < dr.length; i++) {
            int r = initR + dr[i];
            int c = initC + dc[i];

            if (!inRange(r, c) || visited[r][c]) {
                continue;
            }

            char next = table[r][c];
            if (!curr.visited(next)) {
                curr.r = r;
                curr.c = c;

                maxima = Math.max(maxima, dfs(curr, visited, cnt + 1));

                curr.r = initR;
                curr.c = initC;
            }
        }

        visited[initR][initC] = false;
        curr.unVisit(table[initR][initC]);

        return Math.max(maxima, cnt);
    }
}

class State {

    int r, c;
    boolean[] visited;

    private static final int CHAR_LENGTH = 'z' - 'a' + 1;

    private static int toIndex(char ch) {
        ch = Character.toLowerCase(ch);
        return ch - 'a';
    }

    public State(int newR, int newC, State parent) {
        visited = new boolean[CHAR_LENGTH];
        this.r = newR;
        this.c = newC;
        if (parent != null && parent.visited != null) {
            System.arraycopy(parent.visited, 0, visited, 0, visited.length);
        }
    }

    public boolean visited(char ch) {
        return visited[toIndex(ch)];
    }

    public void visit(char ch) {
        visited[toIndex(ch)] = true;
    }

    public void unVisit(char ch) {
        visited[toIndex(ch)] = false;
    }
}
