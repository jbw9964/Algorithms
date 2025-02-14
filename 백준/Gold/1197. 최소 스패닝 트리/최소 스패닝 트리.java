
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int[] parents;
    private static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parents = IntStream.range(0, V + 1).toArray();
        pq = new PriorityQueue<>(E);

        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Edge(v1, v2, cost));
        }

        System.out.println(getMstCost());
    }

    private static long getMstCost() {

        long cost = 0;

        while (!pq.isEmpty())  {
            Edge e = pq.poll();

            int v1 = e.v1, v2 = e.v2;
            int parent1 = findRootParent(v1);
            int parent2 = findRootParent(v2);

            if (parent1 == parent2) {
                continue;
            }

            cost += e.cost;

            int root = Math.min(parent1, parent2);
            int child = Math.max(parent1, parent2);

            parents[child] = root;
        }

        return cost;
    }

    private static int findRootParent(int v)    {
        return v == parents[v] ?
                v : (parents[v] = findRootParent(parents[v]));
    }
}

class Edge implements Comparable<Edge> {
    int v1, v2;
    int cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}
