import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, Q;
    private static Edge[] edges;
    private static Stack<Edge> removalStack;
    private static long[] groupCnt;
    private static int[] parent;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        edges = new Edge[M];
        Set<Edge> remainEdges = new HashSet<>(M);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(v1, v2);
            remainEdges.add(edges[i]);
        }

        removalStack = new Stack<>();
        for (int i = 0; i < Q; i++) {
            int removalIndex = Integer.parseInt(br.readLine()) - 1;
            removalStack.push(edges[removalIndex]);
            remainEdges.remove(edges[removalIndex]);
        }

        groupCnt = new long[N + 1];
        Arrays.fill(groupCnt, 1);
        parent = IntStream.rangeClosed(0, N).toArray();

        for (Edge e : remainEdges) {
            int v1 = e.v1, v2 = e.v2;

            int p1 = findParent(v1);
            int p2 = findParent(v2);

            if (p1 == p2) {
                continue;
            }

            long cnt = groupCnt[p1] + groupCnt[p2];
            groupCnt[p1] = groupCnt[p2] = cnt;

            if (p1 < p2) {
                parent[p2] = p1;
            } else {
                parent[p1] = p2;
            }
        }
    }

    private static int findParent(int v) {
        return parent[v] == v ? v : (parent[v] = findParent(parent[v]));
    }

    public static void main(String[] args) throws IOException {

        init();

        long answer = 0;
        while (!removalStack.isEmpty()) {

            Edge add = removalStack.pop();

            int v1 = add.v1, v2 = add.v2;

            int p1 = findParent(v1);
            int p2 = findParent(v2);

            if (p1 == p2) {
                continue;
            }

            long cnt1 = groupCnt[p1];
            long cnt2 = groupCnt[p2];

            answer += cnt1 * cnt2;

            groupCnt[p1] = groupCnt[p2] = cnt1 + cnt2;

            if (p1 < p2) {
                parent[p2] = p1;
            } else {
                parent[p1] = p2;
            }
        }

        System.out.println(answer);
    }
}

class Edge {

    final int v1, v2;

    public Edge(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}
