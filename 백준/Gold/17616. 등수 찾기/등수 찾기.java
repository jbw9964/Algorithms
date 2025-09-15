import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, X;
    private static List<Integer>[] forwardGraph;
    private static List<Integer>[] backwardGraph;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        forwardGraph = new List[N + 1];
        backwardGraph = new List[N + 1];
        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (forwardGraph[from] == null) {
                forwardGraph[from] = new ArrayList<>();
            }
            forwardGraph[from].add(to);

            if (backwardGraph[to] == null) {
                backwardGraph[to] = new ArrayList<>();
            }
            backwardGraph[to].add(from);
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int lowerCount = countVisitFrom(X, forwardGraph);
        int upperCount = countVisitFrom(X, backwardGraph);

        int fastest, slowest;
        fastest = upperCount;
        slowest = N - lowerCount + 1;

        System.out.printf("%d %d\n", fastest, slowest);
    }

    private static int countVisitFrom(int start, List<Integer>[] graph) {

        boolean[] visited = new boolean[N + 1];
        int count = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {

            int curr = queue.poll();
            count++;

            List<Integer> adjacent = graph[curr];
            if (adjacent == null)   {
                continue;
            }

            for (int next : adjacent) {

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }

            }
        }

        return count;
    }
}
