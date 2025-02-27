import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int[] heights;

    public static void main(String[] args) throws IOException {

        final int N = 9;
        heights = new int[N];

        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(heights);

        dfs(0, 0, 0, new int[7]);
    }

    private static void dfs(int index, int cnt, int sum, int[] tmp)  {

        if (cnt == 7)   {

            if (sum == 100) {
                for (int ans : tmp) {
                    System.out.println(ans);
                }
                System.exit(0);
            }

            return;
        }
        if (index == heights.length) {
            return;
        }

        tmp[cnt] = heights[index];
        dfs(index + 1, cnt + 1, sum + heights[index], tmp);
        dfs(index + 1, cnt, sum, tmp);
    }
}
