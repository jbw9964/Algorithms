import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] parents;
    private static PriorityQueue<Edge> pq;
    private static long TOTAL_COST, EDGE_CNT;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = IntStream.range(0, N).toArray();
        pq = new PriorityQueue<>(M);
        TOTAL_COST = EDGE_CNT = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Edge(v1, v2, cost));
            TOTAL_COST += cost;
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long mstCost = getMstCost();

        // N 개의 vertex 로 이루어진 MST 그래프는
        // 반드시 N - 1 개의 edge 를 가진다.

        System.out.println(EDGE_CNT == N - 1 ? TOTAL_COST - mstCost : -1);
    }

    private static long getMstCost() {

        long costSum = 0;

        while (!pq.isEmpty() && EDGE_CNT < N - 1) {

            Edge curr = pq.poll();

            int v1 = curr.v1, v2 = curr.v2;
            int p1 = getParent(v1), p2 = getParent(v2);

            if (p1 == p2)   {
                continue;
            }

            int root = Math.min(p1, p2);
            int child = Math.max(p1, p2);
            parents[child] = root;

            costSum += curr.cost;
            EDGE_CNT++;
        }

        return costSum;
    }

    private static int getParent(int v) {
        return parents[v] == v ? v : (parents[v] = getParent(parents[v]));
    }
}

class Edge implements Comparable<Edge> {

    int v1, v2;
    long cost;

    public Edge(int v1, int v2, long cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.cost, o.cost);
    }
}
