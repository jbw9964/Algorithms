import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    private static int[][] table;
    private static boolean[][] visited;

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void init() throws IOException {

        R = C = Integer.parseInt(br.readLine());

        table = new int[R][];

        for (int i = 0; i < R; i++) {
            int[] values = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            table[i] = values;
        }

        visited = new boolean[R][C];
    }

    public static void main(String[] args) throws IOException {

        init();

        List<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (visited[i][j] || table[i][j] != 1) {
                    continue;
                }

                sizes.add(bfs(i, j));
            }
        }

        sizes.sort(Integer::compareTo);

        System.out.println(sizes.size());
        for (int size : sizes) {
            System.out.println(size);
        }
    }

    private static int bfs(int initR, int initC) {

        visited[initR][initC] = true;

        Queue<Cord> queue = new LinkedList<>();
        queue.add(new Cord(initR, initC));

        int size = 1;

        while ((!queue.isEmpty())) {

            Cord cur = queue.poll();

            for (int i = 0; i < dr.length; i++) {

                int r = cur.r + dr[i];
                int c = cur.c + dc[i];

                if (!inRange(r, c) || table[r][c] != 1 || visited[r][c]) {
                    continue;
                }

                visited[r][c] = true;
                queue.add(new Cord(r, c));
                size++;
            }
        }

        return size;
    }
}

class Cord {

    int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}