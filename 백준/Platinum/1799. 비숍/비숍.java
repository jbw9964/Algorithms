import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int ANSWER = 0;
    private static boolean[][] placeable;
    private static Cord[][] cords;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        placeable = new boolean[N][N];
        cords = new Cord[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cords[i][j] = new Cord(i, j);

                if ("1".equals(st.nextToken())) {
                    placeable[i][j] = true;
                }
            }
        }

        dfs(0, 0);

        if (N > 1) {
            dfs(1, ANSWER);
        }

        System.out.println(ANSWER);
    }

    private static void dfs(int count, int placedBishops) {
        if (count >= N * N) {
            ANSWER = Math.max(ANSWER, placedBishops);
            return;
        }

        int i = count / N;
        int j = count % N;

        if (placeable[i][j]) {
            List<Cord> markings = getPlaceable(i, j);

            for (Cord marking : markings) {
                placeable[marking.i][marking.j] = false;
            }

            dfs(
                    count + (N % 2 != 0 || j + 2 < N ? 2 : j == N - 1 ? 1 : 3),
                    placedBishops + 1
            );

            for (Cord marking : markings) {
                placeable[marking.i][marking.j] = true;
            }

            markings.clear();
        }

        dfs(
                count + (N % 2 != 0 || j + 2 < N ? 2 : j == N - 1 ? 1 : 3),
                placedBishops
        );
    }

    private static List<Cord> getPlaceable(int i, int j) {
        Set<Cord> dst = new HashSet<>(N);

        int r = Math.max(0, i - j);
        int c = Math.max(0, j - i);

        while (r < N && c < N) {
            if (placeable[r][c]) {
                placeable[r][c] = false;
                dst.add(cords[r][c]);
            }
            r++;
            c++;
        }

        r = Math.max(0, 1 - N + i + j);
        c = Math.min(i + j, N - 1);

        while (r < N && c >= 0) {
            if (placeable[r][c]) {
                placeable[r][c] = false;
                dst.add(cords[r][c]);
            }
            r++;
            c--;
        }

        return new ArrayList<>(dst);
    }

    private static void showArr() {
        for (boolean[] row : placeable) {
            for (boolean b : row) {
                System.out.print(b ? '1' : '0');
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void showArr(int[][] arr) {
        for (int[] row : arr) {
            for (int b : row) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Cord {

    int i, j;

    public Cord(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cord cord = (Cord) o;
        return i == cord.i && j == cord.j;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }

    @Override
    public String toString() {
        return "Cord{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}