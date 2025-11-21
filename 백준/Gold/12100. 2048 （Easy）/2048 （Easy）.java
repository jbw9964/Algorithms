import java.io.*;
import java.util.*;
import java.util.function.*;

@SuppressWarnings("DuplicatedCode")
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[][] table;
    private static int ANSWER;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        table = new int[N][];
        for (int i = 0; i < N; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void main(String[] args) throws IOException {

        init();

        solve(0);

        System.out.println(ANSWER);
    }

    private static void solve(int moveCnt) {

        if (moveCnt == 5) {
            updateAnswer();
            return;
        }

        //noinspection unchecked
        Supplier<Boolean>[] func = new Supplier[]{
                Main::shiftTableUp, Main::shiftTableDown,
                Main::shiftTableLeft, Main::shiftTableRight
        };

        for (Supplier<Boolean> fun : func) {
            int[][] prev = cloneTable();
            boolean modified = fun.get();

            if (modified) {
                solve(moveCnt + 1);
            } else {
                updateAnswer();
            }

            Main.table = prev;
        }

    }

    private static void updateAnswer() {
        int maxima = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxima = Math.max(maxima, table[i][j]);
            }
        }

        ANSWER = Math.max(ANSWER, maxima);
    }

    private static int[][] cloneTable() {
        int[][] clone = new int[N][];
        for (int i = 0; i < N; i++) {
            clone[i] = table[i].clone();
        }
        return clone;
    }

    private static boolean shiftTableUp() {
        boolean modified = false;

        // merge
        for (int c = 0; c < N; c++) {

            for (int r1 = 0; r1 < N; r1++) {

                int val1 = table[r1][c];
                if (val1 == 0) {
                    continue;
                }

                boolean mergeable = false;

                for (int r2 = r1 + 1; r2 < N; r2++) {
                    int val2 = table[r2][c];

                    if (val2 == 0) {
                        continue;
                    }
                    if (val1 == val2) {
                        mergeable = true;
                        table[r2][c] = 0;
                    }

                    break;
                }

                if (!mergeable) {
                    continue;
                }

                table[r1][c] = 2 * val1;
                modified |= mergeable;
            }
        }

        // shift
        for (int c = 0; c < N; c++) {
            for (int r = 0; r < N; r++) {

                int val = table[r][c];
                if (val == 0) {
                    continue;
                }

                boolean shifted = false;

                int i = r;
                while (i - 1 >= 0 && table[i - 1][c] == 0) {
                    i--;
                    shifted = true;
                }

                table[r][c] = 0;
                table[i][c] = val;
                modified |= shifted;
            }
        }

        return modified;
    }

    private static boolean shiftTableDown() {
        boolean modified = false;

        // merge
        for (int c = 0; c < N; c++) {

            for (int r1 = N - 1; r1 >= 0; r1--) {

                int val1 = table[r1][c];
                if (val1 == 0) {
                    continue;
                }

                boolean mergeable = false;

                for (int r2 = r1 - 1; r2 >= 0; r2--) {
                    int val2 = table[r2][c];

                    if (val2 == 0) {
                        continue;
                    }
                    if (val1 == val2) {
                        mergeable = true;
                        table[r2][c] = 0;
                    }

                    break;
                }

                if (!mergeable) {
                    continue;
                }

                table[r1][c] = 2 * val1;
                modified |= mergeable;
            }
        }

        // shift
        for (int c = 0; c < N; c++) {
            for (int r = N - 1; r >= 0; r--) {

                int val = table[r][c];
                if (val == 0) {
                    continue;
                }

                boolean shifted = false;

                int i = r;
                while (i + 1 < N && table[i + 1][c] == 0) {
                    i++;
                    shifted = true;
                }

                table[r][c] = 0;
                table[i][c] = val;
                modified |= shifted;
            }
        }

        return modified;
    }

    private static boolean shiftTableLeft() {
        boolean modified = false;

        // merge
        for (int r = 0; r < N; r++) {

            for (int c1 = 0; c1 < N; c1++) {
                int val1 = table[r][c1];
                if (val1 == 0) {
                    continue;
                }

                boolean mergeable = false;

                for (int c2 = c1 + 1; c2 < N; c2++) {
                    int val2 = table[r][c2];
                    if (val2 == 0) {
                        continue;
                    }

                    if (val1 == val2) {
                        mergeable = true;
                        table[r][c2] = 0;
                    }

                    break;
                }

                if (!mergeable) {
                    continue;
                }

                table[r][c1] = 2 * val1;
                modified |= mergeable;
            }
        }

        // shift
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int val = table[r][c];
                if (val == 0) {
                    continue;
                }

                boolean shifted = false;

                int i = c;
                while (i - 1 >= 0 && table[r][i - 1] == 0) {
                    i--;
                    shifted = true;
                }

                table[r][c] = 0;
                table[r][i] = val;
                modified |= shifted;
            }
        }

        return modified;
    }

    private static boolean shiftTableRight() {
        boolean modified = false;

        // merge
        for (int r = 0; r < N; r++) {

            for (int c1 = N - 1; c1 >= 0; c1--) {
                int val1 = table[r][c1];
                if (val1 == 0) {
                    continue;
                }

                boolean mergeable = false;

                for (int c2 = c1 - 1; c2 >= 0; c2--) {
                    int val2 = table[r][c2];
                    if (val2 == 0) {
                        continue;
                    }

                    if (val1 == val2) {
                        mergeable = true;
                        table[r][c2] = 0;
                    }

                    break;
                }

                if (!mergeable) {
                    continue;
                }

                table[r][c1] = 2 * val1;
                modified |= mergeable;
            }
        }

        // shift
        for (int r = 0; r < N; r++) {
            for (int c = N - 1; c >= 0; c--) {
                int val = table[r][c];
                if (val == 0) {
                    continue;
                }

                boolean shifted = false;

                int i = c;
                while (i + 1 < N && table[r][i + 1] == 0) {
                    i++;
                    shifted = true;
                }

                table[r][c] = 0;
                table[r][i] = val;
                modified |= shifted;
            }
        }

        return modified;
    }


    private static void showTable() {
        for (int[] row : table) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

