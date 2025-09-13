import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static PriorityQueue<Edge> edges;
    private static int[] parents;

    private static final Comparator<Planet>
            sortByX = Comparator.comparing(Planet::getX),
            sortByY = Comparator.comparing(Planet::getY),
            sortByZ = Comparator.comparing(Planet::getZ);
    private static final ToLongFunction<Planet>
            getX = Planet::getX,
            getY = Planet::getY,
            getZ = Planet::getZ;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        List<Planet> planetList = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());

            planetList.add(new Planet(i, x, y, z));
        }

        edges = new PriorityQueue<>(3 * N);
        Comparator<Planet>[] comparators = new Comparator[]{
                sortByX, sortByY, sortByZ
        };
        ToLongFunction<Planet>[] getFunctions = new ToLongFunction[]{
                getX, getY, getZ
        };

        for (int i = 0; i < 3; i++) {
            Comparator<Planet> comp = comparators[i];
            ToLongFunction<Planet> func = getFunctions[i];

            planetList.sort(comp);

            Iterator<Planet> iterator = planetList.iterator();
            Planet prev = iterator.next();

            while (iterator.hasNext()) {
                Planet curr = iterator.next();

                int v1 = prev.index;
                int v2 = curr.index;
                long cost = Math.abs(func.applyAsLong(curr) - func.applyAsLong(prev));

                edges.add(new Edge(v1, v2, cost));

                prev = curr;
            }
        }

        parents = IntStream.range(0, N).toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        long answer = getMstCostFrom();

        System.out.println(answer);
    }

    private static long getMstCostFrom() {

        int edgeCnt = 0;
        long costSum = 0;

        while (!edges.isEmpty() && edgeCnt < N - 1) {

            Edge edge = edges.poll();

            int v1 = edge.v1, v2 = edge.v2;
            int p1 = getParent(v1), p2 = getParent(v2);

            if (p1 == p2) {
                continue;
            }

            costSum += edge.cost;
            edgeCnt++;

            int root = Math.min(p1, p2);
            int child = Math.max(p1, p2);

            parents[child] = root;
        }

        return costSum;
    }

    private static int getParent(int v) {
        return parents[v] == v ? v : (parents[v] = getParent(parents[v]));
    }
}

class Planet {

    final int index;
    final long x, y, z;

    public Planet(int index, long x, long y, long z) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Planet{" +
               "x=" + x +
               ", y=" + y +
               ", z=" + z +
               '}';
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
        return Long.compare(this.cost, o.cost);
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
