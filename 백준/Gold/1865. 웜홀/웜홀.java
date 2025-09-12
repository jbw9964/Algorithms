import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, W;
    private static List<Edge>[] graph;

    @SuppressWarnings("DuplicatedCode")
    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        //noinspection unchecked
        graph = new List[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            long cost = Long.parseLong(st.nextToken());

            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            graph[from].add(new Edge(to, cost));

            if (graph[to] == null) {
                graph[to] = new ArrayList<>();
            }
            graph[to].add(new Edge(from, cost));
        }

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            long cost = Long.parseLong(st.nextToken());

            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            graph[from].add(new Edge(to, -cost));
        }
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        TEST_CASE:
        while (T-- > 0) {

            init();

            boolean[] visited = new boolean[N];

            for (int start = 0; start < N; start++) {
                if (visited[start]) {
                    continue;
                }

                long[] minCostTable = bellmanFord(start);

                boolean hasNegativeCycle = updateCostTable(minCostTable);

                if (hasNegativeCycle) {
                    answer.append("YES").append("\n");
                    continue TEST_CASE;
                }

                for (int v = 0; v < N; v++) {
                    if (minCostTable[v] != Long.MAX_VALUE)  {
                        visited[v] = true;
                    }
                }
            }

            answer.append("NO").append("\n");
        }

        System.out.print(answer.toString());
    }

    private static long[] bellmanFord(int start) {
        long[] costTable = new long[N];
        Arrays.fill(costTable, Long.MAX_VALUE);
        costTable[start] = 0;

        for (int iter = 0; iter < N - 1; iter++) {
            boolean updated = updateCostTable(costTable);

            if (!updated) {
                return costTable;
            }
        }

        return costTable;
    }

    private static boolean updateCostTable(long[] costTable) {

        boolean updated = false;
        for (int from = 0; from < N; from++) {

            long baseCost = costTable[from];
            if (baseCost == Long.MAX_VALUE) {
                continue;
            }

            List<Edge> edges = graph[from];
            if (edges == null) {
                continue;
            }

            for (Edge e : edges) {
                int to = e.to;
                long newCost = baseCost + e.cost;

                if (newCost < costTable[to]) {
                    costTable[to] = newCost;
                    updated = true;
                }
            }
        }

        return updated;
    }
}

class Edge {

    final int to;
    final long cost;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }
}
