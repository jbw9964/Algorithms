import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static class Coord  {
        int r, c;
        Coord(int r, int c) {this.r = r; this.c = c;}
    }

    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    private static int N, M;
    private static boolean inRange(int r, int c)    {
        return 0<=r&&r<N&&0<=c&&c<M;
    }

    private static boolean[][] moveableTable;
    private static final Queue<Coord> queue = new LinkedList<>();

    public static void input() throws IOException {
        String[] line = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        moveableTable = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");

            for (int j = 0; j < M; j++) {
                if (line[j].equals("0"))    continue;
                if (line[j].equals("2"))    queue.add(new Coord(i, j));
                moveableTable[i][j] = true;
            } 
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        for (int[] row : BFS()) {
            for (int dist : row)
            sb.append(dist + " ");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int[][] BFS() {
        int[][] table = new int[N][M];
        boolean[][] visitTable = new boolean[N][M];

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        Coord init = queue.peek();
        visitTable[init.r][init.c] = true;

        while (!queue.isEmpty())    {
            Coord coord = queue.poll();
            int dist = table[coord.r][coord.c];

            for (int i = 0; i < 4; i++) {
                int r = coord.r + dr[i];
                int c = coord.c + dc[i];

                if (!inRange(r, c))         continue;
                if (!moveableTable[r][c])   continue;
                if (visitTable[r][c])       continue;

                visitTable[r][c] = true;
                table[r][c] = dist + 1;
                queue.add(new Coord(r, c));
            }
        }

        for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
        if (table[i][j] == 0 && moveableTable[i][j])
        table[i][j] = -1;

        table[init.r][init.c] = 0;

        return table;
    }
}
