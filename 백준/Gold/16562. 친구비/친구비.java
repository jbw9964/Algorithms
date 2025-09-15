import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static long K;
    private static PriorityQueue<Edge> edges;
    private static int[] parents;

    private static final int root = 0;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        edges = new PriorityQueue<>(N);
        st = new StringTokenizer(br.readLine());
        for (int to = 1; to <= N; to++) {
            long cost = Long.parseLong(st.nextToken());
            edges.add(new Edge(to, cost));
        }

        parents = IntStream.rangeClosed(0, N).toArray();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            int p1 = findParent(v1);
            int p2 = findParent(v2);

            if (p1 == p2) {
                continue;
            }

            int root = Math.min(p1, p2);
            int child = Math.max(p1, p2);

            parents[child] = root;
        }

    }

    private static int findParent(int v) {
        return parents[v] == v ? v : (parents[v] = findParent(parents[v]));
    }

    public static void main(String[] args) throws IOException {

        init();

        long minimumCost = getMstCost(root);

        if (minimumCost > K) {
            System.out.println("Oh no");
        } else {
            System.out.println(minimumCost);
        }

//        int rootParent = findParent(root);
//
//        boolean isEveryoneFriend = IntStream.rangeClosed(1, N)
//                .map(Main::findParent)
//                .allMatch(p -> p == rootParent);
//
//        if (minimumCost > K || !isEveryoneFriend) {
//            System.out.println("On no");
//        } else {
//            System.out.println(minimumCost);
//        }
    }

    private static long getMstCost(int start) {

        long costSum = 0;
        edges.add(new Edge(start, 0));

        while (!edges.isEmpty()) {
            Edge e = edges.poll();
            int to = e.to;

            int startParent = findParent(start);
            int toParent = findParent(to);

            if (startParent == toParent) {
                continue;
            }

            costSum += e.cost;

            int root = Math.min(startParent, toParent);
            int child = Math.max(startParent, toParent);

            parents[child] = root;
        }

        return costSum;
    }
}

class Edge implements Comparable<Edge> {

    final int to;
    final long cost;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
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
               '}';
    }
}
