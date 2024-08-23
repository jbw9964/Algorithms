import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int[][] arr;

    private static final int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
    private static boolean inRange(int r, int c)    {
        return 0 <= r && r < arr.length && 0 <= c && c < arr[r].length;
    }

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        Set<Integer> distinctLevels = init();

        int answer = 1;

        List<Integer> levelList = new ArrayList<>(distinctLevels);
        levelList.sort(Integer::compareTo);
        
        while (levelList.size() > 1)    {
            int level = levelList.remove(0);
        }
        
        for (int level : distinctLevels) {
            int count = 0;

            boolean[][] visited = new boolean[N][N];
            for (int r = 0; r < N; r++)
                for (int c = 0; c < N; c++)
                    if (!visited[r][c] && arr[r][c] > level) {
                        // BFS(visited, new Coord(r, c), level);
                        DFS(visited, new Coord(r, c), level);
                        count++;
                    }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    //<editor-fold desc="init">
    private static Set<Integer> init() throws IOException  {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        Set<Integer> levels = new HashSet<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int level = Integer.parseInt(st.nextToken());
                arr[i][j] = level;
                levels.add(level);
            }
        }

        return levels;
    }
    //</editor-fold>

    //<editor-fold desc="BFS & DFS">
    private static void BFS(boolean[][] visit, Coord init, int LEVEL)  {
        visit[init.r][init.c] = true;

        Queue<Coord> queue = new LinkedList<>();
        queue.add(init);

        while (!queue.isEmpty()) {
            Coord coord = queue.poll();

            for (int i = 0; i < 4; i++) {
                int r = coord.r + dr[i];
                int c = coord.c + dc[i];

                if (!inRange(r, c))         continue;
                if (arr[r][c] <= LEVEL)     continue;
                if (visit[r][c])            continue;

                visit[r][c] = true;
                queue.add(new Coord(r, c));
            }
        }
    }
    private static void DFS(boolean[][] visit, Coord init, int LEVEL)  {
        visit[init.r][init.c] = true;

        for (int i = 0; i < 4; i++) {
            int r = init.r + dr[i];
            int c = init.c + dc[i];

            if (!inRange(r, c))         continue;
            if (arr[r][c] <= LEVEL)     continue;
            if (visit[r][c])            continue;

            DFS(visit, new Coord(r, c), LEVEL);
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