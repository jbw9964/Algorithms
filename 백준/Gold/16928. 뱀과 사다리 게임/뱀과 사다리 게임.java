import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static final List<Edge>[] adjacentList = new List[100 + 1];

    private static void init() throws IOException {

        for (int i = 1; i <= 100; i++) {
            adjacentList[i] = new ArrayList<>(6);
            for (
                    int j = i + 1;
                    j <= 100 && j <= i + 6;
                    j++
            ) {
                adjacentList[i].add(new Edge(j, 1));
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjacentList[from].clear();
            adjacentList[from].add(new Edge(to, 0));
        }
    }


    public static void main(String[] args) throws IOException {

        init();

        int answer = getMinCostOn(1, 100);
        System.out.println(answer);
    }

    private static int getMinCostOn(int fromX, int toX) {

        int[] costTable = new int[adjacentList.length];
        Arrays.fill(costTable, Integer.MAX_VALUE);
        costTable[fromX] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.addAll(adjacentList[fromX]);

        boolean[] visited = new boolean[adjacentList.length];
        visited[fromX] = true;

        while (!pq.isEmpty() && !visited[toX]) {
            Edge e = pq.poll();
            int to = e.to;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            int baseCost = (costTable[to] = e.cost);

            List<Edge> candidates = adjacentList[to];
            for (Edge candidate : candidates) {

                int to2 = candidate.to;
                int cost = baseCost + candidate.cost;

                if (visited[to2] || cost >= costTable[to2]) {
                    continue;
                }

                costTable[to2] = cost;
                pq.add(new Edge(to2, cost));
            }
        }

        return costTable[toX];
    }
}

class Edge implements Comparable<Edge> {

    int to, cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }

    @Override
    public String toString() {
        return "Edge{" +
               "to=" + to +
               ", cost=" + cost +
               '}';
    }
}