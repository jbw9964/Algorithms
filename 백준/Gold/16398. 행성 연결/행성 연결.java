import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static List<Edge> edges;

    private static int[] parents;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        edges = new ArrayList<>(N * N / 2);
        parents = IntStream.range(0, N + 1).toArray();

        for (int i = 0; i < N; i++) {
            int[] costs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = i + 1; j < N; j++) {
                edges.add(new Edge(i + 1, j + 1, costs[j]));
            }
        }

        edges.sort(Comparator.comparing(e -> e.cost));
    }

    public static void main(String[] args) throws IOException {

        init();

        long answer = getMSTCost();

        System.out.println(answer);
    }


    private static long getMSTCost() {
        long cost = 0L;
        int count = 0;


        for (Edge minima : edges) {

            int v1 = minima.v1;
            int v2 = minima.v2;

            int p1 = findAndUpdateParent(v1);
            int p2 = findAndUpdateParent(v2);

            if (p1 == p2) {
                continue;
            }

            parents[p1] = p2;

            cost += minima.cost;
            count++;

            if (count >= N - 1) {
                break;
            }
        }

        return cost;
    }

    private static int findAndUpdateParent(int v) {
        return v == parents[v] ? v : (parents[v] = findAndUpdateParent(parents[v]));
    }

    private static void showParents() {
        System.out.println(Arrays.toString(parents));
    }
}

class Edge {

    int v1, v2;
    int cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
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
