import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, K, W;
    private static int[] costs;
    private static int[] inDegrees;
    private static List<Integer>[] graph;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        costs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        inDegrees = new int[N];

        //noinspection unchecked
        graph = IntStream.range(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            graph[from].add(to);
            inDegrees[to]++;
        }

        W = Integer.parseInt(br.readLine());
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {
            init();

            Queue<Integer> q = new ArrayDeque<>(N);
            int[] table = new int[N];

            for (int i = 0; i < N; i++) {
                if (inDegrees[i] == 0) {
                    table[i] = costs[i];
                    q.add(i);
                }
            }

            while (!q.isEmpty()) {
                int curr = q.poll();
                int currVal = table[curr];

                for (int adj : graph[curr]) {

                    table[adj] = Math.max(
                            table[adj], currVal + costs[adj]
                    );

                    if (--inDegrees[adj] == 0) {
                        q.add(adj);
                    }
                }
            }

            answer.append(table[W - 1]).append("\n");
        }

        System.out.println(answer.toString());
    }
}
