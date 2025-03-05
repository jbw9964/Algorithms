import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C, T, ALL;
    private static int[][] table;

    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static Cord upperPurifier, lowerPurifier;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        table = new int[R][];

        for (int i = 0; i < R; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 0; j < C; j++) {
                if (table[i][j] == -1) {
                    if (upperPurifier == null) {
                        upperPurifier = new Cord(i, j);
                    } else {
                        lowerPurifier = new Cord(i, j);
                    }
                } else {
                    ALL += table[i][j];
                }
            }
        }

        assert upperPurifier != null && lowerPurifier != null;
    }

    private static int getALL() {
        int val = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                val += table[i][j];
            }
        }

        return val + 2;
    }

    public static void main(String[] args) throws IOException {

        init();

        int removed = 0;
        while (T-- > 0) {
            spreadDust();

            removed += purifyAir();
        }

        System.out.println(ALL - removed);
    }

    private static void spreadDust() {
        int[][] newTable = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                int val = table[i][j];

                if (val / 5 > 0) {
                    int spreadCnt = 0;

                    for (int k = 0; k < dr.length; k++) {
                        int adjR = i + dr[k];
                        int adjC = j + dc[k];

                        if (!inRange(adjR, adjC)) {
                            continue;
                        }

                        if (table[adjR][adjC] == -1) {
                            continue;
                        }

                        spreadCnt++;
                        newTable[adjR][adjC] += val / 5;
                    }

                    val -= spreadCnt * (val / 5);
                }

                newTable[i][j] += val;
            }
        }

        table = newTable;
    }

    private static int purifyAir() {
        return doUpper() + doLower();
    }

    private static int doUpper() {
        assert upperPurifier != null;

        for (int r = upperPurifier.r; r > 0; r--) {
            table[r][0] = table[r - 1][0];
        }

        copyRight(0);

        for (int r = 0; r < upperPurifier.r; r++) {
            table[r][C - 1] = table[r + 1][C - 1];
        }

        copyLeft(upperPurifier.r);

        int val = table[upperPurifier.r][0];
        table[upperPurifier.r][0] = -1;
        table[upperPurifier.r][1] = 0;

        return val;
    }

    private static int doLower() {
        assert lowerPurifier != null;

        for (int r = lowerPurifier.r; r < R - 1; r++) {
            table[r][0] = table[r + 1][0];
        }

        copyRight(R - 1);

        for (int r = R - 1; r > lowerPurifier.r; r--) {
            table[r][C - 1] = table[r - 1][C - 1];
        }

        copyLeft(lowerPurifier.r);

        int val = table[lowerPurifier.r][0];
        table[lowerPurifier.r][0] = -1;
        table[lowerPurifier.r][1] = 0;

        return val;
    }

    private static void copyRight(int r) {
        for (int c = 0; c < C - 1; c++) {
            table[r][c] = table[r][c + 1];
        }
    }

    private static void copyLeft(int r) {
        for (int c = C - 1; c > 0; c--) {
            table[r][c] = table[r][c - 1];
        }
    }

    private static void showTable() {
        for (int[] row : table) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Cord {

    final int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
