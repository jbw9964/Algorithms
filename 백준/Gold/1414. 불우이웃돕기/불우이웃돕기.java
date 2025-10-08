import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static PriorityQueue<Edge> edgePq;
    private static int[] parents;
    private static long TOTAL_SUM;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        edgePq = new PriorityQueue<>();
        parents = IntStream.range(0, N).toArray();

        for (int i = 0; i < N; i++) {
            char[] costTable = br.readLine().toCharArray();

            for (int j = 0; j < costTable.length; j++) {

                char c = costTable[j];
                if (c == '0') {
                    continue;
                }

                long len = toLen(c);
                edgePq.add(new Edge(i, j, len));
                TOTAL_SUM += len;
            }
        }
    }

    private static long toLen(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 27;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static int findParent(int v) {
        return parents[v] == v ? v : (parents[v] = findParent(parents[v]));
    }

    public static void main(String[] args) throws IOException {

        init();

        int edgeCnt = 0;
        long edgeCostSum = 0;

        while (!edgePq.isEmpty() && edgeCnt < N - 1) {

            Edge edge = edgePq.poll();
            int v1 = edge.v1;
            int v2 = edge.v2;

            int p1 = findParent(v1);
            int p2 = findParent(v2);

            if (p1 == p2) {
                continue;
            }

            int root = Math.min(p1, p2);
            int child = Math.max(p1, p2);

            parents[child] = root;

            edgeCostSum += edge.cost;
            edgeCnt++;
        }

        if (edgeCnt < N - 1) {
            System.out.println(-1);
        } else {
            System.out.println(TOTAL_SUM - edgeCostSum);
        }
    }
}

class Edge implements Comparable<Edge> {

    final int v1, v2;
    final long cost;

    public Edge(int v1, int v2, long cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return "Edge{" +
               "v1=" + v1 +
               ", v2=" + v2 +
               ", cost=" + cost +
               '}';
    }
}
