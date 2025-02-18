import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, D;
    private static int[][] table;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        table = new int[N][];
        for (int i = 0; i < N; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    private static final List<int[]> availableArcherPoses = new ArrayList<>();

    private static void initArcherPoses(int depth, int prev, int[] tmp) {

        if (depth >= tmp.length) {
            availableArcherPoses.add(tmp.clone());
            return;
        }

        int restore = tmp[depth];
        for (int trial = prev + 1; trial < M; trial++) {
            tmp[depth] = trial;
            initArcherPoses(depth + 1, trial, tmp);
            tmp[depth] = restore;
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        initArcherPoses(0, -1, new int[3]);

        int MAXIMA = Arrays.stream(table)
                .mapToInt(row -> Arrays.stream(row).sum())
                .sum();

//        availableArcherPoses.forEach(
//                arr -> System.out.println(Arrays.toString(arr))
//        );

        int ans = 0;
        for (int[] archerPose : availableArcherPoses) {

            if (MAXIMA == ans) {
                break;
            }

            GameManager manager = new GameManager(
                    D, archerPose[0], archerPose[1], archerPose[2], table
            );

            int score = manager.doGame();
            ans = Math.max(ans, score);
        }

        System.out.println(ans);
    }
}

class GameManager {

    final int D;
    final int N, M;
    final List<Integer> archerIndices;
    final int[][] gameTable;

    public GameManager(int D, int i1, int i2, int i3, int[][] table) {
        this.D = D;
        archerIndices = Arrays.asList(i1, i2, i3);
        archerIndices.sort(Integer::compareTo);

        N = table.length;
        M = table[0].length;

        gameTable = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(table[i], 0, gameTable[i], 0, M);
        }
    }

    private int getNumOfEnemies() {
        return Arrays.stream(gameTable)
                .mapToInt(arr -> Arrays.stream(arr).sum())
                .sum();
    }

    public int doGame() {
        int score = 0;

        while (getNumOfEnemies() > 0) {
            score += killEnemies();
            moveEnemiesDown();
        }

        return score;
    }


    private int killEnemies() {
        Set<Cord> enemyToKill = new HashSet<>();
        for (int archerIndex : archerIndices) {
            Cord enemy = getClosestEnemyFromArcher(archerIndex);
            if (enemy != null) {
                enemyToKill.add(enemy);
            }
        }

        for (Cord enemy : enemyToKill) {
            gameTable[enemy.i][enemy.j] = 0;
        }

        return enemyToKill.size();
    }

    private static final int[] di = {0, -1, 0}, dj = {-1, 0, 1};

    private boolean inRange(int i, int j) {
        return i >= 0 && i < gameTable.length && j >= 0 && j < gameTable[i].length;
    }

    private Cord getClosestEnemyFromArcher(int archerIndex) {

        Queue<Cord> queue = new ArrayDeque<>((N + 1) * M);
        boolean[][] visited = new boolean[N + 1][M];

        queue.add(new Cord(N, archerIndex, 0));
        visited[N][archerIndex] = true;

        while (!queue.isEmpty()) {
            Cord cord = queue.poll();
            int dist = cord.dist;

            if (gameTable[cord.i][cord.j] != 0) {
                return cord;
            }

            if (dist >= D) {
                continue;
            }

            for (int k = 0; k < di.length; k++) {
                int i = cord.i + di[k];
                int j = cord.j + dj[k];

                if (!inRange(i, j) || visited[i][j]) {
                    continue;
                }

                visited[i][j] = true;
                queue.add(new Cord(i, j, dist + 1));
            }
        }

        return null;
    }

    private void moveEnemiesDown() {
        for (int i = N; i >= 1; i--) {
            gameTable[i] = gameTable[i - 1];
        }
        gameTable[0] = gameTable[N]= new int[M];
    }

    public void showTable() {
        for (int[] row : gameTable) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

class Cord {

    int i, j;
    int dist;

    public Cord(int i, int j, int dist) {
        this.i = i;
        this.j = j;
        this.dist = dist;
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
        return Objects.hash(i, j);
    }
}
