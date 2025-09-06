import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static List<Edge>[] edgeList;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] visible = new boolean[N];

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 0; i < N - 2; i++) {
            if (st.nextToken().equals("1")) {
                visible[i + 1] = true;
            }
        }

        //noinspection unchecked
        edgeList = new List[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (visible[from] || visible[to]) {
                continue;
            }

            if (edgeList[from] == null) {
                edgeList[from] = new ArrayList<>();
            }
            edgeList[from].add(new Edge(to, cost));

            if (edgeList[to] == null) {
                edgeList[to] = new ArrayList<>();
            }
            edgeList[to].add(new Edge(from, cost));
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long minCost = getMinCostOn(0, N - 1);

        System.out.println(minCost == Long.MAX_VALUE ? -1 : minCost);
    }

    private static long getMinCostOn(int start, int end) {

        boolean[] visited = new boolean[N];

        long[] costTable = new long[N];
        Arrays.fill(costTable, Long.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty() && !visited[end]) {
            Edge e = pq.poll();

            int to = e.to;
            long baseCost = e.cost;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            costTable[to] = baseCost;

            List<Edge> candidates = edgeList[to];
            if (candidates == null || candidates.isEmpty()) {
                continue;
            }

            for (Edge candidate : candidates) {
                int to2 = candidate.to;
                long cost = baseCost + candidate.cost;

                if (visited[to2] || costTable[to2] <= cost) {
                    continue;
                }

                pq.add(new Edge(to2, cost));
            }
        }

        return costTable[end];
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
}
