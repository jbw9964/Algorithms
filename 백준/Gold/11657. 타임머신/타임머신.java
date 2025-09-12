import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static List<Edge>[] graph;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //noinspection unchecked
        graph = new List[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            long weight = Long.parseLong(st.nextToken());

            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            graph[from].add(new Edge(to, weight));
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long[] costTable = bellmanFord(0);

        boolean hasNegativeCycle = updateCostTable(costTable);

        if (hasNegativeCycle) {
            System.out.println(-1);
            return;
        }

        for (int i = 1; i < N; i++) {
            long cost = costTable[i];
            System.out.println(cost == Integer.MAX_VALUE ? -1 : cost);
        }
    }

    private static long[] bellmanFord(int start) {

        long[] costTable = new long[N];
        Arrays.fill(costTable, Integer.MAX_VALUE);
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

            long base = costTable[from];
            if (base == Integer.MAX_VALUE) {
                continue;
            }

            List<Edge> edges = graph[from];
            if (edges == null) {
                continue;
            }

            for (Edge edge : edges) {
                int to = edge.to;
                long newWeight = base + edge.weight;

                if (newWeight < costTable[to]) {
                    costTable[to] = newWeight;
                    updated = true;
                }
            }
        }

        return updated;
    }
}

class Edge {

    final int to;
    final long weight;

    public Edge(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }
}
