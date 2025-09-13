import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static Planet[] planets;
    private static List<Edge>[] edges;

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
        planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());

            planetList.add(planets[i] = new Planet(i, x, y, z));
        }

        edges = new List[N];
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

                int from = prev.index;
                int to = curr.index;
                long cost = Math.abs(func.applyAsLong(curr) - func.applyAsLong(prev));

                if (edges[from] == null) {
                    edges[from] = new ArrayList<>();
                }
                edges[from].add(new Edge(to, cost));

                if (edges[to] == null) {
                    edges[to] = new ArrayList<>();
                }
                edges[to].add(new Edge(from, cost));

                prev = curr;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long answer = getMstCostFrom(0);

        System.out.println(answer);
    }

    private static long getMstCostFrom(int start) {

        boolean[] visited = new boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        int visitCnt = 0;
        long costSum = 0;

        while (!pq.isEmpty() && visitCnt < N) {
            Edge curr = pq.poll();

            int to = curr.to;
            long cost = curr.cost;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            costSum += cost;
            visitCnt++;

            List<Edge> candidates = edges[to];
            if (candidates == null) {
                continue;
            }

            for (Edge c : candidates) {
                int to2 = c.to;
                if (!visited[to2]) {
                    pq.add(c);
                }
            }
        }

        return costSum;
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

    final int to;
    final long cost;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
               "to=" + to +
               ", cost=" + cost +
               '}';
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.cost, o.cost);
    }
}
