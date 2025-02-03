import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final Stack<Cord> targets = new Stack<>();
    private static final int[][] table = new int[9][9];

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 9; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 9; j++) {

                int value = Integer.parseInt(st.nextToken());
                table[i][j] = value;

                if (value == 0) {
                    targets.push(new Cord(i, j));
                }
            }
        }

        if (!targets.isEmpty()) {
            dfs();
        }
    }

    private static void dfs() {

        if (targets.isEmpty()) {
            showTable();
            System.exit(0);
        }

        Cord current = targets.pop();

        for (int trial = 1; trial <= 9; trial++) {
            table[current.r][current.c] = trial;

            if (isValid(current.r, current.c)) {
                dfs();
            }

            table[current.r][current.c] = 0;
        }

        targets.push(current);
    }

    private static boolean isValid(int r, int c) {

        int value = table[r][c];

        for (int i = 0; i < 9; i++) {
            if (table[r][i] == value && table[r][i] != 0 && i != c) {
                return false;
            }
            if (table[i][c] == value && table[i][c] != 0 && i != r) {
                return false;
            }
        }

        // region check
        int boxR = (r / 3) * 3;
        int boxC = (c / 3) * 3;
        for (int i = boxR; i < boxR + 3; i++) {
            for (int j = boxC; j < boxC + 3; j++) {
                if (table[i][j] == value && table[i][j] != 0 && i != r && j != c) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void showTable() {
        for (int[] row : table) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}

class Cord {

    int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
