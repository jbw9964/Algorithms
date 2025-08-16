
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static char[][] table;

    private static final int[] dr = {1, -1, 0, 0},
            dc = {0, 0, 1, -1};
    private static final State[] dState
            = {State.TO_DOWN, State.TO_UP, State.TO_RIGHT, State.TO_LEFT};

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        table = new char[N][];
        for (int i = 0; i < N; i++) {
            table[i] = br.readLine().toCharArray();
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int startR, startC, endR, endC;
        startR = startC = endR = endC = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                char val = table[i][j];

                if (val == '#') {
                    if (startR == -1) {
                        startR = i;
                        startC = j;
                    } else {
                        endR = i;
                        endC = j;
                    }
                }

            }
        }

        List<Cord> initCords = getDeployableCordsFrom(startR, startC);
        initCords.forEach(c -> c.placedMirrors = 0);

        assert !initCords.isEmpty();

        int answer = bfs(initCords, endR, endC);

        System.out.println(answer);
    }

    private static List<Cord> getDeployableCordsFrom(int r, int c) {
        List<Cord> deployableCords = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if (
                    inRange(nextR, nextC) &&
                    table[nextR][nextC] != '*'
            ) {
                deployableCords.add(new Cord(nextR, nextC, dState[i]));
            }
        }

        return deployableCords;
    }

    private static int bfs(List<Cord> starts, int endR, int endC) {

        boolean[][][] visited = new boolean[N][N][N * N + 1];
        starts.forEach(c -> visited[c.r][c.c][0] = true);

        PriorityQueue<Cord> pq = new PriorityQueue<>();
        pq.addAll(starts);

        while (!pq.isEmpty()) {
            Cord cord = pq.poll();

            int r = cord.r;
            int c = cord.c;
            int placedMirrors = cord.placedMirrors;
            State currentState = cord.state;

            if (r == endR && c == endC) {
                return cord.placedMirrors;
            }

            if (table[r][c] == '.') {
                int nextR = cord.nextR();
                int nextC = cord.nextC();

                if (
                        inRange(nextR, nextC) &&
                        table[nextR][nextC] != '*' &&
                        !visited[nextR][nextC][placedMirrors]
                ) {
                    visited[nextR][nextC][placedMirrors] = true;
                    pq.add(new Cord(nextR, nextC, placedMirrors, currentState));
                }

            } else if (table[r][c] == '!') {

                List<Cord> deployableCords = getDeployableCordsFrom(r, c).stream()
                        .filter(cr -> cr.state != cord.state.reverse())
                        .collect(Collectors.toList());

                for (Cord deployable : deployableCords) {

                    deployable.placedMirrors
                            = placedMirrors + (deployable.state == currentState ? 0 : 1);

                    int nextR = deployable.r;
                    int nextC = deployable.c;

                    visited[nextR][nextC][deployable.placedMirrors] = true;
                    pq.add(deployable);
                }
            }
        }

        throw new RuntimeException();
    }
}

enum State {
    TO_UP, TO_DOWN, TO_LEFT, TO_RIGHT;

    State reverse() {
        return this == TO_UP ? TO_DOWN : this == TO_DOWN ? TO_UP :
                this == TO_LEFT ? TO_RIGHT : TO_LEFT;
    }
}

class Cord implements Comparable<Cord> {

    int r, c;
    int placedMirrors;
    State state;

    public Cord(int r, int c, State state) {
        this.r = r;
        this.c = c;
        this.state = state;
    }

    public Cord(int r, int c, int placedMirrors, State state) {
        this.r = r;
        this.c = c;
        this.placedMirrors = placedMirrors;
        this.state = state;
    }

    public int nextR() {
        return this.state == State.TO_DOWN ? r + 1 :
                this.state == State.TO_UP ? r - 1 : r;
    }

    public int nextC() {
        return this.state == State.TO_RIGHT ? c + 1 :
                this.state == State.TO_LEFT ? c - 1 : c;
    }

    @Override
    public int compareTo(Cord o) {
        return Integer.compare(this.placedMirrors, o.placedMirrors);
    }

    @Override
    public String toString() {
        return "Cord{" +
               "r=" + r +
               ", c=" + c +
               ", placedMirrors=" + placedMirrors +
               ", state=" + state +
               '}';
    }
}
