import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[][] costTable;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        costTable = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                costTable[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long ans = getMstCost(0);

        System.out.println(ans);
    }

    private static long getMstCost(int initIndex) {

        long cost = 0;
        int cnt = 0;

        boolean[] visited = new boolean[N];
        visited[initIndex] = true;

        PriorityQueue<Edge> edgeCandidates = new PriorityQueue<>(
                Comparator.comparing(e -> e.cost)
        );
        int[] minimaEdgeCostCache = new int[N];

        // init candidates & edge cost cache
        for (int i = 0; i < N; i++) {
            int candidateCost = costTable[initIndex][i];

            if (i != initIndex) {
                edgeCandidates.add(new Edge(initIndex, i, candidateCost));
                minimaEdgeCostCache[i] = candidateCost;
            }
        }

        while (!edgeCandidates.isEmpty() && cnt < N - 1) {

            Edge optimalEdge = edgeCandidates.poll();
            int newVertex = optimalEdge.v;

            if (visited[newVertex]) {
                continue;
            }

            cost += optimalEdge.cost;
            visited[newVertex] = true;

            for (int i = 0; i < N; i++) {
                int candidateCost = costTable[optimalEdge.v][i];

                // add candidates who's cost is cheaper than cached
                if (!visited[i] && i != newVertex &&
                        candidateCost < minimaEdgeCostCache[i]) {
                    edgeCandidates.add(new Edge(newVertex, i, candidateCost));
                }
            }

            cnt++;
        }

        return cost;
    }
}

class Edge {

    int root, v, cost;

    public Edge(int root, int v, int cost) {
        this.root = root;
        this.v = v;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "root=" + root +
                ", v=" + v +
                ", cost=" + cost +
                '}';
    }
}