import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static int[][] table;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        R = C = n;

        table = new int[n][n];

        for (int i = 0; i < table.length; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < table[i].length; j++) {
                int value = Integer.parseInt(st.nextToken());
                table[i][j] = value;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int score = 0;

        while (true) {
            boolean[][] visit = new boolean[R][R];
            List<BlockGroup> candidates = new ArrayList<>();

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < R; j++) {

                    if (table[i][j] <= 0 || visit[i][j]) {
                        continue;
                    }

                    BlockGroup blockGroup = getBlockGroup(i, j, visit);

                    if (blockGroup != null) {
                        candidates.add(blockGroup);
                    }
                }
            }

            BlockGroup targets = candidates.stream()
                    .sorted()
                    .findFirst().orElse(null);

            if (targets == null) {
                break;
            }

            for (Block target : targets.group)    {
                table[target.r][target.c] = TableManager.EMPTY_BLOCK;
            }

            int addition = targets.size();
            score += addition * addition;


            TableManager.applyGravity(table);
            table = TableManager.rotateTable(table);
            TableManager.applyGravity(table);
        }

        System.out.println(score);
    }

    private static BlockGroup getBlockGroup(int initR, int initC, boolean[][] visit) {
        int initValue = table[initR][initC];
        visit[initR][initC] = true;

        BlockGroup dst = new BlockGroup();
        Queue<Block> q = new LinkedList<>();

        Block initBlock = new Block(initValue, initR, initC);
        dst.addBlock(initBlock);
        q.add(initBlock);

        List<Block> rainbows = new ArrayList<>();

        while (!q.isEmpty()) {

            Block b = q.poll();

            for (int i = 0; i < dr.length; i++) {

                int r = b.r + dr[i];
                int c = b.c + dc[i];

                if (!inRange(r, c)) {
                    continue;
                }

                if (!TableManager.moveable(initValue, r, c, table)) {
                    continue;
                }

                if (visit[r][c]) {
                    continue;
                }

                visit[r][c] = true;
                Block newBlock = new Block(table[r][c], r, c);

                dst.addBlock(newBlock);
                q.add(newBlock);

                if (table[r][c] == 0) {
                    rainbows.add(newBlock);
                }
            }
        }

        for (Block rainbow : rainbows)  {
            visit[rainbow.r][rainbow.c] = false;
        }

        return dst.size() >= 2 ? dst : null;
    }
}

class Block {

    int blockValue;
    int r, c;

    public Block(int blockValue, int r, int c) {
        this.blockValue = blockValue;
        this.r = r;
        this.c = c;
    }
}

class BlockGroup implements Comparable<BlockGroup> {

    final List<Block> group;

    private int numOfRainbows;
    private int minRowIndex, minColIndex;

    public BlockGroup() {
        this.group = new ArrayList<>();
        numOfRainbows = 0;
        minRowIndex = minColIndex = Integer.MAX_VALUE;
    }

    public void addBlock(Block block) {
        if (block.blockValue == 0) {
            numOfRainbows++;
        } else {
            minRowIndex = Math.min(minRowIndex, block.r);
            minColIndex = Math.min(minColIndex, block.c);
        }
        group.add(block);
    }

    public int size() {
        return group.size();
    }

    @Override
    public int compareTo(BlockGroup o) {

        if (size() != o.size()) {
            return o.size() - size();
        }

        if (numOfRainbows != o.numOfRainbows) {
            return o.numOfRainbows - numOfRainbows;
        }

        if (minRowIndex != o.minRowIndex) {
            return o.minRowIndex - minRowIndex;
        }

        return o.minColIndex - minColIndex;
    }
}

class TableManager {

    public static int BLACK_BLOCK = -1;
    public static int EMPTY_BLOCK = BLACK_BLOCK - 5;

    public static int[][] rotateTable(final int[][] table) {

        int len = table.length;
        int[][] dst = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dst[i][j] = table[j][len - i - 1];
            }
        }

        return dst;
    }

    public static void applyGravity(int[][] table) {

        int len = table.length;

        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < len; j++) {
                int blockVal = table[i][j];

                if (blockVal <= BLACK_BLOCK) {
                    continue;
                }

                int lowestRow = getLowestRow(i, j, table);
                table[i][j] = table[lowestRow][j];
                table[lowestRow][j] = blockVal;

            }
        }
    }

    private static int getLowestRow(int r, int c, final int[][] table) {

        while (r < table.length - 1) {
            if (table[r + 1][c] > EMPTY_BLOCK) {
                break;
            }
            r++;
        }

        return r;
    }

    public static boolean moveable(int blockValue, int newR, int newC, int[][] table) {
        int value = table[newR][newC];
        return blockValue == value || value == 0;
    }

    public static void showTable(int[][] table) {

        for (int[] row : table) {
            for (int value : row) {

                if (value == EMPTY_BLOCK) {
                    System.out.print("   ");
                } else {
                    System.out.printf("%2d ", value);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
