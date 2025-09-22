import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static List<Edge>[] graph;
    private static int start, end;
    private static boolean[] visited;
    private static Queue<Integer> queue;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //noinspection unchecked
        graph = new List[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int maxWeight = Integer.parseInt(st.nextToken());

            if (graph[v1] == null) {
                graph[v1] = new LinkedList<>();
            }
            if (graph[v2] == null) {
                graph[v2] = new LinkedList<>();
            }

            graph[v1].add(new Edge(v2, maxWeight));
            graph[v2].add(new Edge(v1, maxWeight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        queue = new LinkedList<>();
    }

    public static void main(String[] args) throws IOException {

        init();

        int left = 1, right = 1_000_000_000;
        while (left < right - 1) {

            int mid = (left + right) >>> 1;

            boolean traversable = bfs(start, end, mid);

            if (traversable) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        int answer = bfs(start, end, right) ? right : left;

        System.out.println(answer);
    }

    private static boolean bfs(int start, int end, int weight) {

        Arrays.fill(visited, false);
        visited[start] = true;

        queue.clear();
        queue.add(start);

        while (!queue.isEmpty() && !visited[end]) {
            int curr = queue.poll();

            List<Edge> edges = graph[curr];
            if (edges == null)  {
                continue;
            }

            for (Edge edge : edges) {
                int to = edge.to;
                int maxWeight = edge.maxWeight;

                if (visited[to] || maxWeight < weight) {
                    continue;
                }

                visited[to] = true;
                queue.add(to);
            }
        }

        return visited[end];
    }
}

class Edge {

    final int to, maxWeight;

    public Edge(int to, int maxWeight) {
        this.to = to;
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return "Edge{" +
               "to=" + to +
               ", maxWeight=" + maxWeight +
               '}';
    }
}
