import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, START, END;
    private static List<Edge>[] graph;
    private static long[] profits;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String[] inputs = new String[M];
        for (int i = 0; i < M; i++) {
            inputs[i] = br.readLine();
        }

        profits = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        //noinspection unchecked
        graph = IntStream.range(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (String input : inputs) {

            st = new StringTokenizer(input);

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int travelCost = Integer.parseInt(st.nextToken());

            long cost = profits[to] - travelCost;

            graph[from].add(new Edge(to, cost));
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long[] profitTable = getProfitTable(START);
        long profit = profitTable[END];

        if (profit == Integer.MIN_VALUE) {
            System.out.println("gg");
            return;
        }

        long[] copy = Arrays.copyOf(profitTable, profitTable.length);
        updateTable(copy);

        int[] negativeCycleCandidates = IntStream.range(0, N)
                .filter(i -> profitTable[i] != copy[i])
                .toArray();

        boolean[] visited = new boolean[N];
        Queue<Integer> q = new ArrayDeque<>();
        for (int c : negativeCycleCandidates) {
            visited[c] = true;
            q.add(c);
        }

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == END) {
                System.out.println("Gee");
                return;
            }

            for (Edge adj : graph[curr]) {
                if (!visited[adj.to]) {
                    visited[adj.to] = true;
                    q.add(adj.to);
                }
            }
        }

        System.out.println(profitTable[END]);
    }

    private static long[] getProfitTable(int from) {

        long[] profitTable = new long[N];
        Arrays.fill(profitTable, Integer.MIN_VALUE);
        profitTable[from] = profits[from];

        for (int iter = 0; iter < N - 1; iter++) {
            if (!updateTable(profitTable)) {
                return profitTable;
            }
        }

        return profitTable;
    }

    private static boolean updateTable(long[] table) {

        boolean updated = false;
        for (int v = 0; v < N; v++) {

            if (table[v] == Integer.MIN_VALUE) {
                continue;
            }

            long base = table[v];
            for (Edge adj : graph[v]) {

                int to = adj.to;
                long cost = base + adj.cost;

                if (table[to] <= cost) {
                    table[to] = cost;
                    updated = true;
                }
            }
        }

        return updated;
    }
}

class Edge {

    final int to;
    final long cost;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }
}
