import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dfs(new int[M], N, M, 0);

        System.out.println(sb.toString());
    }


    private static void dfs(int[] arr, int n, int m, int index) {

        if (index == m) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        LOOP:
        for (int trial = 1; trial <= n; trial++) {

            for (int j = 0; j < index; j++) {
                if (arr[j] == trial) {
                    continue LOOP;
                }
            }

            int prev = arr[index];
            arr[index] = trial;
            dfs(arr, n, m, index + 1);
            arr[index] = prev;
        }
    }
}
