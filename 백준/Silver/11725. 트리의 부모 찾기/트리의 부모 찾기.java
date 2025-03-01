import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static List<Edge>[] adjacent;
    private static int[] parents;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        adjacent = new List[N + 1];
        parents = new int[N + 1];

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if (adjacent[v1] == null) {
                adjacent[v1] = new ArrayList<>();
            }
            if (adjacent[v2] == null) {
                adjacent[v2] = new ArrayList<>();
            }

            adjacent[v1].add(new Edge(v1, v2));
            adjacent[v2].add(new Edge(v2, v1));
        }
    }

    public static void main(String[] args) throws IOException {

        init();
        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        
        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void dfs(int node, int parent)   {

        if (parents[node] != 0) {
            return;
        }

        parents[node] = parent;

        List<Edge> edges = adjacent[node];

        if (edges != null) {
            for (Edge edge : edges) {
                dfs(edge.to, node);
            }
        }
    }
}

class Edge {

    final int from, to;

    Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }
}
