import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static List<Edge>[] graph;

    private static int start, end;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new List[N];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            long cost = Integer.parseInt(st.nextToken());

            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }

            graph[from].add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;
    }

    public static void main(String[] args) throws IOException {

        init();

        long minCost = getMinCost(start, end);

        System.out.println(minCost);
    }

    private static long getMinCost(int start, int end) {

        boolean[] visited = new boolean[N];
        visited[start] = true;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.addAll(graph[start]);

        long[] minCostTable = new long[N];
        Arrays.fill(minCostTable, Long.MAX_VALUE);
        minCostTable[start] = 0;

        while (!pq.isEmpty() && !visited[end]) {
            Edge e = pq.poll();

            int eTo = e.to;
            long eCost = e.cost;

            if (visited[eTo]) {
                continue;
            }

            visited[eTo] = true;
            minCostTable[eTo] = e.cost;

            List<Edge> newNeighbours = graph[eTo];
            if (newNeighbours == null) {
                continue;
            }
            for (Edge neighbour : newNeighbours) {

                if (
                        visited[neighbour.to] ||
                        eCost + neighbour.cost >= minCostTable[neighbour.to]
                ) {
                    continue;
                }

                pq.add(new Edge(neighbour.to, eCost + neighbour.cost));
            }
        }

        return minCostTable[end];
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
        return Long.compare(this.cost, o.cost);
    }

    @Override
    public String toString() {
        return "Edge{" +
               "to=" + to +
               ", cost=" + cost +
               '}';
    }
}
