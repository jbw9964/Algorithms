import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static List<Edge>[] graph;
    private static long TOTAL_COST, VISIT_CNT;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //noinspection unchecked
        graph = new List[N];
        TOTAL_COST = VISIT_CNT = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            if (graph[v1] == null)  {
                graph[v1] = new ArrayList<>();
            }
            graph[v1].add(new Edge(v2, cost));

            if (graph[v2] == null)  {
                graph[v2] = new ArrayList<>();
            }
            graph[v2].add(new Edge(v1, cost));

            TOTAL_COST += cost;
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        long mstCost = getMstCost(0);

        System.out.println(VISIT_CNT == N ? TOTAL_COST - mstCost : -1);
    }

    private static long getMstCost(int start) {

        boolean[] visited = new boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        long costSum = 0;
        while (!pq.isEmpty() && VISIT_CNT < N) {
            Edge curr = pq.poll();

            int to = curr.to;
            long cost = curr.cost;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            costSum += cost;
            VISIT_CNT++;

            List<Edge> candidates = graph[to];
            if (candidates!=null && !candidates.isEmpty()) {
                pq.addAll(candidates);
            }
        }

        return costSum;
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
