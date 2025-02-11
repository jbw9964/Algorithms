import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, EDGE_COST_TOTAL;
    private static int[][] costTable;
    private static int[] parents;
    private static final Set<Integer> visited = new HashSet<>();

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        costTable = new int[N][];

        for (int i = 0; i < N; i++) {
            costTable[i] = br.readLine().chars()
                    .map(Main::convertCost)
                    .toArray();

            EDGE_COST_TOTAL += Arrays.stream(costTable[i]).sum();
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {

                int cost1 = costTable[i][j];
                int cost2 = costTable[j][i];

                int minima = cost1 != 0 && cost2 != 0 ?
                        Math.min(cost1, cost2) :
                        cost1 == 0 ? cost2 : cost1;

                costTable[i][j] = minima;
                costTable[j][i] = minima;
            }
        }

        parents = IntStream.range(0, N + 1).toArray();
    }

    private static int convertCost(int ch) {
        return ch == '0' ? 0 :
                ch >= 'a' ?
                        ch - 'a' + 1 :
                        ch - 'A' + 27;
    }

    public static int findRootParent(int v) {
        return v != parents[v] ?
                (parents[v] = findRootParent(parents[v])) : v;
    }


    public static void main(String[] args) throws IOException {

        init();

        int cost = getMstEdgeCosts();

        System.out.println(visited.size() != N ?
                -1 : EDGE_COST_TOTAL - cost
        );

    }

    private static int getMstEdgeCosts()    {
        int cost = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] edgeCostCache = new int[N];
        Arrays.fill(edgeCostCache, Integer.MAX_VALUE);
        visited.add(0);

        for (int next = 1; next < N; next++) {
            int nextCost = costTable[0][next];

            if (nextCost != 0)  {
                edgeCostCache[next] = nextCost;

                pq.add(new Edge(0, next, nextCost));
            }
        }


        while (!pq.isEmpty() && visited.size() < N)   {

            Edge edge = pq.poll();
            int from = edge.from, to = edge.to;

            if (visited.contains(to))   {
                continue;
            }

            int parentFrom = findRootParent(from);
            int parentTo = findRootParent(to);

            if (parentFrom == parentTo) {
                continue;
            }

            cost += edge.cost;
            visited.add(to);
            parents[parentTo] = parentFrom;

            for (int next = 1; next < N; next++) {
                int nextCost = costTable[to][next];

                if (visited.contains(next) || nextCost == 0 ||
                        nextCost > edgeCostCache[next])    {
                    continue;
                }

                edgeCostCache[next] = nextCost;
                pq.add(new Edge(to, next, nextCost));
            }
        }

        return cost;
    }
}

class Edge implements Comparable<Edge> {
    int from, to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}