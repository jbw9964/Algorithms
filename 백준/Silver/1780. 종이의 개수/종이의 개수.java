import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[][] numbers;
    private static int zeroCount, oneCount, minusCount;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        numbers = new int[N][];
        for (int i = 0; i < N; i++) {
            numbers[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        solve(0, 0, N);

        System.out.println(minusCount);
        System.out.println(zeroCount);
        System.out.println(oneCount);
    }

    private static void solve(int initR, int initC, int size) {

        int startVal = numbers[initR][initC];

        for (int r = initR; r < initR + size; r++) {
            for (int c = initC; c < initC + size; c++) {

                if (numbers[r][c] == startVal) {
                    continue;
                }

                int nextSize = size / 3;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int nextR = initR + i * nextSize;
                        int nextC = initC + j * nextSize;

                        solve(nextR, nextC, nextSize);
                    }
                }
                return;
            }
        }

        switch (startVal) {
            case 0:
                zeroCount++;
                return;
            case 1:
                oneCount++;
                return;
            case -1:
                minusCount++;
                return;
            default:
                throw new RuntimeException();
        }
    }
}
