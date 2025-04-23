import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[][] plates;
    private static Command[] commands;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        plates = new int[N][];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            plates[i] = row;
        }

        commands = new Command[T];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            commands[i] = new Command(
                    x, (d == 0 ? DIR.LEFT : DIR.RIGHT),
                    k
            );
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        for (Command cmd : commands) {

            int x = cmd.x;

            List<Integer> rotatingIndices = IntStream.range(0, N)
                    .filter(i -> (i + 1) % x == 0)
                    .boxed()
                    .collect(Collectors.toList());

            for (int index : rotatingIndices) {
                int[] target = plates[index];
                rotatePlate(target, cmd);
            }

            List<Cord> deletions = new ArrayList<>();
            boolean[][] visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int value = plates[i][j];

                    if (visited[i][j] || value == 0) {
                        continue;
                    }

                    List<Cord> dst = new ArrayList<>();
                    dfs(value, i, j, visited, dst);

                    if (dst.size() > 1) {
                        deletions.addAll(dst);
                    }
                }
            }

            if (deletions.isEmpty()) {
                processPlate(plates);
            } else {
                for (Cord cord : deletions) {
                    int r = cord.r;
                    int c = cord.c;
                    plates[r][c] = 0;
                }
            }
        }

        int sum = Arrays.stream(plates).mapToInt(row -> Arrays.stream(row).sum()).sum();
        System.out.println(sum);
    }

    private static void dfs(int value, int r, int c, boolean[][] visited, List<Cord> dst) {

        if (r < 0 || r >= visited.length) {
            return;
        }

        if (c < 0) {
            c = visited[r].length + c;
        } else if (c >= visited[r].length) {
            c = c - visited[r].length;
        }

        if (visited[r][c] || value != plates[r][c]) {
            return;
        }

        dst.add(new Cord(r, c));
        visited[r][c] = true;

        dfs(value, r - 1, c, visited, dst);
        dfs(value, r + 1, c, visited, dst);
        dfs(value, r, c - 1, visited, dst);
        dfs(value, r, c + 1, visited, dst);
    }

    private static void rotatePlate(int[] plate, Command command) {

        DIR dir = command.d;
        int step = command.k;
        step = dir == DIR.RIGHT ? -step : step;

        int[] tmp = new int[plate.length];
        for (int i = 0; i < plate.length; i++) {
            int val = plate[i];
            int newIdx = (i + step) % plate.length;
            newIdx = newIdx < 0 ? newIdx + plate.length : newIdx;
            tmp[newIdx] = val;
        }

        System.arraycopy(tmp, 0, plate, 0, tmp.length);
    }

    private static void processPlate(int[][] plates) {
        int sum = 0;

        List<Cord> targets = new ArrayList<>();
        for (int i = 0; i < plates.length; i++) {
            for (int j = 0; j < plates[i].length; j++) {

                int val = plates[i][j];
                sum += val;

                if (val != 0) {
                    targets.add(new Cord(i, j));
                }
            }
        }

        if (targets.isEmpty()) {
            return;
        }

        double avg = (double) sum / targets.size();
        for (Cord cord : targets) {
            int r = cord.r;
            int c = cord.c;

            int val = plates[r][c];

            if (avg > val) {
                plates[r][c]++;
            } else if (avg < val) {
                plates[r][c]--;
            }
        }
    }
}

enum DIR {
    LEFT, RIGHT
}

class Command {

    int x, k;
    DIR d;

    public Command(int x, DIR d, int k) {
        this.x = x;
        this.k = k;
        this.d = d;
    }
}

class Cord {

    int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}