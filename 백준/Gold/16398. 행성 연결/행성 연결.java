import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[][] costs;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        costs = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long ans = getMstCost();

        System.out.println(ans);
    }

    private static long getMstCost() {

        long cost = 0;
        int mstSize = 0;

        PriorityQueue<Edge> candidates = new PriorityQueue<>();

        boolean[] visited = new boolean[N];

        int[] minimaEdgeCostCache = new int[N];
        Arrays.fill(minimaEdgeCostCache, Integer.MAX_VALUE);

        candidates.add(new Edge(0, 0));

        while (!candidates.isEmpty() && mstSize < N) {

            Edge cheapest = candidates.poll();
            int newVertex = cheapest.index;

            if (visited[newVertex]) {
                continue;
            }

            cost += cheapest.cost;
            visited[newVertex] = true;
            mstSize++;

            // add candidates from newVertex's neighbor
            for (int i = 0; i < N; i++) {
                int candidateCost = costs[cheapest.index][i];

                // filter MST included neighbors
                // add to candidates when cost is cheaper than cached
                if (!visited[i] && i != newVertex &&
                        candidateCost < minimaEdgeCostCache[i]) {
                    candidates.add(new Edge(i, candidateCost));
                }
            }
        }

        return cost;
    }
}

class Edge implements Comparable<Edge> {

    int index, cost;

    public Edge(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}