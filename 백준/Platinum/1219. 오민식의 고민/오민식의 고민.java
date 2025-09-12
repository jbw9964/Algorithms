import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int START, END;
    private static long[][] adjTable;
    private static long[] profits;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjTable = new long[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(adjTable[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            adjTable[from][to] = Math.min(adjTable[from][to], cost);
        }

        profits = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        for (int from = 0; from < N; from++) {
            for (int to = 0; to < N; to++) {

                if (adjTable[from][to] != Long.MAX_VALUE) {
                    adjTable[from][to] -= profits[to];
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long[] costTable = bellmanFord(START);
        long minCost = costTable[END];

        long[] reUpdatedTable = Arrays.copyOf(costTable, N);
        boolean updated = updateCostTable(reUpdatedTable);

        if (minCost == Long.MAX_VALUE) {
            System.out.println("gg");
            return;
        }

        if (!updated) {
            System.out.println(-minCost);
            return;
        }

        Queue<Integer> q = IntStream.range(0, N)
                .filter(i -> costTable[i] != reUpdatedTable[i])
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        boolean[] visited = new boolean[N];
        for (int v : q) {
            visited[v] = true;
        }

        while (!q.isEmpty()) {
            int from = q.poll();

            if (from == END) {
                System.out.println("Gee");
                return;
            }

            for (int to = 0; to < N; to++) {
                if (
                        adjTable[from][to] == Long.MAX_VALUE ||
                        visited[to]
                ) {
                    continue;
                }

                visited[to] = true;
                q.add(to);
            }
        }

        System.out.println(-minCost);
    }

    private static long[] bellmanFord(int start) {

        long[] costTable = new long[N];
        Arrays.fill(costTable, Long.MAX_VALUE);
        costTable[start] = -profits[start];

        for (int iter = 0; iter < N - 1; iter++) {
            boolean modified = updateCostTable(costTable);

            if (!modified) {
                return costTable;
            }
        }

        return costTable;
    }

    private static boolean updateCostTable(long[] costTable) {

        boolean modified = false;

        for (int from = 0; from < N; from++) {

            long baseCost = costTable[from];
            if (baseCost == Long.MAX_VALUE) {
                continue;
            }

            for (int to = 0; to < N; to++) {

                if (adjTable[from][to] == Long.MAX_VALUE) {
                    continue;
                }

                long newCost = baseCost + adjTable[from][to];
                if (newCost < costTable[to]) {
                    costTable[to] = newCost;
                    modified = true;
                }
            }
        }

        return modified;
    }
}
