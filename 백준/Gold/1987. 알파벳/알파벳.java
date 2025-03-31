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
    private static boolean[] charVisited;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        table = new char[R][];
        for (int i = 0; i < R; i++) {
            table[i] = br.readLine().toCharArray();
        }
        charVisited = new boolean['z' - 'a' + 1];
    }

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(dfs(0, 0, new boolean[R][C], 1));
    }

    private static int dfs(int r, int c, boolean[][] visited, int cnt)  {

        visited[r][c] = true;
        char curr = table[r][c];
        visit(curr);

        int maxima = cnt;
        for (int i = 0; i < dr.length; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if (!inRange(nextR, nextC) || visited[nextR][nextC]) {
                continue;
            }

            char next = table[nextR][nextC];
            if (!charVisited(next)) {
                maxima = Math.max(maxima, dfs(nextR, nextC, visited, cnt + 1));
            }
        }

        unVisited(curr);
        visited[r][c] = false;

        return Math.max(maxima, cnt);
    }

    private static void visit(char ch)  {
        charVisited[toIndex(ch)] = true;
    }
    private static void unVisited(char ch) {
        charVisited[toIndex(ch)] = false;
    }
    private static boolean charVisited(char ch) {
        return charVisited[toIndex(ch)];
    }

    private static int toIndex(char ch) {
        ch = Character.toLowerCase(ch);
        return ch - 'a';
    }
}