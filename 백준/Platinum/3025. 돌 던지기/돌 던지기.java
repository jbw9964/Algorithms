import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;
    private static char[][] table;
    private static int N;
    private static int[] dropColumns;
    private static Deque<Cord>[] cordCaches;

    private static boolean outOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        table = new char[R][];
        for (int i = 0; i < R; i++) {
            table[i] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());
        dropColumns = new int[N];
        for (int i = 0; i < N; i++) {
            dropColumns[i] = Integer.parseInt(br.readLine()) - 1;
        }

        //noinspection unchecked
        cordCaches = IntStream.range(0, C)
                .mapToObj(c -> new ArrayDeque<>(R))
                .toArray(Deque[]::new);
    }

    public static void main(String[] args) throws IOException {

        init();

        for (int col : dropColumns) {
            simulateOn(col);
        }

        printTable();
    }

    private static void simulateOn(int dropCol) {

        int r, c;

        Deque<Cord> cordCache = cordCaches[dropCol];
        validateCache(cordCache);

        if (cordCache.isEmpty()) {
            r = 0;
            c = dropCol;
        } else {
            r = cordCache.peekLast().r;
            c = cordCache.peekLast().c;
        }

        while (true) {

            if (blockedByWallOrEdge(r, c)) {
                break;
            }

            if (nothingBelow(r, c)) {
                cordCache.addLast(new Cord(r++, c));
                continue;
            }

            if (leftMoveable(r, c)) {
                r++;
                c--;
                continue;
            }

            if (rightMoveable(r, c)) {
                r++;
                c++;
                continue;
            }

            break;
        }

        table[r][c] = 'O';
    }

    private static void validateCache(Deque<Cord> cordCache) {
        Cord peek;

        while (
                (peek = cordCache.peekLast()) != null &&
                table[peek.r][peek.c] != '.'
        ) {
            cordCache.removeLast();
        }
    }

    private static boolean blockedByWallOrEdge(int r, int c) {
        return outOfRange(r + 1, c) || table[r + 1][c] == 'X';
    }

    private static boolean nothingBelow(int r, int c) {
        if (outOfRange(r, c)) {
            throw new IllegalArgumentException();
        }
        return table[r + 1][c] == '.';
    }

    private static boolean leftMoveable(int r, int c) {
        return !outOfRange(r + 1, c - 1) &&
               table[r][c - 1] == '.' &&
               table[r + 1][c - 1] == '.';
    }

    private static boolean rightMoveable(int r, int c) {
        return !outOfRange(r + 1, c + 1) &&
               table[r][c + 1] == '.' &&
               table[r + 1][c + 1] == '.';
    }

    public static void printTable() {
        for (char[] row : table) {
            System.out.println(String.valueOf(row));
        }
    }
}

class Cord {

    int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
