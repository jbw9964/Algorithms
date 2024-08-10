import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiPredicate;

public class Main {
    private static int N;
    private static char[][] letters;

    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    private static boolean inRange(int r, int c)    {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        letters = new char[N][];

        for (int i = 0; i < N; i++)
            letters[i] = br.readLine().toUpperCase().toCharArray();

        BiPredicate<Character, Character> RGBlindness
                = (c, adj) -> c == adj ? true : c != 'B' && adj != 'B';

        int normalCount = 0;
        int blindCount = 0;

        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (visited[i][j])      continue;

                normalCount++;
                // BFS(visited, new Coord(i, j), (c1, c2) -> c1 == c2);
                DFS(visited, new Coord(i, j), (c1, c2) -> c1 == c2);
            }

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;

                blindCount++;
                // BFS(visited, new Coord(i, j), RGBlindness);
                DFS(visited, new Coord(i, j), RGBlindness);
            }

        System.out.println(normalCount + " " + blindCount);
    }

    //<editor-fold desc="BFS & DFS">
    private static void BFS(boolean[][] visited, Coord init,
                            BiPredicate<Character, Character> predicate) {
        visited[init.r][init.c] = true;

        char origin = letters[init.r][init.c];

        Queue<Coord> queue = new LinkedList<>();
        queue.add(init);

        while (!queue.isEmpty()) {
            Coord coord = queue.poll();

            for (int i = 0; i < 4; i++) {
                int r = coord.r + dr[i];
                int c = coord.c + dc[i];

                if (!inRange(r, c))                 continue;

                char adj = letters[r][c];
                if (!predicate.test(origin, adj))   continue;
                if (visited[r][c])                  continue;

                visited[r][c] = true;
                queue.add(new Coord(r, c));
            }
        }
    }
    private static void DFS(boolean[][] visited, Coord init,
                            BiPredicate<Character, Character> predicate) {
        visited[init.r][init.c] = true;

        char origin = letters[init.r][init.c];

        for (int i = 0; i < 4; i++) {
            int r = init.r + dr[i];
            int c = init.c + dc[i];

            if (!inRange(r, c))                 continue;

            char adj = letters[r][c];
            if (!predicate.test(origin, adj))   continue;
            if (visited[r][c])                  continue;

            BFS(visited, new Coord(r, c), predicate);
        }
    }
    //</editor-fold>

    private static class Coord  {
        int r, c;

        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}