import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, X;
    private static int[][] costTable;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        costTable = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            costTable[from][to] = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        int maxima = Integer.MIN_VALUE;
        for (int node = 1; node <= N; node++) {
            int go = getMinimumCost(node, X);
            int back = getMinimumCost(X, node);

            maxima = Math.max(maxima, go + back);
        }

        System.out.println(maxima);
    }

    private static int getMinimumCost(int from, int to) {
        if (from == to) {
            return 0;
        }

        int[] costCache = new int[N + 1];
        Arrays.fill(costCache, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N + 1];

        PriorityQueue<EdgeInfo> edges = new PriorityQueue<>();
        edges.add(new EdgeInfo(from, 0));

        while (!edges.isEmpty() && !visited[to]) {

            EdgeInfo optimal = edges.poll();
            int dst = optimal.to;

            if (visited[dst]) {
                continue;
            }

            visited[dst] = true;
            costCache[dst] = optimal.cost;

            int[] adjacentCosts = costTable[dst];
            for (int adj = 1; adj <= N; adj++) {
                int cost = adjacentCosts[adj];

                if (visited[adj] || cost == 0 || cost + optimal.cost > costCache[adj]) {
                    continue;
                }
                
                edges.add(new EdgeInfo(adj, cost + optimal.cost));
            }
        }

        return costCache[to];
    }
}

class EdgeInfo implements Comparable<EdgeInfo> {

    int to, cost;

    public EdgeInfo(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(EdgeInfo o) {
        return cost - o.cost;
    }

    @Override
    public String toString() {
        return "EdgeInfo{" +
                "to=" + to +
                ", cost=" + cost +
                '}';
    }
}
