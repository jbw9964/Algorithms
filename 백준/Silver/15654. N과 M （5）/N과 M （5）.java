import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] numbers;
    private static boolean[] visited;
    private static final StringBuilder ANS = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        visited = new boolean[N];

        Arrays.sort(numbers);

        dfs(0, 0, new int[M]);

        System.out.print(ANS.toString());
    }

    private static void dfs(int cnt, int index, int[] tmp) {

        if (cnt == M) {
            for (int val : tmp) {
                ANS.append(val).append(" ");
            }
            ANS.append("\n");

            return;
        }

        int prev = tmp[cnt];
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            int trial = numbers[i];
            visited[i] = true;
            tmp[cnt] = trial;
            dfs(cnt + 1, index + 1, tmp);
            tmp[cnt] = prev;
            visited[i] = false;
        }

    }
}
