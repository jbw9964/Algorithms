import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, T;
    private static int[][] numbers;
    private static Command[] commands;

    private static final int[]
            dr = {0, 0, 1, -1},
            dc = {1, -1, 0, 0};

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        numbers = new int[N][];
        for (int i = 0; i < N; i++) {
            numbers[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        commands = new Command[T];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            commands[i] = new Command(x, d, k);
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        for (Command command : commands) {

            rotateFor(command);

            boolean[][] visited = new boolean[N][M];
            List<Traversal> netTraversals = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    int val = numbers[r][c];

                    if (val != 0 && !visited[r][c]) {
                        Traversal traversal = travelOn(visited, r, c);
                        if (traversal.hasAdjacent()) {
                            netTraversals.add(traversal);
                        }
                    }

                }
            }

            if (netTraversals.isEmpty()) {
                double avg = getAvg();

                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {

                        int val;
                        if ((val = numbers[r][c]) == 0) {
                            continue;
                        }

                        if (val > avg) {
                            numbers[r][c]--;
                        } else if (val < avg) {
                            numbers[r][c]++;
                        }
                    }
                }

            } else {
                netTraversals.stream()
                        .flatMap(t -> t.getCords().stream())
                        .forEach(c -> numbers[c.r][c.c] = 0);
            }
        }

        int sum = Arrays.stream(numbers)
                .mapToInt(r -> Arrays.stream(r).sum())
                .sum();

        System.out.println(sum);
    }

    private static void rotateFor(Command command) {

        int x = command.x;
        int d = command.d;
        int k = command.k;

        for (int r = 1; r <= N; r++) {

            if (r % x == 0) {
                int[] row = numbers[r - 1];

                switch (d) {
                    case 0:
                        shiftRight(row, k);
                        break;
                    case 1:
                        shiftLeft(row, k);
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }
    }

    private static void shiftRight(int[] row, int move) {

        // [1, 2, 3, 4] --> [4, 1, 2, 3] --> [3, 4, 1, 2]

        int len = row.length;

        int[] tempArr = new int[len];
        for (int i = 0; i < len; i++) {
            int replaceI = i - move < 0 ? len + i - move : i - move;
            tempArr[i] = row[replaceI];
        }

        System.arraycopy(tempArr, 0, row, 0, len);
    }

    private static void shiftLeft(int[] row, int move) {

        // [1, 2, 3, 4] --> [2, 3, 4, 1] --> [3, 4, 1, 2]
        int len = row.length;

        int[] tempArr = new int[len];
        for (int i = 0; i < len; i++) {
            int replaceI = (i + move) % len;
            tempArr[i] = row[replaceI];
        }

        System.arraycopy(tempArr, 0, row, 0, len);
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static int adjustColumn(int c) {
        return c < 0 ? M + c : c % M;
    }

    private static Traversal travelOn(boolean[][] visited, int initR, int initC) {

        Traversal traversal = new Traversal();

        Queue<Cord> q = new LinkedList<>();
        q.add(new Cord(initR, initC));

        int val = numbers[initR][initC];
        visited[initR][initC] = true;

        while (!q.isEmpty()) {
            Cord cord = q.poll();
            int r = cord.r;
            int c = cord.c;

            traversal.addCord(cord);

            for (int i = 0; i < 4; i++) {

                int nextR = r + dr[i];
                int nextC = adjustColumn(c + dc[i]);

                if (
                        !inRange(nextR, nextC) ||
                        numbers[nextR][nextC] != val ||
                        visited[nextR][nextC]
                ) {
                    continue;
                }

                visited[nextR][nextC] = true;
                q.add(new Cord(nextR, nextC));
            }
        }

        return traversal;
    }

    private static double getAvg() {
        double sum = 0;
        int count = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int val = numbers[r][c];

                if (val != 0) {
                    sum += val;
                    count++;
                }
            }
        }
        return sum / count;
    }
}

class Command {

    final int x, d, k;

    public Command(int x, int d, int k) {
        this.x = x;
        this.d = d;
        this.k = k;
    }
}

class Traversal {

    final List<Cord> visitedCords;

    public Traversal() {
        visitedCords = new ArrayList<>();
    }

    public void addCord(Cord cord) {
        visitedCords.add(cord);
    }

    public boolean hasAdjacent() {
        return visitedCords.size() >= 2;
    }

    public List<Cord> getCords() {
        return visitedCords;
    }
}

class Cord {

    final int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
