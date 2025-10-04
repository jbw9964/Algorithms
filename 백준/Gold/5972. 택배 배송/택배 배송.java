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

            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            if (graph[v1] == null) {
                graph[v1] = new ArrayList<>();
            }
            graph[v1].add(new Edge(v2, cost));

            if (graph[v2] == null) {
                graph[v2] = new ArrayList<>();
            }
            graph[v2].add(new Edge(v1, cost));
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = getMinCostWith(0, N - 1);

        System.out.println(answer);
    }

    private static int getMinCostWith(int start, int end) {

        int[] costTable = new int[N];
        Arrays.fill(costTable, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty() && !visited[end]) {

            Edge e = pq.poll();
            int to = e.to;
            int baseCost = e.cost;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            costTable[to] = baseCost;

            List<Edge> candidates = graph[to];
            if (candidates == null) {
                continue;
            }

            for (Edge c : candidates) {
                int to2 = c.to;
                int cost = baseCost + c.cost;

                if (!visited[to2] && cost < costTable[to2]) {
                    costTable[to2] = cost;
                    pq.add(new Edge(to2, cost));
                }
            }
        }

        return costTable[end];
    }
}

class Edge implements Comparable<Edge> {

    final int to, cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return "Edge{" +
               "to=" + to +
               ", cost=" + cost +
               '}';
    }
}
