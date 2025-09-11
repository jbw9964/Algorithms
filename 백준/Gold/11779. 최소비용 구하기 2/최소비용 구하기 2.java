import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static List<Edge>[] graph;
    private static int START, END;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        //noinspection unchecked
        graph = new List[N + 1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            graph[from].add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {

        init();

        Result result = solve(START, END);

        int[] pathTable = result.pathTable;
        Stack<Integer> pathStack = new Stack<>();

        int i = END;
        while (true) {

            pathStack.push(i);

            if (i == START) {
                break;
            }

            i = pathTable[i];
        }

        long minCost = result.minCost;
        int size = pathStack.size();

        System.out.println(minCost);
        System.out.println(size);
        while (!pathStack.isEmpty()) {
            System.out.print(pathStack.pop() + " ");
        }
        System.out.println();
    }

    private static Result solve(int start, int end) {

        long[] costTable = new long[N + 1];
        Arrays.fill(costTable, Long.MAX_VALUE);

        boolean[] visited = new boolean[N + 1];
        int[] pathTable = new int[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0, 0));

        while (!pq.isEmpty() && !visited[end]) {
            Edge curr = pq.poll();

            int to = curr.to;
            long baseCost = curr.cost;
            int prev = curr.prev;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            costTable[to] = baseCost;
            pathTable[to] = prev;

            List<Edge> candidates = graph[to];
            if (candidates == null || candidates.isEmpty()) {
                continue;
            }

            for (Edge candidate : candidates) {
                int to2 = candidate.to;
                long cost = candidate.cost + baseCost;

                if (visited[to2] || costTable[to2] <= cost) {
                    continue;
                }

                pq.add(new Edge(to2, cost, to));
            }
        }

        return new Result(costTable[END], pathTable);
    }
}

class Result {

    final long minCost;
    final int[] pathTable;

    public Result(long minCost, int[] pathTable) {
        this.minCost = minCost;
        this.pathTable = pathTable;
    }
}

class Edge implements Comparable<Edge> {

    int to;
    long cost;
    Integer prev;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
        prev = null;
    }

    public Edge(int to, long cost, int prev) {
        this.to = to;
        this.cost = cost;
        this.prev = prev;
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
               ", prev=" + prev +
               '}';
    }
}
