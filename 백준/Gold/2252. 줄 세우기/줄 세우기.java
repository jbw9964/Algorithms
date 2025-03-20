import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final Map<Integer, List<Integer>> adjacent = new HashMap<>();
    private static int N, M;
    private static int[] inDegree;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (!adjacent.containsKey(from)) {
                adjacent.put(from, new ArrayList<>());
            }

            adjacent.get(from).add(to);
            inDegree[to]++;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        Queue<Integer> q = IntStream.rangeClosed(1, N)
                .filter(v -> inDegree[v] == 0)
                .boxed().collect(Collectors.toCollection(ArrayDeque::new));

        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {

            int root = q.poll();
            sb.append(root).append(" ");

            for (int adj : getAdjacent(root)) {
                if (--inDegree[adj] == 0) {
                    q.add(adj);
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static List<Integer> getAdjacent(int v) {
        List<Integer> result = new ArrayList<>();

        if (adjacent.containsKey(v)) {
            result.addAll(adjacent.get(v));
        }

        return result;
    }
}
