import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] map = new int[N][M];
        int numOfWalls = 0;

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 6)
                    numOfWalls++;
            }
        }

        Manager manager = new Manager(map);
        manager.traverse();
        System.out.println(N * M - numOfWalls - manager.MAX_COVERAGE);
    }
}

class Manager {
    public int MAX_COVERAGE = 0;
    private List<CCTV> list;
    private int[][] map;

    int R, C;

    boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public Manager(int[][] initInfo) {
        R    = initInfo.length;
        C    = initInfo[0].length;
        list = new ArrayList<>(10);

        this.map = initInfo.clone();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++)
                if (initInfo[r][c] != 0 && initInfo[r][c] != 6)
                    list.add(new CCTV(r, c, initInfo[r][c]));
        }
    }

    public void traverse() {
        traverse(0);
    }

    private void traverse(int index) {
        if (index >= list.size()) {
            estimateCoverage();
            return;
        }

        CCTV cctv = list.get(index);
        CCTV_Type type = cctv.type;

        if (type == CCTV_Type.NO5)
            traverse(index + 1);

        else if (type == CCTV_Type.NO2) {
            for (int i = 0; i < 2; i++) {
                traverse(index + 1);
                cctv.rotate();
            }
        }

        else {
            for (int i = 0; i < 4; i++) {
                traverse(index + 1);
                cctv.rotate();
            }
        }
    }

    private void estimateCoverage() {
        boolean[][] visitTable = new boolean[R][C];

        for (CCTV cctv : list)
            for (Direction dir : cctv.directions)
                fillVisitTable(visitTable, cctv.r, cctv.c, dir);

        int coverage = 0;
        for (boolean[] row : visitTable)
            for (boolean b : row)
                if (b)
                    coverage++;

        MAX_COVERAGE = Math.max(MAX_COVERAGE, coverage);
    }

    private void fillVisitTable(
            boolean[][] visitTable, int r, int c, Direction dir
    ) {
        int dr = dir == Direction.LEFT || dir == Direction.RIGHT ? 0 : dir == Direction.UP ? -1 : 1;
        int dc = dir == Direction.UP || dir == Direction.DOWN ? 0 : dir == Direction.LEFT ? -1 : 1;

        while (inRange(r, c) && map[r][c] != 6) {
            visitTable[r][c] = true;
            r += dr;
            c += dc;
        }
    }
}

class CCTV {
    public CCTV_Type type;
    public Direction[] directions;
    public int r, c;

    public CCTV(int r, int c, int no) {
        this.r     = r;
        this.c     = c;
        type       = CCTV_Type.valueOf(no);
        directions = Direction.valueOf(type);
    }

    public void rotate() {
        for (int i = 0; i < directions.length; i++)
             directions[i] = Direction.rotateClockwise(directions[i]);
    }
}

//<editor-fold desc="enum constants">
enum CCTV_Type {
    NO1, NO2, NO3, NO4, NO5;

    public static CCTV_Type valueOf(int no) {
        switch (no) {
            case 1:
                return NO1;
            case 2:
                return NO2;
            case 3:
                return NO3;
            case 4:
                return NO4;
            case 5:
                return NO5;
            default:
                return null;
        }
    }
}

enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public static Direction[] valueOf(CCTV_Type type) {
        switch (type) {
            case NO1:
                return new Direction[]{Direction.RIGHT};
            case NO2:
                return new Direction[]{Direction.RIGHT, Direction.LEFT};
            case NO3:
                return new Direction[]{Direction.RIGHT, Direction.UP};
            case NO4:
                return new Direction[]{Direction.RIGHT, Direction.LEFT, Direction.UP,};
            case NO5:
                return new Direction[]{Direction.RIGHT, Direction.LEFT, Direction.UP, Direction.DOWN};
            default:
                return null;
        }
    }

    public static Direction rotateClockwise(Direction dir) {
        switch (dir) {
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case UP:
                return RIGHT;
            default:
                return null;
        }
    }
}
//</editor-fold>
