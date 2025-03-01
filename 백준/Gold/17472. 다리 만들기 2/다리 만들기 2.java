import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int[] parents;

    private static int[][] init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] table = new int[n][];
        for (int i = 0; i < n; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return table;
    }

    public static void main(String[] args) throws IOException {

        int[][] table = init();

        IslandManager manager = new IslandManager(table);

        int numOfIslands = manager.islands.size();
        parents = IntStream.range(0, numOfIslands).toArray();

        PriorityQueue<Edge> edges = new PriorityQueue<>(
                manager.inspectEdges()
        );

        int minCost = getMstCost(edges);

        int root = getRootParent(0);
        for (int node = 1; node < numOfIslands; node++) {
            if (root != getRootParent(node)) {
                minCost = -1;
                break;
            }
        }

        System.out.println(minCost);
    }

    private static int getMstCost(Queue<Edge> edges) {

        int cost = 0;

        while (!edges.isEmpty())    {
            Edge edge = edges.poll();

            int p1 = getRootParent(edge.from);
            int p2 = getRootParent(edge.to);

            if (p1 == p2)   {
                continue;
            }

            cost += edge.cost;

            int root = Math.min(p1, p2);
            int child = Math.max(p1, p2);
            parents[child] = root;
        }

        return cost;
    }

    private static int getRootParent(int v) {
        return v == parents[v] ? parents[v] : (parents[v] = getRootParent(parents[v]));
    }
}

class IslandManager {

    final List<Island> islands = new ArrayList<>();

    final int[][] table;

    final int R, C;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    private boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public IslandManager(int[][] table) {
        this.table = table;
        R = table.length;
        C = table[0].length;

        inspectIslands();
    }

    private void inspectIslands() {
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (table[i][j] != 1 || visited[i][j]) {
                    continue;
                }

                List<Cord> cords = new ArrayList<>();

                visited[i][j] = true;
                Queue<Cord> q = new ArrayDeque<>();
                q.add(new Cord(i, j));

                while (!q.isEmpty()) {
                    Cord cord = q.poll();

                    cords.add(cord);

                    for (int k = 0; k < dr.length; k++) {
                        int r = cord.i + dr[k];
                        int c = cord.j + dc[k];

                        if (inRange(r, c) && table[r][c] == 1 && !visited[r][c]) {
                            q.add(new Cord(r, c));
                            visited[r][c] = true;
                            cords.add(new Cord(r, c));
                        }
                    }
                }

                islands.add(new Island(
                        islands.size(), cords
                ));
            }
        }
    }

    private static final BiFunction<Integer, Integer, int[]> UP
            = (r, c) -> new int[]{r - 1, c};
    private static final BiFunction<Integer, Integer, int[]> DOWN
            = (r, c) -> new int[]{r + 1, c};
    private static final BiFunction<Integer, Integer, int[]> LEFT
            = (r, c) -> new int[]{r, c - 1};
    private static final BiFunction<Integer, Integer, int[]> RIGHT
            = (r, c) -> new int[]{r, c + 1};


    public List<Edge> inspectEdges() {
        List<Edge> edges = new ArrayList<>();

        for (Island island : islands) {
            List<Edge> newEdges = inspectEdgeOn(island);
            edges.addAll(newEdges);
        }

        return edges;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private List<Edge> inspectEdgeOn(Island init) {
        List<Edge> edges = new ArrayList<>();

        boolean[][] visited = new boolean[R][C];
        for (Cord cord : init.cords) {
            visited[cord.i][cord.j] = true;
        }

        for (Cord cord : init.cords) {
            int r = cord.i;
            int c = cord.j;

            for (BiFunction func : new BiFunction[]{UP, DOWN, LEFT, RIGHT}) {
                Cord end = travelTillEnd(r, c, visited, func);

                if (end == null) {
                    continue;
                }

                int cost = (r != end.i ? Math.abs(r - end.i) : Math.abs(c - end.j)) - 1;
                if (cost < 2) {
                    continue;
                }

                Island connected = getIsland(end.i, end.j);
                if (connected == null) {
                    throw new RuntimeException();
                }

                edges.add(new Edge(init.index, connected.index, cost));
            }
        }

        return edges;
    }

    private Cord travelTillEnd(
            int r, int c, boolean[][] visited,
            BiFunction<Integer, Integer, int[]> func
    ) {
        int[] next = func.apply(r, c);
        int nextR = next[0];
        int nextC = next[1];

        if (!inRange(nextR, nextC) || visited[nextR][nextC]) {
            return null;
        }
        if (table[nextR][nextC] == 1 && !visited[nextR][nextC]) {
            return new Cord(nextR, nextC);
        }

        return travelTillEnd(nextR, nextC, visited, func);
    }

    private Island getIsland(int r, int c) {

        for (Island island : islands) {
            if (island.contains(r, c)) {
                return island;
            }
        }

        return null;
    }
}

class Island {

    final int index;
    final Set<Cord> cords;

    public Island(int index, List<Cord> cords) {
        this.index = index;
        this.cords = new HashSet<>(cords);
    }

    public boolean contains(int i, int j) {
        return cords.contains(new Cord(i, j));
    }
}

class Cord {

    final int i, j;

    public Cord(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Cord)) {
            return false;
        }

        Cord cord = (Cord) o;
        return i == cord.i && j == cord.j;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }
}

class Edge implements Comparable<Edge> {

    final int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}
