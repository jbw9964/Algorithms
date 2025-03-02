import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C, K;
    private static int S, X, Y;

    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    private static int[][] table;
    private static boolean[][] visited;

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static List<Virus> init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        table = new int[R][];
        visited = new boolean[R][C];
        List<Virus> viruses = new ArrayList<>(K);

        for (int i = 0; i < R; i++) {
            int[] row = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 0; j < row.length; j++) {
                int val = row[j];

                if (val != 0)   {
                    viruses.add(new Virus(
                            i, j, 0, val
                    ));
                    visited[i][j] = true;
                }
            }

            table[i] = row;
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        return viruses;
    }

    public static void main(String[] args) throws IOException {
        List<Virus> initPoints = init();

        System.out.println(bfs(initPoints));
    }

    private static int bfs(List<Virus> initPoints)    {

        PriorityQueue<Virus> pq = new PriorityQueue<>(initPoints);
        Virus curr;

        while ((curr = pq.poll()) != null && curr.time < S) {

            if (curr.r == X && curr.c == Y)    {
                return curr.type;
            }

            for (int i = 0; i < dr.length; i++) {
                int r = curr.r + dr[i];
                int c = curr.c + dc[i];

                if (!inRange(r, c) || visited[r][c] || table[r][c] != 0)    {
                    continue;
                }

                table[r][c] = curr.type;
                visited[r][c] = true;
                pq.add(new Virus(r, c, curr.time + 1, curr.type));
            }
        }

        return table[X][Y];
    }

    private static void showTable() {
        for (int[] row : table) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

class Virus implements Comparable<Virus> {
    final int r, c, time, type;

    public Virus(int r, int c, int time, int type) {
        this.r = r;
        this.c = c;
        this.time = time;
        this.type = type;
    }

    @Override
    public int compareTo(Virus o) {
        return time != o.time ? time - o.time : type - o.type;
    }
}
