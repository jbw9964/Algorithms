import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int[][] table;
    private static int totalCnt;
    private static int[] cntMap;
    private static boolean[][] filled;
    private static int ANSWER;

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < table.length && c >= 0 && c < table[r].length;
    }

    private static void init() throws IOException {

        table = new int[10][];

        for (int i = 0; i < 10; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int val : table[i]) {
                if (val == 1) {
                    totalCnt += 1;
                }
            }
        }

        cntMap = new int[5];
        Arrays.fill(cntMap, 5);

        filled = new boolean[10][10];
        ANSWER = Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {

        init();

        backtrack(0, 0);

        System.out.println(ANSWER == Integer.MAX_VALUE ? -1 : ANSWER);
    }

    private static void backtrack(int fillCnt, int elementCnt) {

        if (fillCnt >= totalCnt) {
            int paperCnt = 0;

            for (int cnt : cntMap) {
                paperCnt += 5 - cnt;
            }

            ANSWER = Math.min(ANSWER, paperCnt);
            return;
        }

        int r = elementCnt / 10;
        int c = elementCnt % 10;

        if (table[r][c] == 0 || filled[r][c]) {
            backtrack(fillCnt, elementCnt + 1);
            return;
        }

        for (int size = 5; size >= 1; size--) {

            if (available(size, r, c)) {
                cntMap[size - 1]--;
                fill(size, r, c);

                backtrack(fillCnt + size * size, elementCnt + size);

                unfill(size, r, c);
                cntMap[size - 1]++;
            }
        }
    }

    private static boolean available(int size, int r, int c) {
        if (cntMap[size - 1] <= 0) {
            return false;
        }

        for (int nR = r; nR < r + size; nR++) {
            for (int nC = c; nC < c + size; nC++) {
                if (
                        !inRange(nR, nC) ||
                        table[nR][nC] != 1 ||
                        filled[nR][nC]
                ) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void fill(int size, int r, int c) {
        for (int nR = r; nR < r + size; nR++) {
            for (int nC = c; nC < c + size; nC++) {
                if (!inRange(nR, nC) || filled[nR][nC]) {
                    throw new RuntimeException();
                }
                filled[nR][nC] = true;
            }
        }
    }

    private static void unfill(int size, int r, int c) {
        for (int nR = r; nR < r + size; nR++) {
            for (int nC = c; nC < c + size; nC++) {
                if (!inRange(nR, nC) || !filled[nR][nC]) {
                    throw new RuntimeException();
                }
                filled[nR][nC] = false;
            }
        }
    }
}
