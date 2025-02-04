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

        dfs(new ArrayList<>(N), N, M);

        System.out.println(sb.toString());
    }


    private static void dfs(List<Integer> arr, int n, int m)    {

        if (arr.size() == m)    {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!arr.contains(i))    {
                arr.add(i);
                dfs(arr, n, m );
                arr.remove(arr.size() - 1);
            }
        }
    }
}
