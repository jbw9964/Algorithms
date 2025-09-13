import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int TOTAL_SUM;
    private static int[] peoples;
    private static List<Edge>[] graph;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        peoples = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        TOTAL_SUM = Arrays.stream(peoples).sum();

        //noinspection unchecked
        graph = new List[N];

        StringTokenizer st;
        for (int from = 0; from < N; from++) {

            if (graph[from] == null) {
                graph[from] = new ArrayList<>(10);
            }

            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {

                int to = Integer.parseInt(st.nextToken()) - 1;
                graph[from].add(new Edge(to));

                if (graph[to] == null) {
                    graph[to] = new ArrayList<>(10);
                }
                graph[to].add(new Edge(from));
            }

        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int minDiff = Integer.MAX_VALUE;

        List<List<Integer>> everyPossibleCombinations = getCombination();

        for (List<Integer> comb : everyPossibleCombinations) {

            if (!satisfiesConn(comb)) {
                continue;
            }

            int sum = comb.stream()
                    .mapToInt(i -> peoples[i])
                    .sum();
            int other = TOTAL_SUM - sum;
            int diff = Math.abs(other - sum);

            minDiff = Math.min(minDiff, diff);
        }

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    private static boolean satisfiesConn(List<Integer> singleCase) {

        Set<Integer> tempSet = IntStream.range(0, N).boxed().collect(Collectors.toSet());
        singleCase.forEach(tempSet::remove);

        List<Integer> oppositeCase = new ArrayList<>(tempSet);

        return checkConn(singleCase) && checkConn(oppositeCase);
    }

    private static boolean checkConn(List<Integer> combination) {

        if (combination == null || combination.isEmpty()) {
            return false;
        }

        Set<Integer> set = new HashSet<>(combination);

        boolean[] visited = new boolean[N];

        int start = combination.get(0);
        visited[start] = true;

        Queue<Edge> q = new LinkedList<>();
        q.add(new Edge(start));

        while (!q.isEmpty()) {
            Edge edge = q.poll();

            int from = edge.to;

            List<Edge> neighbours = graph[from];
            if (neighbours == null || neighbours.isEmpty()) {
                continue;
            }

            for (Edge n : neighbours) {
                int to = n.to;
                if (set.contains(to) && !visited[to]) {
                    visited[to] = true;
                    q.add(n);
                }
            }
        }

        return combination.stream()
                .allMatch(i -> visited[i]);
    }

    private static List<List<Integer>> getCombination() {

        List<List<Integer>> result = new ArrayList<>();

        for (int maxUsed = 1; maxUsed <= N / 2; maxUsed++) {
            boolean[] used = new boolean[N];
            getCombination(used, 0, maxUsed, -1, result);
        }

        return result;
    }

    private static void getCombination(
            boolean[] used, int usedCnt, int maxUsed, int prevIndex,
            List<List<Integer>> dst
    ) {

        if (usedCnt == maxUsed) {
            List<Integer> comb = new ArrayList<>(usedCnt);

            for (int i = 0; i < used.length; i++) {
                if (used[i]) {
                    comb.add(i);
                }
            }

            dst.add(comb);
            return;
        }

        for (int trial = prevIndex + 1; trial < used.length; trial++) {

            if (!used[trial]) {
                used[trial] = true;

                getCombination(used, usedCnt + 1, maxUsed, trial, dst);

                used[trial] = false;
            }

        }
    }
}

class Edge {

    final int to;

    public Edge(int to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Edge{" +
               "to=" + to +
               '}';
    }
}
