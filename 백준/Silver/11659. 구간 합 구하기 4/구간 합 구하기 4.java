import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static long[] sumTable;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] tmp = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        sumTable = new long[n + 1];
        System.arraycopy(tmp, 0, sumTable, 1, tmp.length);

        initSumTable();

        StringBuilder sb = new StringBuilder();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            long val = getSum(i, j);

            sb.append(val).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void initSumTable() {
        for (int i = 2; i < sumTable.length; i++) {
            sumTable[i] += sumTable[i - 1];
        }
    }

    private static long getSum(int i, int j) {
        return sumTable[j] - sumTable[i - 1];
    }
}