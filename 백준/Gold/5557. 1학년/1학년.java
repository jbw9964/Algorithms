import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int first = numbers[0];
        int last = numbers[numbers.length - 1];

        long[][] cntTable = new long[N - 1][21];
        cntTable[0][first]++;

        for (int i = 1; i < N - 1; i++) {
            long[] prevCntRow = cntTable[i - 1];
            List<Integer> indices = getIndices(prevCntRow);

            int currentNumber = numbers[i];
            for (int val : indices) {
                long prevCnt = cntTable[i - 1][val];

                if (val + currentNumber <= 20) {
                    cntTable[i][val + currentNumber] += prevCnt;
                }
                if (val - currentNumber >= 0) {
                    cntTable[i][val - currentNumber] += prevCnt;
                }
            }
        }

        long ans = cntTable[N - 2][last];
        System.out.println(ans);
    }

    private static List<Integer> getIndices(long[] counts) {
        return IntStream.range(0, counts.length)
                .filter(i -> counts[i] > 0)
                .boxed()
                .collect(Collectors.toList());
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
