import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static List<Edge>[] edges;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        edges = new List[N];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            long cost = Long.parseLong(st.nextToken());

            Edge e1 = new Edge(v2, cost);
            Edge e2 = new Edge(v1, cost);

            if (edges[v1] == null) {
                edges[v1] = new LinkedList<>();
            }
            if (edges[v2] == null) {
                edges[v2] = new LinkedList<>();
            }

            edges[v1].add(e1);
            edges[v2].add(e2);
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int primaryVertex1 = getPrimaryVertex(getCostTableFrom(0));
        long[] primaryCostTable1 = getCostTableFrom(primaryVertex1);

        int primaryVertex2 = getPrimaryVertex(primaryCostTable1);
        long[] primaryCostTable2 = getCostTableFrom(primaryVertex2);

        long[] concatenatedCostTable = new long[primaryCostTable1.length + primaryCostTable2.length];
        System.arraycopy(
                primaryCostTable1,
                0, concatenatedCostTable,
                0, primaryCostTable1.length
        );
        System.arraycopy(
                primaryCostTable2,
                0, concatenatedCostTable,
                primaryCostTable1.length, primaryCostTable2.length
        );
        Arrays.sort(concatenatedCostTable);

        System.out.println(concatenatedCostTable[concatenatedCostTable.length - 3]);
    }

    private static long[] getCostTableFrom(int node)    {

        boolean[] visited = new boolean[N];
        visited[node] = true;

        long[] costTable = new long[N];
        Arrays.fill(costTable, Long.MAX_VALUE);
        costTable[node] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(edges[node].size());
        pq.addAll(edges[node]);

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int to = e.to;

            if (visited[to])  {
                continue;
            }

            visited[to] = true;
            costTable[to] = e.cost;

            long baseCost = e.cost;
            List<Edge> newEdges = edges[to];

            for (Edge newEdge : newEdges) {
                if (visited[newEdge.to]) {
                    continue;
                }

                if (baseCost + newEdge.cost >= costTable[newEdge.to]) {
                    continue;
                }

                pq.add(new Edge(newEdge.to, baseCost + newEdge.cost));
            }
        }

        return costTable;
    }

    private static int getPrimaryVertex(long[] costTable)   {

        int index = 0;
        long maxima = Long.MIN_VALUE;
        for (int i = 0; i < costTable.length; i++) {
            if (costTable[i] > maxima) {
                maxima = costTable[i];
                index = i;
            }
        }

        return index;
    }
}

class Edge implements Comparable<Edge> {

    int to;
    long cost;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return "Edge{" +
               "to=" + to +
               ", cost=" + cost +
               '}';
    }
}