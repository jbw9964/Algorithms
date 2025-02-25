import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();


        long[][] cntTable = new long[N - 1][21];
        cntTable[0][numbers[0]]++;

        for (int i = 1; i < N - 1; i++) {
            int currentNum = numbers[i];

            for (int num = 0; num <= 20; num++) {
                long cnt = cntTable[i - 1][num];
                if (cnt <= 0)   {
                    continue;
                }

                if (num + currentNum <= 20) {
                    cntTable[i][num + currentNum] += cnt;
                }
                if (num - currentNum >= 0) {
                    cntTable[i][num - currentNum] += cnt;
                }
            }
        }

        long ans = cntTable[N - 2][numbers[N - 1]];
        System.out.println(ans);
    }

    private static void showRow(long[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] > 0) {
                System.out.printf("%2d(%2d)", i, row[i]);
            }
            else {
                System.out.print("      ");
            }
        }
        System.out.println();
    }
}
